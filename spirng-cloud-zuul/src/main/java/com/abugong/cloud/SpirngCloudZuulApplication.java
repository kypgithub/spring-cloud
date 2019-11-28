package com.abugong.cloud;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.abugong.cloud.utils.zuul.CustomZuulFilter;

@EnableEurekaClient
@EnableZuulProxy
@SpringBootApplication
public class SpirngCloudZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpirngCloudZuulApplication.class, args);
	}
	
	  @Bean 
	  public CustomZuulFilter customZuulFilter() { 
		  return new CustomZuulFilter(); 
	  }
	 
}
