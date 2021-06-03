package com.abugong.cloud.utils.shiro.qq;
 
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
 
/**
 * 
 * @author kyp
 * @version 2019年12月6日上午12:37:28
 */

@Component
@ConfigurationProperties(prefix = "oauth")//对应application.yml中，oauth下参数
public class OAuthProperties {
 
    //获取applicaiton.yml下qq下所有的参数
    private QQProperties qq = new QQProperties();
 
    public QQProperties getQQ() {
        return qq;
    }
 
    public void setQQ(QQProperties qq) {
        this.qq = qq;
    }
}