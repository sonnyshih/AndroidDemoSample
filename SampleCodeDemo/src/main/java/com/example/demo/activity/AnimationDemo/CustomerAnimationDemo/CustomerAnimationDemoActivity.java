
package com.example.demo.activity.AnimationDemo.CustomerAnimationDemo;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.demo.R;

public class CustomerAnimationDemoActivity extends AppCompatActivity implements OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_animation_demo_activity);

        Button slideUpButton = (Button) findViewById(R.id.linearAnimationDemo_slideUpButton);
        slideUpButton.setOnClickListener(this);

        Button SlideDownButton = (Button) findViewById(R.id.linearAnimationDemo_SlideDownButton);
        SlideDownButton.setOnClickListener(this);

    }


    private void onSlideUpButtonClick(){
        final View linearLayout = (LinearLayout) findViewById(R.id.linearAnimationDemo_demoLayout);

        int height = linearLayout.getHeight();

        Log.d("Mylog", "height="+height);

        Animation animSlideUp = new SlideDownUpAnimation(linearLayout, height, 0);
        animSlideUp.setDuration(300);

        animSlideUp.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.d("Mylog", "Slide Up onAnimationStart");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.d("Mylog", "Slide Up onAnimationEnd");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Log.d("Mylog", "Slide Up onAnimationRepeat");
            }
        });


        // Start the Animation
        linearLayout.startAnimation(animSlideUp);

    }

    private void onSlideDownButtonClick(){
        final View linearLayout = (LinearLayout) findViewById(R.id.linearAnimationDemo_demoLayout);

        int height = 200;

        Animation animSlideDown = new SlideDownUpAnimation(linearLayout, 0, height);
        animSlideDown.setDuration(300);

        animSlideDown.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.d("Mylog", "Slide Down onAnimationStart");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.d("Mylog", "Slide Down onAnimationEnd");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Log.d("Mylog", "Slide Down onAnimationRepeat");
            }
        });

        // Start the Animation
        linearLayout.startAnimation(animSlideDown);

    }


    @Override
    public void onClick(View view) {
            switch (view.getId()) {

                case R.id.linearAnimationDemo_slideUpButton:
                    onSlideUpButtonClick();
                    break;

                case R.id.linearAnimationDemo_SlideDownButton:
                    onSlideDownButtonClick();
                    break;

                default:
                    break;
            }
    }
}
