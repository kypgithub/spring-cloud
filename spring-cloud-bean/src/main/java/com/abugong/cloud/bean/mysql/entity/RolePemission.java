package com.abugong.cloud.bean.mysql.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableName;

@TableName("t_role_pemission")
public class RolePemission implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer roleId;
	private Integer pemissionId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getPemissionId() {
		return pemissionId;
	}
	public void setPemissionId(Integer pemissionId) {
		this.pemissionId = pemissionId;
	}
	@Override
	public String toString() {
		return "RolePemission [id=" + id + ", roleId=" + roleId + ", pemissionId=" + pemissionId + "]";
	}


}
