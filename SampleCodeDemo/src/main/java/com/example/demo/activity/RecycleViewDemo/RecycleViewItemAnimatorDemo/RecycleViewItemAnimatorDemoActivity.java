
package com.example.demo.activity.RecycleViewDemo.RecycleViewItemAnimatorDemo;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.demo.R;
import com.example.demo.activity.RecycleViewDemo.RecycleViewItemAnimatorDemo.ListViewIncludeRecycleViewAnimatorDemo.ListViewIncludeRecycleViewAnimatorActivity;
import com.example.demo.activity.RecycleViewDemo.RecycleViewItemAnimatorDemo.RecycleViewCustomerAnimatorDemo.RecycleViewItemCustomerAnimatorDemoActivity;
import com.example.demo.activity.RecycleViewDemo.RecycleViewItemAnimatorDemo.RecycleViewDefaultAnimatorDemo.RecycleViewItemDefaultAnimatorDemoActivity;
import com.example.demo.activity.RecycleViewDemo.RecycleViewItemAnimatorDemo.RecycleViewIncludeRecycelViewAnimatorDemo.RecycleViewIncludeRecycleViewAnimatorActivity;

public class RecycleViewItemAnimatorDemoActivity extends AppCompatActivity
    implements OnClickListener{

    private Button defaultAnimatorButton;
    private Button customerAnimatorButton;
    private Button listViewIncludeRecycleViewAnimatorButton;
    private Button recycleViewIncludeRecycleViewAnimatorButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycleview_item_animator_demo);

        defaultAnimatorButton = (Button) findViewById(R.id.recycleViewItemAnimatorDemo_defaultAnimatorButton);
        defaultAnimatorButton.setOnClickListener(this);

        customerAnimatorButton = (Button) findViewById(R.id.recycleViewItemAnimatorDemo_customerAnimatorButton);
        customerAnimatorButton.setOnClickListener(this);

        listViewIncludeRecycleViewAnimatorButton = (Button) findViewById(R.id.recycleViewItemAnimatorDemo_listViewIncludeRecycleViewAnimatorButton);
        listViewIncludeRecycleViewAnimatorButton.setOnClickListener(this);

        recycleViewIncludeRecycleViewAnimatorButton = (Button) findViewById(R.id.recycleViewItemAnimatorDemo_recycleViewIncludeRecycleViewAnimatorButton);
        recycleViewIncludeRecycleViewAnimatorButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();

        switch (view.getId()) {
            case R.id.recycleViewItemAnimatorDemo_defaultAnimatorButton:
                intent.setClass(this, RecycleViewItemDefaultAnimatorDemoActivity.class);
                break;

            case R.id.recycleViewItemAnimatorDemo_customerAnimatorButton:
                intent.setClass(this, RecycleViewItemCustomerAnimatorDemoActivity.class);
                break;

            case R.id.recycleViewItemAnimatorDemo_listViewIncludeRecycleViewAnimatorButton:
                intent.setClass(this, ListViewIncludeRecycleViewAnimatorActivity.class);
                break;

            case R.id.recycleViewItemAnimatorDemo_recycleViewIncludeRecycleViewAnimatorButton:
                intent.setClass(this, RecycleViewIncludeRecycleViewAnimatorActivity.class);
                break;

            default:
                break;
        }

        startActivity(intent);
    }

}
