package com.example.ThreadAndAsyncTaskDemo.entity;

import java.io.Serializable;
import com.google.gson.annotations.SerializedName;

public class ShopAllNavigationEntity implements Serializable{

	private static final long serialVersionUID = -3912073620937206324L;
	
	@SerializedName("ShowSeeAllDeals")
	private boolean showSeeAllDeals;
	
	@SerializedName("CustomLink")
	private String customLink;
	
	@SerializedName("LinkParams")
	private Object linkParams;
	
	@SerializedName("Description")
	private String description;
	
	@SerializedName("StoreType")
	private int storeType;
	
	@SerializedName("CategoryId")
	private int categoryId;
	
	@SerializedName("StoreId")
	private int storeId;
	
	@SerializedName("SubCategoryId")
	private int subCategoryId;
	
	@SerializedName("BrandId")
	private int brandId;
	
	@SerializedName("NValue")
	private String nValue;
	
	@SerializedName("ItemCount")
	private int itemCount;
	
	@SerializedName("NodeId")
	private int nodeId;
	
	@SerializedName("Keyword")
	private String keyword;

	public boolean isShowSeeAllDeals() {
		return showSeeAllDeals;
	}

	public void setShowSeeAllDeals(boolean showSeeAllDeals) {
		this.showSeeAllDeals = showSeeAllDeals;
	}

	public String getCustomLink() {
		return customLink;
	}

	public void setCustomLink(String customLink) {
		this.customLink = customLink;
	}

	public Object getLinkParams() {
		return linkParams;
	}

	public void setLinkParams(Object linkParams) {
		this.linkParams = linkParams;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStoreType() {
		return storeType;
	}

	public void setStoreType(int storeType) {
		this.storeType = storeType;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public int getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(int subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public int getBrandId() {
		return brandId;
	}

	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}

	public String getnValue() {
		return nValue;
	}

	public void setnValue(String nValue) {
		this.nValue = nValue;
	}

	public int getItemCount() {
		return itemCount;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

	public int getNodeId() {
		return nodeId;
	}

	public void setNodeId(int nodeId) {
		this.nodeId = nodeId;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
}
