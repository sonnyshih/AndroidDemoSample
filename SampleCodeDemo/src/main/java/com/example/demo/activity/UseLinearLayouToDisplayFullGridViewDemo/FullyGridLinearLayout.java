
package com.example.demo.activity.UseLinearLayouToDisplayFullGridViewDemo;


import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.view.View.OnClickListener;

import java.util.List;

public class FullyGridLinearLayout {
    private Context context;
    private int adapterLayout;
    private FullyGridLinearLayoutListener listener;
    private List list;
    private LinearLayout mainLinearLayout;
    private int column;
    private LayoutInflater inflater;
    private OnClickListener onClickListener;

    public FullyGridLinearLayout(Context context, final LinearLayout mainLinearLayout,
                                 int adapterLayout, FullyGridLinearLayoutListener listener,
                                 List list, int column) {
        this.context = context;
        this.adapterLayout = adapterLayout;
        this.listener = listener;
        this.list = list;
        this.mainLinearLayout = mainLinearLayout;
        this.column = column;

        inflater = LayoutInflater.from(context);

    }

    public void setSubItemOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void startLayout() {
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

        mainLinearLayout.removeAllViews();

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

                View convertView = inflater.inflate(adapterLayout, null);
                ViewGroup.LayoutParams columnLayoutParams = new ViewGroup.LayoutParams((int) width / column,
                        ViewGroup.LayoutParams.MATCH_PARENT);

                int index = i * column + j;

                int viewType = View.VISIBLE;

                if (index >= list.size()) {
                    viewType = View.GONE;

                } else {

                    if (onClickListener != null) {
                        convertView.setOnClickListener(onClickListener);
                        convertView.setTag(index);
                    }

                }

                listener.setSubItemListener(convertView, index, columnLayoutParams, viewType);


                linearLayout.addView(convertView);

            }


            mainLinearLayout.addView(linearLayout);
        }

    }

    public interface FullyGridLinearLayoutListener {
        void setSubItemListener(View convertView, int position,
                                ViewGroup.LayoutParams columnLayoutParams, int viewType);
    }
}
