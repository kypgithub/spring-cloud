package com.abugong.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.abugong.cloud.mysql.entity.User;
import com.abugong.cloud.user.UserApi;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
@RestController
public class HystrixController {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private UserApi userApi;


	/**
	 * feign访问
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod="getFallback")   //线程池隔离（默认），不等待，适合线程安全高并发(快速响应，避免堆积）
	@RequestMapping(value= "/findeUserById", method = RequestMethod.GET)
	public User findeUserById(@RequestParam("id") Integer id) {
		return userApi.findeUserById(id);
	}
	/**
	 * feign 的回滚方法
	 * @param id
	 * @return
	 */
	public String getFallback() {
		return "调用远程接口超时===getFallback";
	}
	/**
	 * Templeat 的回滚方法
	 * @param id
	 * @return
	 */
	public String getFallbackTempleat() {	
		return "调用远程接口异常===getFallback";
	}
	
	
	
	
	/**
	 * RestTemplate  
	 * Hystrix断路器监控此方法，如果远程调用出现异常或者延迟，调用自定义的getFallback方法返回子弟工艺信息
	 * @param id
	 * @return
	 */
//	@HystrixCommand(fallbackMethod="getFallbackTempleat")
//	@RequestMapping(value= "/findeUserById", method=RequestMethod.GET)
//	public String get(@RequestParam("id") Integer id) {
//		String URL="http://spring-cloud-provider/user/findeUserById?id=" + id; 
//		return restTemplate.getForObject(URL, String.class,id);
//	}
}
