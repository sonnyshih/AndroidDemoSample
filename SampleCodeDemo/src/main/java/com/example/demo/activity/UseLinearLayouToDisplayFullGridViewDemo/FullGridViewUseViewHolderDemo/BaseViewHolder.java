package com.example.demo.activity.UseLinearLayouToDisplayFullGridViewDemo.FullGridViewUseViewHolderDemo;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class BaseViewHolder extends RecyclerView.ViewHolder{
    private View itemView;

    public BaseViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
    }

    public View getItemView() {
        return itemView;
    }

    public abstract void bindView(ViewHolderInfo viewInfo);
}
