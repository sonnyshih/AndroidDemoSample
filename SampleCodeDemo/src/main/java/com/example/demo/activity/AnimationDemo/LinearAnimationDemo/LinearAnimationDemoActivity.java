package com.example.demo.activity.AnimationDemo.LinearAnimationDemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.demo.R;

public class LinearAnimationDemoActivity extends AppCompatActivity implements OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_animation_demo_activity);

        Button showButton = (Button) findViewById(R.id.linearAnimationDemo_showButton);
        showButton.setOnClickListener(this);

        Button hideButton = (Button) findViewById(R.id.linearAnimationDemo_hideButton);
        hideButton.setOnClickListener(this);

    }

    private void onShowButtonClick(){
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearAnimationDemo_demoLayout);

        Animation animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.animation_examples_fade_in);

        // Start the Animation
        linearLayout.startAnimation(animFadeIn);

    }

    private void onHideButtonClick(){
        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearAnimationDemo_demoLayout);

        Animation animFadeOut = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.animation_examples_fade_out);

        animFadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.d("Mylog", "FadeOut onAnimationStart");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.d("Mylog", "FadeOut onAnimationEnd");

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Log.d("Mylog", "FadeOut onAnimationRepeat");
            }
        });


        // Start the Animation
        linearLayout.startAnimation(animFadeOut);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linearAnimationDemo_showButton:
                onShowButtonClick();
                break;

            case R.id.linearAnimationDemo_hideButton:
                onHideButtonClick();
                break;

            default:
                break;
        }
    }
}
