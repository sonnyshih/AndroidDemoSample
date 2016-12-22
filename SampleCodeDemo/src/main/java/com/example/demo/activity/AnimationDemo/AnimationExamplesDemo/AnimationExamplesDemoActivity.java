/*
* Refer to: http://www.journaldev.com/9481/android-animation-example
* */
package com.example.demo.activity.AnimationDemo.AnimationExamplesDemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.example.demo.R;

public class AnimationExamplesDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation_examples_demo_activity);

        fadeIn();
        fadeOut();
        crossFade();
        blink();
        zoomIn();
        zoomOut();
        rotate();
        move();
        slideUp();
        slideDown();
        bounce();
        sequential();
        together();


    }

    // fade in
    private void fadeIn() {
        final TextView txtFadeIn = (TextView) findViewById(R.id.txt_fade_in);
        final Animation animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.animation_examples_fade_in);

        Button btnFadeIn = (Button) findViewById(R.id.btnFadeIn);
        btnFadeIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                txtFadeIn.setVisibility(View.VISIBLE);
                txtFadeIn.startAnimation(animFadeIn);
            }
        });
    }

    // fade out
    private void fadeOut() {
        final TextView txtFadeOut = (TextView) findViewById(R.id.txt_fade_out);
        final Animation animFadeOut = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.animation_examples_fade_out);

        Button btnFadeOut = (Button) findViewById(R.id.btnFadeOut);
        btnFadeOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtFadeOut.setVisibility(View.VISIBLE);
                txtFadeOut.startAnimation(animFadeOut);
            }
        });
    }

    // cross fade
    private void crossFade() {
        final TextView txtOut = (TextView) findViewById(R.id.txt_out);
        final TextView txtIn = (TextView) findViewById(R.id.txt_in);

        final Animation animCrossFadeIn = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.animation_examples_fade_in);
        final Animation animCrossFadeOut = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.animation_examples_fade_out);

        Button btnCrossFade = (Button) findViewById(R.id.btnCrossFade);
        btnCrossFade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtOut.setVisibility(View.VISIBLE);
                // start fade in animation
                txtOut.startAnimation(animCrossFadeIn);

                // start fade out animation
                txtIn.startAnimation(animCrossFadeOut);
            }
        });
    }

    // blink
    private void blink() {
        final TextView txtBlink = (TextView) findViewById(R.id.txt_blink);
        final Animation animBlink = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.animation_examples_blink);

        Button btnBlink = (Button) findViewById(R.id.btnBlink);
        btnBlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtBlink.setVisibility(View.VISIBLE);
                txtBlink.startAnimation(animBlink);
            }
        });
    }

    // Zoom In
    private void zoomIn() {
        final TextView txtZoomIn = (TextView) findViewById(R.id.txt_zoom_in);
        final Animation animZoomIn = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.animation_examples_zoom_in);

        Button btnZoomIn = (Button) findViewById(R.id.btnZoomIn);
        btnZoomIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtZoomIn.setVisibility(View.VISIBLE);
                txtZoomIn.startAnimation(animZoomIn);
            }
        });
    }

    // Zoom Out
    private void zoomOut(){

        final TextView txtZoomOut = (TextView) findViewById(R.id.txt_zoom_out);
        final Animation animZoomOut = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.animation_examples_zoom_out);

        Button btnZoomOut = (Button) findViewById(R.id.btnZoomOut);
        btnZoomOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtZoomOut.setVisibility(View.VISIBLE);
                txtZoomOut.startAnimation(animZoomOut);
            }
        });
    }

    // Rotate
    private void rotate() {
        final TextView txtRotate = (TextView) findViewById(R.id.txt_rotate);
        final Animation animRotate = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.animation_examples_rotate);

        Button  btnRotate = (Button) findViewById(R.id.btnRotate);
        btnRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtRotate.startAnimation(animRotate);
            }
        });
    }

    // Move
    private void move(){
        final TextView txtMove = (TextView) findViewById(R.id.txt_move);
        final Animation animMove = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.animation_examples_move);

        Button btnMove = (Button) findViewById(R.id.btnMove);
        btnMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtMove.startAnimation(animMove);
            }
        });

    }

    // Slide Up
    private void slideUp(){
        final TextView txtSlideUp = (TextView) findViewById(R.id.txt_slide_up);
        final Animation animSlideUp = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.animation_examples_slide_up);

        Button btnSlideUp = (Button) findViewById(R.id.btnSlideUp);
        btnSlideUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtSlideUp.startAnimation(animSlideUp);
            }
        });

    }

    // Slide Down
    private void slideDown(){
        final TextView txtSlideDown = (TextView) findViewById(R.id.txt_slide_down);
        final Animation animSlideDown = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.animation_examples_slide_down);

        Button btnSlideDown = (Button) findViewById(R.id.btnSlideDown);
        btnSlideDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtSlideDown.startAnimation(animSlideDown);
            }
        });

    }

    // bounce
    private void bounce(){
        final TextView txtBounce = (TextView) findViewById(R.id.txt_bounce);
        final Animation animBounce = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.animation_examples_bounce);

        Button btnBounce = (Button) findViewById(R.id.btnBounce);
        btnBounce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtBounce.startAnimation(animBounce);
            }
        });

    }

    // Sequential
    private void sequential(){
        final TextView txtSeq = (TextView) findViewById(R.id.txt_seq);
        final Animation animSequential = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.animation_examples_sequential);

        Button btnSequential = (Button) findViewById(R.id.btnSequential);
        btnSequential.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txtSeq.startAnimation(animSequential);
            }
        });

    }

    // Together
    private void together(){
        final TextView txtTog = (TextView) findViewById(R.id.txt_tog);
        final Animation animTogether = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.animation_examples_together);

        Button btnTogether = (Button) findViewById(R.id.btnTogether);
        btnTogether.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtTog.startAnimation(animTogether);
            }
        });
    }
}
