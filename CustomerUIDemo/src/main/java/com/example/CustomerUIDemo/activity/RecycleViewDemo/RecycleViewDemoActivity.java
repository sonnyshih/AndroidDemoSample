package com.example.CustomerUIDemo.activity.RecycleViewDemo;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.CustomerUIDemo.R;

public class RecycleViewDemoActivity extends Activity implements OnClickListener{

    private Button linearRecycleViewButton;
    private Button gridRecycleViewButton;
    private Button listViewIncludeRecycleViewButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycleview_demo);

        linearRecycleViewButton = (Button) findViewById(R.id.recycleviewDemo_linearRecycleViewButton);
        linearRecycleViewButton.setOnClickListener(this);

        gridRecycleViewButton = (Button) findViewById(R.id.recycleviewDemo_gridRecycleViewButton);
        gridRecycleViewButton.setOnClickListener(this);

        listViewIncludeRecycleViewButton = (Button) findViewById(R.id.recycleviewDemo_listViewIncludeRecycleViewButton);
        listViewIncludeRecycleViewButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.recycleviewDemo_linearRecycleViewButton:
                intent.setClass(this, LinearLayoutRecycleViewActivity.class);
                break;

            case R.id.recycleviewDemo_gridRecycleViewButton:
                intent.setClass(this, GridLayoutRecycleViewActivity.class);
                break;

            case R.id.recycleviewDemo_listViewIncludeRecycleViewButton:
                intent.setClass(this, ListViewIncludeRecycleViewActivity.class);
                break;

            default:
                break;
        }

        startActivity(intent);
    }
}
