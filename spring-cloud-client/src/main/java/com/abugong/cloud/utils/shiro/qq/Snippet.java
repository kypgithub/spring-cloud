package com.abugong.cloud.utils.shiro.qq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.message.BasicNameValuePair;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.abugong.cloud.utils.HttpUtil;
import com.alibaba.fastjson.JSON;

@RestController
public class Snippet {
	
    @Autowired
    private OAuthProperties oauth;
	
	//接收回调地址带过来的code码
	@GetMapping("/authorize/qq")
	public String authorizeQQ(Map<String, String> msg, String code) {
		List<BasicNameValuePair> list = new ArrayList<>();
	    list.add(new BasicNameValuePair("code", code));
	    list.add(new BasicNameValuePair("grant_type", "authorization_code"));
	    list.add(new BasicNameValuePair("redirect_uri", oauth.getQQ().getRedirect_uri()));
	    list.add(new BasicNameValuePair("client_id", oauth.getQQ().getClient_id()));
	    list.add(new BasicNameValuePair("client_secret", oauth.getQQ().getClient_secret()));
	    
	    //获取access_token如：access_token=9724892714FDF1E3ED5A4C6D074AF9CB&expires_in=7776000&refresh_token=9E0DE422742ACCAB629A54B3BFEC61FF
	    String url = oauth.getQQ().getAccess_token_callback_uri();
	    String result = HttpUtil.get(url, list);
	    //对拿到的数据进行切割字符串
	    String[] strings = result.split("&");
	    //切割好后放进map
	    Map<String, String> reulsts = new HashMap<>();
	    for (String str : strings) {
	        String[] split = str.split("=");
	        if (split.length > 1) {
	            reulsts.put(split[0], split[1]);
	        }
	    }
	    //到这里access_token已经处理好了
	    //下一步获取openid，只有拿到openid才能拿到用户信息
	    List<BasicNameValuePair> list2 = new ArrayList<>();
	    list2.add(new BasicNameValuePair("access_token", reulsts.get("access_token")));
	    String openidContent = HttpUtil.get(oauth.getQQ().getOpenid_callback_uri(), list2);
	    //接下来对openid进行处理
	    //截取需要的那部分json字符串
	    String openid = openidContent.substring(openidContent.indexOf("{"), openidContent.indexOf("}") + 1);
	    //将返回的openid转换成DTO
	    QQOpenidDTO qqOpenidDTO = JSON.parseObject(openid, QQOpenidDTO.class);
	
	    //接下来说说获取用户信息部分
	    //登陆的时候去数据库查询用户数据对于openid是存在，如果存在的话，就不用拿openid获取用户信息了，而是直接从数据库拿用户数据直接认证用户，
	    // 否则就拿openid去腾讯服务器获取用户信息，并存入数据库，再去认证用户
	    //下面关于怎么获取用户信息，并登陆
	    List<BasicNameValuePair> list3 = new ArrayList<>();
	    list3.add(new BasicNameValuePair("access_token", reulsts.get("access_token")));//设置access_token
	    list3.add(new BasicNameValuePair("openid", qqOpenidDTO.getOpenid()));//设置openid
	    list3.add(new BasicNameValuePair("oauth_consumer_key", qqOpenidDTO.getClient_id()));//设置appid
	    //获取用户信息
	    String userInfo = HttpUtil.get(oauth.getQQ().getUser_info_callback_uri(), list3);
	    QQDTO qqDTO = JSON.parseObject(userInfo, QQDTO.class);
	    //这里拿用户昵称，作为用户名，openid作为密码（正常情况下，在开发时候用openid作为用户名，再自己定义个密码就可以了）
	    try {
	        SecurityUtils.getSubject().login(new UsernamePasswordToken(qqDTO.getNickname(), qqOpenidDTO.getOpenid()));
	    }catch (Exception e){
	        msg.put("msg","第三方登陆失败,请联系管理！");
	        return "login.html";
	    }
	    return "redirect:/index";
	}
	
}

