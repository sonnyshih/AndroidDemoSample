package com.example.demo.activity.UseLinearLayouToDisplayFullGridViewDemo.GridViewUseViewHolderDemo;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.demo.R;
import com.example.demo.activity.UseLinearLayouToDisplayFullGridViewDemo.entity.Data;

public class ViewHolder1 extends BaseViewHolder{

    private LinearLayout itemLayout;
    private TextView titleTextView;
    private TextView descriptionTextView;

    public ViewHolder1(View itemView) {
        super(itemView);

        itemLayout = (LinearLayout) itemView.findViewById(R.id.fullGridView_itemLayout);
        titleTextView = (TextView) itemView.findViewById(R.id.gridView_titleTextView);
        descriptionTextView = (TextView) itemView.findViewById(R.id.gridView_descriptionTextView);

    }

    @Override
    public void bindView(ViewHolderInfo viewInfo) {
        ItemViewHolderInfo itemViewHolderInfo = (ItemViewHolderInfo) viewInfo;

        Data data = itemViewHolderInfo.getData();
        titleTextView.setText(data.getTitle());
        descriptionTextView.setText(data.getDescription());

    }

    public void setItemLayout(ViewGroup.LayoutParams columnLayoutParams){
        itemLayout.setLayoutParams(columnLayoutParams);
    }

    public void setItemLayoutVisible(int visible){
        itemLayout.setVisibility(visible);
    }

    public void setItemLayoutOnClickListener(OnClickListener onClickListener){
        itemLayout.setOnClickListener(onClickListener);

    }

    public void setItemLayoutTag(Object tag){
        itemLayout.setTag(tag);
    }
}
