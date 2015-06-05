package com.example.volleydemo.CustomerGsonRequest.entity;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class UserInfoEntity implements Serializable{
	
	private static final long serialVersionUID = 673639622201786122L;
	
	@SerializedName("token")
	private String token;

	@SerializedName("id")
	private String id;

	@SerializedName("name")
	private String name;

	@SerializedName("cellphone")
	private String cellphone;

	@SerializedName("country_code")
	private String countryCode;

	@SerializedName("last_edit_time")
	private String lastEditTime;

	@SerializedName("usage")
	private double usage;

	@SerializedName("total")
	private double total;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getLastEditTime() {
		return lastEditTime;
	}

	public void setLastEditTime(String lastEditTime) {
		this.lastEditTime = lastEditTime;
	}

	public double getUsage() {
		return usage;
	}

	public void setUsage(double usage) {
		this.usage = usage;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

}
