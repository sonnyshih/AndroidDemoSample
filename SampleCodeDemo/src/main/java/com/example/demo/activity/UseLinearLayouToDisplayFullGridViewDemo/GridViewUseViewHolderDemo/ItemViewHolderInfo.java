package com.example.demo.activity.UseLinearLayouToDisplayFullGridViewDemo.GridViewUseViewHolderDemo;


import com.example.demo.activity.UseLinearLayouToDisplayFullGridViewDemo.entity.Data;

public class ItemViewHolderInfo implements ViewHolderInfo {

    private Data data;

    public ItemViewHolderInfo(Data data){
        this.data = data;
    }

    @Override
    public int getItemViewType() {
        return data.getType();
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
