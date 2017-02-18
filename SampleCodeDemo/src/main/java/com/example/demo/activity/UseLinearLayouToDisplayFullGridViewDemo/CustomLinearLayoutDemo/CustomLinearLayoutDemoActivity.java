package com.example.demo.activity.UseLinearLayouToDisplayFullGridViewDemo.CustomLinearLayoutDemo;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.demo.R;
import com.example.demo.activity.UseLinearLayouToDisplayFullGridViewDemo.entity.Data;

import java.util.ArrayList;

public class CustomLinearLayoutDemoActivity extends AppCompatActivity{
    private CustomGridViewLinearLayout linearLayout;
    private ArrayList<Data> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_linear_layout_demo_activity);

        list = generateList();
        linearLayout = (CustomGridViewLinearLayout) findViewById(R.id.customerLinearLayoutDemo_mainLinear);

        layoutFullyGridLinearLayout();

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Waiting for completing to rotate.
        linearLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                layoutFullyGridLinearLayout();
            }
        }, 100);
    }

    private void layoutFullyGridLinearLayout(){
        int column = Integer.valueOf(getResources().getString(R.string.column_number));
        CustomAdapter customAdapter = new CustomAdapter(list);
        linearLayout.setSpanCount(column);
        linearLayout.setAdapter(customAdapter);
    }

    private ArrayList<Data> generateList() {

        ArrayList<Data> list = new ArrayList<>();

        for (int i = 0; i < 31; i++) {
            Data data = new Data();
            data.setType((int )(Math.random() * 2 + 1));
            data.setTitle("Title " + i);
            data.setDescription("Description " + i);
            list.add(data);
        }

        return list;
    }
}
