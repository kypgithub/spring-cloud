package com.abugong.cloud.api.user;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.abugong.cloud.api.configuration.FeignLog;
import com.abugong.cloud.mysql.entity.Pemission;
import com.abugong.cloud.mysql.entity.User;
/**
 *  用户服务
 * @author 64691
 *
 */
@FeignClient(value="spring-cloud-user", configuration = FeignLog.class)
public interface UserApi {
	
	@RequestMapping(value= "/user/findeUserById", method = RequestMethod.GET)
	public User findeUserById(@RequestParam("id") Integer id);
	
	@RequestMapping(value= "/user/login", method = RequestMethod.POST)
	public User login(@RequestBody User user);
	
	@RequestMapping(value= "/user/regist", method = RequestMethod.POST)
	public Boolean regist(@RequestBody User user);

	@RequestMapping(value= "/user/findeUserByUsername", method = RequestMethod.GET)
	public User findeUserByUserame(@RequestParam("username") String username);
	
	@GetMapping("/user/getPemissionList")
	List<Pemission> getPemissionList(@RequestParam("id") Integer id);

}
