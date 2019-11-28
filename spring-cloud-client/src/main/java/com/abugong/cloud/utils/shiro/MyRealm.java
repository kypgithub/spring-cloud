package com.abugong.cloud.utils.shiro;

import java.util.List;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import com.abugong.cloud.mysql.entity.Pemission;
import com.abugong.cloud.mysql.entity.User;
import com.abugong.cloud.user.UserApi;
/**
 * 授权认证
 */
public class MyRealm extends AuthorizingRealm{
	
	@Autowired
	private UserApi userApi;
	/**
	 * 授权（权限认证）
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		List<Pemission> pemissionList = userApi.getPemissionList(user.getId());
		if (pemissionList != null) {
			for (Pemission Pemission: pemissionList) {
				info.addStringPermission(Pemission.getPemission());
			}
		}
		
		return info;
	}
	/**
	 * 认证 （身份认证）
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		//获取登陆信息
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		User res = userApi.findeUserByUserame(upToken.getUsername());
		if (res != null) {
			// 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配, CredentialsMatcher会自动匹配密码
//			System.out.println(Md5.MD5Pwd(res.getUsername(), res.getPassword()));
	        return new SimpleAuthenticationInfo(res, res.getPassword(), ByteSource.Util.bytes(res.getUsername() + "salt"), getName());
		}
        else {
        	return null;
        }
	}
	
}
