package com.example.demo.activity.UseLinearLayouToDisplayFullGridViewDemo.CustomLinearLayoutDemo;


import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

public class CustomGridViewLinearLayout extends LinearLayout {
    private LinearLayout linearLayout;
    private Adapter adapter;
    private int spanCount = 1;


    public CustomGridViewLinearLayout(Context context) {
        super(context);
    }

    public CustomGridViewLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomGridViewLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setSpanCount(int spanCount){
        if (spanCount == this.spanCount) {
            return;
        }

        this.spanCount = spanCount;

        if (adapter != null){
            startLayout();
        }

    }

    public void setAdapter(Adapter adapter) {
        this.adapter = adapter;
        startLayout();
    }

    private void startLayout() {
        this.linearLayout = this;
        this.removeAllViews();

        ViewTreeObserver vto = linearLayout.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                layoutView(linearLayout.getWidth());

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    linearLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    linearLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }

            }
        });

    }

    public void layoutView(double width) {
        int itemCount = adapter.getItemCount();

        int row = 0;

        int remainder = itemCount % spanCount;

        if (remainder > 0) {
            row = itemCount / spanCount + 1;
        } else {
            row = itemCount / spanCount;
        }

        for (int i = 0; i < row; i++) {

            LinearLayout.LayoutParams rowLayoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            LinearLayout linearLayout = new LinearLayout(this.getContext());
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout.setLayoutParams(rowLayoutParams);

            for (int j = 0; j < spanCount; j++) {

                ViewGroup.LayoutParams columnLayoutParams = new ViewGroup.LayoutParams((int) width / spanCount,
                        ViewGroup.LayoutParams.MATCH_PARENT);

                int index = i * spanCount + j;

                if (index < itemCount) {
                    ViewHolder viewHolder = adapter.createViewHolder(this, adapter.getItemViewType(index));
                    viewHolder.itemView.setLayoutParams(columnLayoutParams);
                    linearLayout.addView(viewHolder.itemView);

                    adapter.onBindViewHolder(viewHolder, index);
                }

            }

            this.addView(linearLayout);
        }

    }

}
