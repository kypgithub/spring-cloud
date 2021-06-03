package com.abugong.cloud.bean.mongo.entity;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="product")
public class Product implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
    private String productName;
    private Integer state;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", productName=" + productName + ", state=" + state + "]";
	}

    

}
