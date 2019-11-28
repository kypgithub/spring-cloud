package com.abugong.cloud.mysql.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abugong.cloud.mysql.entity.Pemission;
import com.abugong.cloud.mysql.entity.User;
import com.abugong.cloud.mysql.service.PemissionService;
import com.abugong.cloud.mysql.service.UserService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import ch.qos.logback.classic.Logger;

@RestController
@RequestMapping("/user")
public class UserCoutroller {
	
	protected final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserService userService;
	@Autowired
	private PemissionService pemissionService;
	
	@RequestMapping(value= "/login", method = RequestMethod.POST)
	public User login(@RequestBody User user) {
	    EntityWrapper<User> ew = new EntityWrapper<User>();
	    ew.where("username={0}", user.getUsername()).and("password={0}", user.getPassword());
		return userService.selectOne(ew);
	}
	
	@RequestMapping(value= "/regist", method = RequestMethod.POST)
	public Boolean regist(@RequestBody User user) {
		return userService.insert(user);
	}
	
	/**
	 * 测试Hystrix断路器
	 * @param id
	 * @return
	 */
	@RequestMapping(value= "/findeUserById", method = RequestMethod.GET)
	public User findeUserById(@RequestParam("id") Integer id) {
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		User user =userService.selectById(id);
		return user;
	}
	@RequestMapping(value= "/findeUserByUsername", method = RequestMethod.GET)
	public User findeUserByUserame(@RequestParam("username") String username) {
	    EntityWrapper<User> ew = new EntityWrapper<User>();
	    ew.where("username={0}", username);
		return userService.selectOne(ew);
	}
	@GetMapping("/getPemissionList")
	List<Pemission> getPemissionList(@RequestParam("id") Integer id){
		return pemissionService.getPemissionList(id);
	}
}
