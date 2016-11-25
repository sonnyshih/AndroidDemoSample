
package com.example.demo.activity.RecycleViewDemo.RecycleViewItemAnimatorDemo;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.demo.R;
import com.example.demo.activity.RecycleViewDemo.RecycleViewItemAnimatorDemo.RecycleViewCustomerAnimatorDemo.RecycleViewItemCustomerAnimatorDemoActivity;
import com.example.demo.activity.RecycleViewDemo.RecycleViewItemAnimatorDemo.RecycleViewDefaultAnimatorDemo.RecycleViewItemDefaultAnimatorDemoActivity;

public class RecycleViewItemAnimatorDemoActivity extends AppCompatActivity
    implements OnClickListener{

    private Button defaultAnimatorButton;
    private Button customerAnimatorButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycleview_item_animator_demo);

        defaultAnimatorButton = (Button) findViewById(R.id.recycleViewItemAnimatorDemo_defaultAnimatorButton);
        defaultAnimatorButton.setOnClickListener(this);

        customerAnimatorButton = (Button) findViewById(R.id.recycleViewItemAnimatorDemo_customerAnimatorButton);
        customerAnimatorButton.setOnClickListener(this);

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

            default:
                break;
        }

        startActivity(intent);
    }

}
