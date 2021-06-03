package com.abugong.cloud.utils;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@Configuration
@EnableSwagger2
public class Swagger2 {
   
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.abugong.cloud.coutroller"))
				.paths(PathSelectors.any())
				.build();
		        
	}
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("结合swagger，实现restful")
				.description("欢迎大家使用java构建中小型公司架构")
				.termsOfServiceUrl("")
				.version("1.0")
				.build();
	}
}
