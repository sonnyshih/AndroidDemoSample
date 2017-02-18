package com.example.demo.activity.UseLinearLayouToDisplayFullGridViewDemo.GridViewUseViewHolderDemo;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.demo.R;
import com.example.demo.activity.UseLinearLayouToDisplayFullGridViewDemo.entity.Data;

public class ViewHolder2 extends BaseViewHolder{

    private LinearLayout itemLayout;
    private TextView titleTextView;
    private TextView descriptionTextView;

    public ViewHolder2(View itemView) {
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

    public void setLayout(ViewGroup.LayoutParams columnLayoutParams){
        itemLayout.setLayoutParams(columnLayoutParams);
    }

    public void setItemLayoutVisible(int visible){
        itemLayout.setVisibility(visible);
    }
}
