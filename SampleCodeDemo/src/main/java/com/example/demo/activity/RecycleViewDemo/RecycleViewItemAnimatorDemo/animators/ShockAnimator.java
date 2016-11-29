package com.example.demo.activity.RecycleViewDemo.RecycleViewItemAnimatorDemo.animators;


import android.app.Activity;
import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.OvershootInterpolator;

public class ShockAnimator extends BaseItemAnimator {

    private Context mContext;
    private int width;
    private int height;

    public ShockAnimator(Context context) {
        this.mContext = context;
        DisplayMetrics metric = new DisplayMetrics();
        ((Activity)mContext).getWindowManager().getDefaultDisplay().getMetrics(metric);
        width = px2dp(metric.widthPixels);     // 屏幕宽度（像素）
        height = px2dp(metric.heightPixels);   // 屏幕高度（像素）
    }

    @Override
    protected void animateRemoveImpl(final RecyclerView.ViewHolder holder) {
        final View view = holder.itemView;
        int position = (int) view.getTag();
        final ViewPropertyAnimatorCompat animation = ViewCompat.animate(view);
        float endX ,endY;
        if (position % 2 == 0) {
            endY = -100;
            endX = -width;
        } else {
            endY =  100;
            endX = width;
        }
        mRemoveAnimations.add(holder);
        animation.setInterpolator(new AnticipateOvershootInterpolator());
        animation.setDuration(getRemoveDuration())
                .alpha(0).translationX(endX).translationY(endY).setListener(new VpaListenerAdapter() {
            @Override
            public void onAnimationStart(View view) {
                dispatchRemoveStarting(holder);
            }

            @Override
            public void onAnimationEnd(View view) {
                animation.setListener(null);
                //重置为正常
                ViewCompat.setAlpha(view, 1);
                ViewCompat.setTranslationX(view, 0);
                ViewCompat.setTranslationY(view, 0);
                dispatchRemoveFinished(holder);
                mRemoveAnimations.remove(holder);
                dispatchFinishedWhenDone();
            }
        }).start();
    }

    @Override
    protected void animateAddImpl(final RecyclerView.ViewHolder holder) {
        final View view = holder.itemView;
        final ViewPropertyAnimatorCompat animation = ViewCompat.animate(view);
        mAddAnimations.add(holder);
        float startX ,startY;

        int position = (int) view.getTag();

        if (position % 2 == 0) {
            startY = - 100;
            startX = -width;
        } else {
            startY =  100;
            startX = width;
        }
//        Log.d("test", String.valueOf(holder.getLayoutPosition()));
        ViewCompat.setTranslationX(view, startX);
        //应该是相对偏移
        ViewCompat.setTranslationY(view, startY);
        ViewCompat.setAlpha(view, 0);
        animation.setInterpolator(new OvershootInterpolator());
        animation.alpha(1)
                .translationX(0)
                .translationY(0)
                .setDuration(getAddDuration()).
                setListener(new VpaListenerAdapter() {
                    @Override
                    public void onAnimationStart(View view) {
                        dispatchAddStarting(holder);
                    }

                    @Override
                    public void onAnimationCancel(View view) {
                        ViewCompat.setAlpha(view, 1);
                    }

                    @Override
                    public void onAnimationEnd(View view) {
                        animation.setListener(null);
                        dispatchAddFinished(holder);
                        mAddAnimations.remove(holder);
                        dispatchFinishedWhenDone();
                        //使用这个来复原
                        ViewCompat.setTranslationY(view, 0);
                    }
                }).start();
    }

    /**
     * Check the state of currently pending and running animations. If there are none
     * pending/running, call {@link #dispatchAnimationsFinished()} to notify any
     * listeners.
     */
    private void dispatchFinishedWhenDone() {
        if (!isRunning()) {
            dispatchAnimationsFinished();
        }
    }

    private static class VpaListenerAdapter implements ViewPropertyAnimatorListener {
        @Override
        public void onAnimationStart(View view) {}

        @Override
        public void onAnimationEnd(View view) {}

        @Override
        public void onAnimationCancel(View view) {}
    }

    public int px2dp(float pxValue){
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int)(pxValue/scale+0.5f);
    }
}
