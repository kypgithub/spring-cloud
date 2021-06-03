package com.abugong.cloud.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.abugong.cloud.mysql.entity.User;
import com.abugong.cloud.user.UserApi;
import com.abugong.cloud.utils.shiro.Md5;
import com.abugong.cloud.utils.shiro.qq.OAuthProperties;

@Controller
public class UserController {
	@Autowired
	private UserApi userApi;
	 
    @Autowired
    private OAuthProperties oauth;
    
	@PostMapping({"/login", "/api/login"})
	@ResponseBody
	public Map<String, Object> login(@RequestBody User user, Model model) {
		System.out.println(user);
		Subject subject = SecurityUtils.getSubject();  //1.获取subject
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());  //2.封装用户数据
		Map<String ,Object> map= new HashMap<String, Object>();
		try {
			subject.login(token); //3.执行登陆方法
		}catch(UnknownAccountException e){
			map.put("msg", "用户名不存在");
			return map;
		}
		catch (IncorrectCredentialsException e) {
			map.put("msg", "密码错误");
			return map;
		} catch (LockedAccountException lae) {
			map.put("msg", "账户已锁定");
			return map;
		} catch (ExcessiveAttemptsException eae) {
			map.put("msg", "用户名或密码错误次数过多");
			return map;
		}
		map.put("msg", "登陆成功");
		map.put("username", user.getUsername());
		return map;
	}
	
	
	@PostMapping("/regist")
	@ResponseBody
	public Map<String, String> regist(@RequestBody User user){
		String md5pwd = Md5.MD5Pwd(user.getUsername(), user.getPassword());
		user.setPassword(md5pwd);
		Map<String, String> map = new HashMap<String, String>();
		if (userApi.regist(user))
			map.put("msg", "注册成功");
		else
			map.put("msg", "注册失败");
		return map;
	}
	@RequestMapping("/logout")
	public void logout(HttpServletResponse response) throws IOException {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		response.sendRedirect("/login");
	}
	@RequestMapping(value= "/user/findeUserByName", method = RequestMethod.GET)
	public User findeUserByUserame(@RequestParam("username") String username){
		return userApi.findeUserByUserame(username);
	}
	@RequestMapping(value= "/user/findeUserById", method = RequestMethod.GET)
	public User findeUserById(@RequestParam("id") Integer id) {
		return userApi.findeUserById(id);
	}
	
	@GetMapping("/getUsername")
	public String getUsername() {
		Subject subject = SecurityUtils.getSubject();
	    Object principal = subject.getPrincipal();
		return principal.toString();
	}
	@GetMapping({"/login", "/login.html"})
	public String login() {
		return "login";
	}
	@GetMapping({"/index", "index.html"})
	public ModelAndView index(ModelAndView model) {
		String username = getUsername();
		model.addObject("username", username);
		model.setViewName("/index");
		return model;
	}
	@GetMapping("/regist")
	public String regist(){
		return "/regist";
	}
 
	/**
	 * QQ授权登录
	 * @param response
	 */
    @GetMapping("/login/qq")
    public void loginQQ(HttpServletResponse response) {
        try {
            response.sendRedirect(
            		oauth.getQQ().getCode_callback_uri() +
                    "?client_id=" + oauth.getQQ().getClient_id()
                    + "&state=" + UUID.randomUUID()  //这个说是防攻击的，就给个随机uuid吧
                    + "&redirect_uri=" + oauth.getQQ().getRedirect_uri() //这个很重要，这个是回调地址，即就收腾讯返回的code码
                    + "&response_type=code");  //授权模式，授权码模式
        } catch (IOException e) {
            e.printStackTrace();
        }
 
    }
	
}
