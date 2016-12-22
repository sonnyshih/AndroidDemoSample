package com.example.demo.activity.AnimationDemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.demo.R;
import com.example.demo.activity.AnimationDemo.AnimationExamplesDemo.AnimationExamplesDemoActivity;
import com.example.demo.activity.AnimationDemo.RotationDemo.RotationAnimationDemoActivity;

public class AnimationDemoActivity extends AppCompatActivity
        implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation_demo_activity);

        Button rotationAnimationDemoButton = (Button) findViewById(R.id.animationDemo_rotationAnimationDemoButton);
        rotationAnimationDemoButton.setOnClickListener(this);

        Button animationExamplesDemo = (Button) findViewById(R.id.animationDemo_animationExamplesDemoButton);
        animationExamplesDemo.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        Intent intent = new Intent();

        switch (view.getId()) {
            case R.id.animationDemo_rotationAnimationDemoButton:
                intent.setClass(this, RotationAnimationDemoActivity.class);
                break;

            case R.id.animationDemo_animationExamplesDemoButton:
                intent.setClass(this, AnimationExamplesDemoActivity.class);
                break;

            default:
                break;
        }

        startActivity(intent);
    }
}
