package com.abugong.cloud;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableCaching
@EnableEurekaClient
@SpringBootApplication
@MapperScan("com.abugong.cloud.mysql.dao")
public class SpringCloudEmailApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudEmailApplication.class, args);
	}

}
