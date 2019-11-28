package com.abugong.cloud.mysql.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableName;

@TableName("t_role")
public class Role implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String pemission;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPemission() {
		return pemission;
	}
	public void setPemission(String pemission) {
		this.pemission = pemission;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", pemission=" + pemission + "]";
	}


}
