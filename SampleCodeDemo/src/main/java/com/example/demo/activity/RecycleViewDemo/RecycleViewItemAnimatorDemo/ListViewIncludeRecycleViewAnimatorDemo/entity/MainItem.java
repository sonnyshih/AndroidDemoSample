package com.example.demo.activity.RecycleViewDemo.RecycleViewItemAnimatorDemo.ListViewIncludeRecycleViewAnimatorDemo.entity;

import com.example.demo.activity.RecycleViewDemo.RecycleViewItemAnimatorDemo.ListViewIncludeRecycleViewAnimatorDemo.ListViewIncludeRecycleViewAnimatorActivity;

import java.util.ArrayList;

public class MainItem {
    private String mainTitle;
    private ArrayList<SubItem> subItemList;

    public String getMainTitle() {
        return mainTitle;
    }

    public void setMainTitle(String mainTitle) {
        this.mainTitle = mainTitle;
    }

    public ArrayList<SubItem> getSubItemList() {
        return subItemList;
    }

    public void setSubItemList(ArrayList<SubItem> subItemList) {
        this.subItemList = subItemList;
    }
}
