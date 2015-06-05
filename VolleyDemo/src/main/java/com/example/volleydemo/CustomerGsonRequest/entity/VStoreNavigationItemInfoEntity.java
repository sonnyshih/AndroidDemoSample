package com.example.volleydemo.CustomerGsonRequest.entity;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class VStoreNavigationItemInfoEntity implements Serializable{
    private static final long serialVersionUID = 2298090870653359881L;
    
	@SerializedName("ShowSeeAllDeals")
    private boolean showSeeAllDeals;

	@SerializedName("CustomLink")
    private String customLink;

	@SerializedName("LinkParams")
    private String linkParams;

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
    private String Keyword;

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

	public String getLinkParams() {
		return linkParams;
	}

	public void setLinkParams(String linkParams) {
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
		return Keyword;
	}

	public void setKeyword(String keyword) {
		Keyword = keyword;
	}


//	@SerializedName("ShowSeeAllDeals")
//    private boolean showSeeAllDeals;
//
//    @SerializedName("NodeId")
//    private int nodeId;
//
//    @SerializedName("CustomLink")
//    private String customLink;
//
//    @SerializedName("Keyword")
//    private String keyword;
//
//    @SerializedName("Description")
//    private String description;
//
//    @SerializedName("StoreType")
//    private int storeType;
//
//    @SerializedName("CategoryId")
//    private int categoryId;
//
//    @SerializedName("StoreId")
//    private int storeId;
//
//    @SerializedName("SubCategoryId")
//    private int subCategoryId;
//
//    @SerializedName("BrandId")
//    private int brandId;
//
//    @SerializedName("NValue")
//    private String nValue;
//
//    @SerializedName("ItemCount")
//    private int itemCount;
//
//    @SerializedName("ModuleParams")
//    private String moduleParams;
//
////    @SerializedName("LinkParams")
////    private VLinkParamsInfoEntity linkParams;

}
