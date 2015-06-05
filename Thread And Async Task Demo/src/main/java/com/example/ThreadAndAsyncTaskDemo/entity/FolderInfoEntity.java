package com.example.ThreadAndAsyncTaskDemo.entity;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class FolderInfoEntity implements Serializable{

	private static final long serialVersionUID = -6000656345875740731L;

	@SerializedName("folders")
	private List<FolderEntity> folders;

	@SerializedName("files")
	private List<FileEntity> files;

	public List<FolderEntity> getFolders() {
		return folders;
	}

	public void setFolders(List<FolderEntity> folders) {
		this.folders = folders;
	}

	public List<FileEntity> getFiles() {
		return files;
	}

	public void setFiles(List<FileEntity> files) {
		this.files = files;
	}

	
}
