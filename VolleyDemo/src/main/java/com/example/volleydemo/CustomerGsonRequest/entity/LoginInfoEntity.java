package com.example.volleydemo.CustomerGsonRequest.entity;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class LoginInfoEntity implements Serializable{

	private static final long serialVersionUID = -3573451358193148450L;

	@SerializedName("user")
	private String user;

	@SerializedName("password")
	private String password;

	@SerializedName("device_id")
	private String device_id;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDevice_id() {
		return device_id;
	}

	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}

		
}
