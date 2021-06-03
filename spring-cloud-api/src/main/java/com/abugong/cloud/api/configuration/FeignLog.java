package com.abugong.cloud.api.configuration;
import org.springframework.context.annotation.Bean;
import feign.Logger;


/**
 * 日志配置类
 * @author 64691
 *
 */
public class FeignLog {
	
	@Bean
    public Logger.Level feignLoggerLevel() {
        return feign.Logger.Level.FULL;                  //full表示打印全部信息，一共4个级别
	}
//	/**
//	 * feign原生契约
//	 */
//	@Bean
//	public Contract feignContract() {
//		return new feign.Contract.Default();
//	}

}
