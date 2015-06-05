package com.example.CustomerUIDemo.activity.AnimationDemo;

import com.example.CustomerUIDemo.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class AnmationDemoActivity extends Activity implements OnClickListener{

	private TextView textView;
	private Button rotationButton;
	
	private RelativeLayout relativeLayout;
	private RelativeLayout imageLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.anmation_demo_activity);
		textView = (TextView)findViewById(R.id.anmationDemo_textView);
		textView.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anmation_demo_anmation));
		
		rotationButton = (Button) findViewById(R.id.anmationDemo_rotationButton);
		rotationButton.setOnClickListener(this);
		
		relativeLayout = (RelativeLayout) findViewById(R.id.anmationDemo_relativeLayout);
		imageLayout = (RelativeLayout) findViewById(R.id.anmationDemo_imageLayout);

		
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
	}

	private void onRotateImageLayoutClick(){
		MainCountDown countdown = new MainCountDown(3000, 3000);
		countdown.start();
	}
	
	public class MainCountDown extends CountDownTimer {

		public MainCountDown(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}

		@Override
		public void onTick(long millisUntilFinished) {
		}

		@Override
		public void onFinish() {
			applyRotation(0, 180);
		}

	}
	
	
	private void applyRotation(float start, float end) {
		final float centerX = relativeLayout.getWidth() / 2.0f;
		final float centerY = relativeLayout.getHeight() / 2.0f;
		final Rotate3dAnimation rotation = new Rotate3dAnimation(start, end,
				centerX, centerY, 310.0f, true);
		rotation.setDuration(700);
		rotation.setFillAfter(true);
		rotation.setInterpolator(new AccelerateInterpolator());
		rotation.setAnimationListener(new DisplayNextView());
		relativeLayout.startAnimation(rotation);
	}

	private final class DisplayNextView implements AnimationListener {

		@Override
		public void onAnimationStart(Animation animation) {

		}

		@Override
		public void onAnimationEnd(Animation animation) {
			relativeLayout.post(new SwapViews());
		}

		@Override
		public void onAnimationRepeat(Animation animation) {

		}

	}

	private final class SwapViews implements Runnable {
		public void run() {
			final float centerX = relativeLayout.getWidth() / 2.0f;
			final float centerY = relativeLayout.getHeight() / 2.0f;
			Rotate3dAnimation rotation;
			imageLayout.setVisibility(View.GONE);
			rotation = new Rotate3dAnimation(0, 180, centerX, centerY, 310.0f,
					false);
			rotation.setDuration(700);
			rotation.setFillAfter(true);
			rotation.setInterpolator(new DecelerateInterpolator());
			relativeLayout.startAnimation(rotation);

//			Intent intent = new Intent(StartActivity.this, MainActivity.class);
//			startActivity(intent);
//			finish();
		}
	}

	
	
	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.anmationDemo_rotationButton:
			onRotateImageLayoutClick();
			break;

		default:
			break;
		}
	}

	
}
