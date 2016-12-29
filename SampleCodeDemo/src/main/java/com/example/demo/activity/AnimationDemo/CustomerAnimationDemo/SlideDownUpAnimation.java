package com.example.demo.activity.AnimationDemo.CustomerAnimationDemo;


import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class SlideDownUpAnimation extends Animation {

    int fromHeight;
    int toHeight;
    View view;

    public SlideDownUpAnimation(View view, int fromHeight, int toHeight) {
        this.view = view;
        this.fromHeight = fromHeight;
        this.toHeight = toHeight;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation transformation) {
        int newHeight;

        if (view.getHeight() != toHeight) {
            newHeight = (int) (fromHeight + ((toHeight - fromHeight) * interpolatedTime));
            view.getLayoutParams().height = newHeight;
            view.requestLayout();
        }
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
    }

    @Override
    public boolean willChangeBounds() {
        return true;
    }

}

