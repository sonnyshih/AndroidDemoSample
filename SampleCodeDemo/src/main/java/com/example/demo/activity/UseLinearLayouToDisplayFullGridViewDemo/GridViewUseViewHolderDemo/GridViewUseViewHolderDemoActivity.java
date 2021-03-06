package com.example.demo.activity.UseLinearLayouToDisplayFullGridViewDemo.GridViewUseViewHolderDemo;


import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewTreeObserver;

import com.example.demo.R;
import com.example.demo.activity.UseLinearLayouToDisplayFullGridViewDemo.CustomLinearLayoutDemo.CustomGridViewLinearLayout;
import com.example.demo.activity.UseLinearLayouToDisplayFullGridViewDemo.entity.Data;

import java.util.ArrayList;
import java.util.List;

public class GridViewUseViewHolderDemoActivity extends AppCompatActivity {

    private CustomGridViewLinearLayout linearLayout;
    private List<Data> list;
    private List <ViewHolderInfo> viewHolderInfoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_view_use_view_holder_activity);

        linearLayout = (CustomGridViewLinearLayout) findViewById(R.id.gridViewUseViewHolder_mainLinear);
        list = generateList();
        viewHolderInfoList = generateViewHolderInfoList(list);
        layoutGridLinearLayout();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Waiting for completing to rotate.
        linearLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                getWidth();
                setLayoutSpanCount();
            }
        }, 100);
    }

    private void getWidth(){
        ViewTreeObserver vto = linearLayout.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                Log.d("Mylog", "mainLinear.getWidth()="+linearLayout.getWidth());

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    linearLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    linearLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }

            }
        });
    }

    private void setLayoutSpanCount(){
        int column = Integer.valueOf(getResources().getString(R.string.column_number));
        linearLayout.setSpanCount(column);
    }

    private void layoutGridLinearLayout(){
        setLayoutSpanCount();
        MyAdapter myAdapter = new MyAdapter();
        myAdapter.addAll(viewHolderInfoList);
        linearLayout.setAdapter(myAdapter);
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

    private List<ViewHolderInfo> generateViewHolderInfoList (List<Data> list){
        List<ViewHolderInfo> viewHolderList = new ArrayList<>();

        for (Data data: list) {
            ItemViewHolderInfo itemViewHolderInfo = new ItemViewHolderInfo(data);
            viewHolderList.add(itemViewHolderInfo);
        }

        return viewHolderList;
    }
}
