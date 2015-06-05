package com.example.ThreadAndAsyncTaskDemo.entity;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class FileEntity implements Serializable{

	private static final long serialVersionUID = -4797887172434283171L;

	@SerializedName("params")
	private Object params;

	@SerializedName("name")
	private String name;

	@SerializedName("unique_id")
	private String uniqueId;

	@SerializedName("device_name")
	private String deviceName;

	@SerializedName("path")
	private String path;

	@SerializedName("source_path")
	private String sourcePath;

	@SerializedName("type")
	private String type;

	@SerializedName("last_edit_time")
	private String lastEditTime;

	@SerializedName("last_edit_user")
	private String lastEditUser;

	@SerializedName("size")
	private double size;

	@SerializedName("priority")
	private int priority;

	@SerializedName("note")
	private boolean note;

	@SerializedName("note_content")
	private String noteContent;

	@SerializedName("rotate")
	private int rotate;

	@SerializedName("blocks")
	private Object blocks;

	@SerializedName("independent")
	private boolean independent;

	@SerializedName("owner_id")
	private String ownerId;

	@SerializedName("streaming_header_file")
	private String streamingHeaderFile;

	public Object getParams() {
		return params;
	}

	public void setParams(Object params) {
		this.params = params;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
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

	public String getSourcePath() {
		return sourcePath;
	}

	public void setSourcePath(String sourcePath) {
		this.sourcePath = sourcePath;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLastEditTime() {
		return lastEditTime;
	}

	public void setLastEditTime(String lastEditTime) {
		this.lastEditTime = lastEditTime;
	}

	public String getLastEditUser() {
		return lastEditUser;
	}

	public void setLastEditUser(String lastEditUser) {
		this.lastEditUser = lastEditUser;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
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

	public int getRotate() {
		return rotate;
	}

	public void setRotate(int rotate) {
		this.rotate = rotate;
	}

	public Object getBlocks() {
		return blocks;
	}

	public void setBlocks(Object blocks) {
		this.blocks = blocks;
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

	public String getStreamingHeaderFile() {
		return streamingHeaderFile;
	}

	public void setStreamingHeaderFile(String streamingHeaderFile) {
		this.streamingHeaderFile = streamingHeaderFile;
	}
 	
}
