package com.example.ThreadAndAsyncTaskDemo.entity;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class FolderEntity implements Serializable{

	private static final long serialVersionUID = -6581611201174497534L;

	@SerializedName("name")
	private String name;

	@SerializedName("device_name")
	private String deviceName;

	@SerializedName("path")
	private String path;

	@SerializedName("type")
	private String type;

	@SerializedName("last_edit_user")
	private String lastEditUser;

	@SerializedName("count")
	private int count;

	@SerializedName("size")
	private int size;

	@SerializedName("priority")
	private int priority;

	@SerializedName("note")
	private boolean note;

	@SerializedName("note_content")
	private String noteContent;
	
	@SerializedName("independent")
	private boolean independent;

	@SerializedName("owner_id")
	private String ownerId;
	
	@SerializedName("subfoldercount")
	private int subfoldercount;

	@SerializedName("subfilecount")
	private int subfilecount;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLastEditUser() {
		return lastEditUser;
	}

	public void setLastEditUser(String lastEditUser) {
		this.lastEditUser = lastEditUser;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public boolean isNote() {
		return note;
	}

	public void setNote(boolean note) {
		this.note = note;
	}

	public String getNoteContent() {
		return noteContent;
	}

	public void setNoteContent(String noteContent) {
		this.noteContent = noteContent;
	}

	public boolean isIndependent() {
		return independent;
	}

	public void setIndependent(boolean independent) {
		this.independent = independent;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public int getSubfoldercount() {
		return subfoldercount;
	}

	public void setSubfoldercount(int subfoldercount) {
		this.subfoldercount = subfoldercount;
	}

	public int getSubfilecount() {
		return subfilecount;
	}

	public void setSubfilecount(int subfilecount) {
		this.subfilecount = subfilecount;
	}

}
