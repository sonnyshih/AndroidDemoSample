package com.example.demo.activity.UseLinearLayouToDisplayFullGridViewDemo.FullGridViewUseViewHolderDemo;


import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import com.example.demo.R;

import java.util.List;

public class MyAdapter {
    private Context context;
    private LinearLayout mainLinearLayout;
    private List<ItemViewHolderInfo> list;
    private int column;
    private OnClickListener onClickListener;

    public MyAdapter(Context context, LinearLayout mainLinearLayout,
                     List<ItemViewHolderInfo> list, int column) {

        this.context = context;
        this.mainLinearLayout = mainLinearLayout;
        this.list = list;
        this.column = column;
    }

    public void setOnClickListener(OnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }

    public void startLayout() {
        mainLinearLayout.removeAllViews();

        ViewTreeObserver vto = mainLinearLayout.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                layoutView(mainLinearLayout.getWidth());

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    mainLinearLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    mainLinearLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }

            }
        });

    }

    public void layoutView(double width) {

        int row = 0;

        int remainder = list.size() % column;

        if (remainder > 0) {
            row = list.size() / column + 1;
        } else {
            row = list.size() / column;
        }

        for (int i = 0; i < row; i++) {

            LinearLayout.LayoutParams rowLayoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            LinearLayout linearLayout = new LinearLayout(context);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout.setLayoutParams(rowLayoutParams);

            for (int j = 0; j < column; j++) {

                ViewGroup.LayoutParams columnLayoutParams = new ViewGroup.LayoutParams((int) width / column,
                        ViewGroup.LayoutParams.MATCH_PARENT);

                int index = i * column + j;

                LayoutInflater inflater = LayoutInflater.from(context);
                View convertView = null;

                int viewType = View.VISIBLE;
                if (index < list.size()) {

                    ItemViewHolderInfo viewHolderInfo = list.get(index);

                    switch (viewHolderInfo.getItemViewType()) {
                        case 1:
                            convertView = inflater.inflate(R.layout.full_grid_view_adapter1, null);
                            ViewHolder1 viewHolder1 = new ViewHolder1(convertView);
                            viewHolder1.setItemLayout(columnLayoutParams);
                            viewHolder1.setItemLayoutVisible(viewType);
                            viewHolder1.bindView(viewHolderInfo);
                            break;

                        case 2:
                            convertView = inflater.inflate(R.layout.full_grid_view_adapter2, null);
                            ViewHolder2 viewHolder2 = new ViewHolder2(convertView);
                            viewHolder2.setLayout(columnLayoutParams);
                            viewHolder2.setItemLayoutVisible(viewType);
                            viewHolder2.bindView(viewHolderInfo);
                            break;

                        default:
                            break;
                    }

                    if (convertView != null) {
                        linearLayout.addView(convertView);
                    }

                }

            }

            mainLinearLayout.addView(linearLayout);
        }

    }
}
