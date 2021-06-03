package com.abugong.cloud.utils.shiro;

import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

/**
 * @author kangyuanping
 * @version 2019年11月10日下午6:38:49
 */
@Configuration
public class ShiroConfig{
	/**
	 *  创建ShiroFilterFactoryBean
	 * 1:获取数据
	 */
	@Bean
	public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager")DefaultWebSecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);   //设置安全管理器

		//添加shiro内置过滤器
		/**
		 *     常用内置过滤器
		 * anon :无需认证
		 * authc: 必须认证
		 * user: 如果使用rememberMe的功能可以直接访问
		 * perms: 拥有该资源权限的可以访问
		 * role: 该用户拥有权限可以访问
		 */
		Map<String, String> fiterMap = new LinkedHashMap<String, String>();
		/**
		 * 拦截配置
		 */
		fiterMap.put("/js/**", "anon");
		fiterMap.put("/login", "anon");
		fiterMap.put("/login.html", "anon");
		fiterMap.put("/regist", "anon");
		fiterMap.put("/authorize/qq", "anon");//anon表示可以匿名访问
		/**
		 * 授权配置
		 */
		//		fiterMap.put("/add.*", "perms[user:add]");
		fiterMap.put("/**", "authc");
		//设置登陆页面
		shiroFilterFactoryBean.setLoginUrl("/login");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(fiterMap);
		//没有权限跳转页
		shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized.html");
		return shiroFilterFactoryBean;
	}
	/**
	 * 创建DefaultWebSecurityManage
	 * 2: 去安全管理器
	 */
	@Bean(name="securityManager")
	public DefaultWebSecurityManager securityManage(@Qualifier("myRealm")MyRealm myRealm) {
		DefaultWebSecurityManager securityManage = new DefaultWebSecurityManager();
		securityManage.setRealm(myRealm);
		return securityManage;
	}

	/**
	 * 3:创建Realm对象
	 * 关联自定义Realm
	 */
	@Bean(name="myRealm")
	public MyRealm myRealm() {
        //  使用加密算法初始化Realm
		MyRealm myRealm = new MyRealm();
		myRealm.setCredentialsMatcher(hashedCredentialsMatcher());
		myRealm.setCachingEnabled(false);
		return myRealm;
	}

	/**
	 *  1 注解权限
	 *  开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
	 * 配置以下两个bean(DefaultAdvisorAutoProxyCreator和AuthorizationAttributeSourceAdvisor)即可实现此功能
	 * @return
	 */
	@Bean
	public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
		DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		advisorAutoProxyCreator.setProxyTargetClass(true);
		return advisorAutoProxyCreator;
	}

	/**
	 * 开启aop注解支持  2 注解权限
	 * @param securityManager
	 * @return
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager")DefaultWebSecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}
	/**
	 * 配置thymeleaf和shiro标签
	 * @return
	 */
	@Bean
	public ShiroDialect shiroDialect() {
		return new ShiroDialect();
	}
	
	/**
	 * 密码加密
	 * @return
	 */
	@Bean(name = "credentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // 散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        // 散列的次数，比如散列两次，相当于 md5(md5(""));
        hashedCredentialsMatcher.setHashIterations(2);
        // storedCredentialsHexEncoded默认是true，此时用的是密码加密用的是Hex编码；false时用Base64编码
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        return hashedCredentialsMatcher;
    }
}
