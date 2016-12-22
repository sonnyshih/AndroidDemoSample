package com.example.demo.activity.AnimationDemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.demo.R;
import com.example.demo.activity.AnimationDemo.TextViewAnimationDemo.TextViewAnimationDemoActivity;
import com.example.demo.activity.AnimationDemo.LinearAnimationDemo.LinearAnimationDemoActivity;
import com.example.demo.activity.AnimationDemo.RotationDemo.RotationAnimationDemoActivity;

public class AnimationDemoActivity extends AppCompatActivity
        implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation_demo_activity);

        Button rotationAnimationDemoButton = (Button) findViewById(R.id.animationDemo_rotationAnimationDemoButton);
        rotationAnimationDemoButton.setOnClickListener(this);

        Button textViewAnimationDemoButton = (Button) findViewById(R.id.animationDemo_textViewAnimationDemoButton);
        textViewAnimationDemoButton.setOnClickListener(this);

        Button linearLayoutAnimationDemoButton = (Button) findViewById(R.id.animationDemo_linearLayoutAnimationDemoButton);
        linearLayoutAnimationDemoButton.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        Intent intent = new Intent();

        switch (view.getId()) {
            case R.id.animationDemo_rotationAnimationDemoButton:
                intent.setClass(this, RotationAnimationDemoActivity.class);
                break;

            case R.id.animationDemo_textViewAnimationDemoButton:
                intent.setClass(this, TextViewAnimationDemoActivity.class);
                break;

            case R.id.animationDemo_linearLayoutAnimationDemoButton:
                intent.setClass(this, LinearAnimationDemoActivity.class);
                break;

            default:
                break;
        }

        startActivity(intent);
    }
}
