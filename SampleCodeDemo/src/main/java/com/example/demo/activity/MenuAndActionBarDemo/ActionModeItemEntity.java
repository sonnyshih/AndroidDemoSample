package com.example.demo.activity.MenuAndActionBarDemo;

import java.io.Serializable;

public class ActionModeItemEntity implements Serializable{

	private static final long serialVersionUID = 6410408655415224088L;

	private String name;
	private String description;
	private boolean isCheck = false;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public boolean isCheck() {
		return isCheck;
	}
	
	public void setCheck(boolean isCheck) {
		this.isCheck = isCheck;
	}
	
}
