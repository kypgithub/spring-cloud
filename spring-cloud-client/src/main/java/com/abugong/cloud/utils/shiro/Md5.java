package com.abugong.cloud.utils.shiro;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class Md5 {
	
	/**
	 * 加密
	 * @param username
	 * @param pwd
	 * @return
	 */
	public static String MD5Pwd(String username, String pwd) {
        // 加密算法MD5
        // salt盐 username + salt
        // 迭代次数
		// toHex ：十六进制
        String md5Pwd = new SimpleHash("MD5", pwd,ByteSource.Util.bytes(username + "salt"), 2).toHex();
        return md5Pwd;
    }

}

