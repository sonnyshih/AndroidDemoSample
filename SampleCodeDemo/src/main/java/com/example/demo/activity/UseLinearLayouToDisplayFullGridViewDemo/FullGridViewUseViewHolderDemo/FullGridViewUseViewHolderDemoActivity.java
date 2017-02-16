package com.example.demo.activity.UseLinearLayouToDisplayFullGridViewDemo.FullGridViewUseViewHolderDemo;


import android.app.Activity;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import com.example.demo.R;
import com.example.demo.activity.UseLinearLayouToDisplayFullGridViewDemo.entity.Data;

import java.util.ArrayList;
import java.util.List;

public class FullGridViewUseViewHolderDemoActivity extends AppCompatActivity {

    private LinearLayout mainLinear;
    private List<Data> list;
    private List <ItemViewHolderInfo> viewHolderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_grid_view_use_view_holder_activity);

        mainLinear = (LinearLayout) findViewById(R.id.fullGridViewUseViewHolder_mainLinear);

        list = generateList();
        viewHolderList = generateViewHolderInfoList(list);
        layoutFullyGridLinearLayout(viewHolderList);
        getWidth();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Waiting for completing to rotate.
        mainLinear.postDelayed(new Runnable() {
            @Override
            public void run() {
                getWidth();
                layoutFullyGridLinearLayout(viewHolderList);
            }
        }, 100);
    }

    private void getWidth(){
        ViewTreeObserver vto = mainLinear.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                Log.d("Mylog", "mainLinear.getWidth()="+mainLinear.getWidth());
//                layoutView(mainLinearLayout.getWidth());

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    mainLinear.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    mainLinear.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }

            }
        });
    }

    private void layoutFullyGridLinearLayout(List<ItemViewHolderInfo> list){
        int column = Integer.valueOf(getResources().getString(R.string.column_number));

        MyAdapter myAdapter = new MyAdapter(this, mainLinear, list, column);
        myAdapter.startLayout();

    }


    private ArrayList<Data> generateList() {

        ArrayList<Data> list = new ArrayList<>();

        for (int i = 0; i < 17; i++) {
            Data data = new Data();
            data.setType((int )(Math.random() * 2 + 1));
            data.setTitle("Title " + i);
            data.setDescription("Description " + i);
            list.add(data);
        }

        return list;
    }

    private List<ItemViewHolderInfo> generateViewHolderInfoList (List<Data> list){
        List<ItemViewHolderInfo> viewHolderList = new ArrayList<>();

        for (Data data: list) {
            ItemViewHolderInfo itemViewHolderInfo = new ItemViewHolderInfo(data);
            viewHolderList.add(itemViewHolderInfo);
        }

        return viewHolderList;
    }
}
