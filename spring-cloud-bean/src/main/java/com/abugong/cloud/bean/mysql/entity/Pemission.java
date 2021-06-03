package com.abugong.cloud.bean.mysql.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("t_pemission")
public class Pemission implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String jurisdiction;
	private String pemission;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getJurisdiction() {
		return jurisdiction;
	}
	public void setJurisdiction(String jurisdiction) {
		this.jurisdiction = jurisdiction;
	}
	public String getPemission() {
		return pemission;
	}
	public void setPemission(String pemission) {
		this.pemission = pemission;
	}
	@Override
	public String toString() {
		return "Pemission [id=" + id + ", jurisdiction=" + jurisdiction + ", pemission=" + pemission + "]";
	}



}
