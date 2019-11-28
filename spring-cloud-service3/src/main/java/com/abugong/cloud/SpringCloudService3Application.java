package com.abugong.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableEurekaServer
@SpringBootApplication
public class SpringCloudService3Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudService3Application.class, args);
	}
	//因为新版本的security默认开启了csrf拦截, 所以需要禁用csrf功能
		@EnableWebSecurity
	    static class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	        @Override
	        protected void configure(HttpSecurity http) throws Exception {
	            http.csrf().disable();
	            super.configure(http);
	        }
	    }
}
