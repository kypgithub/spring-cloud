package com.abugong.cloud.mysql.entity;

import java.io.Serializable;
/**
 * 认证信息
 * @author 64691
 *
 */
public class UserInfo implements Serializable{
     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;  
     private String uid;
    private String accesstoken;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
   
	public String getAccesstoken() {
		return accesstoken;
	}
	public void setAccesstoken(String accesstoken) {
		this.accesstoken = accesstoken;
	}
	@Override
	public String toString() {
		return "UserInfo [username=" + username + ", uid=" + uid + ", accesstoken=" + accesstoken + "]";
	}

     
     
}
