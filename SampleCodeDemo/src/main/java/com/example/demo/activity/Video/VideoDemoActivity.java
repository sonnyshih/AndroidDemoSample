package com.example.demo.activity.Video;

import com.example.demo.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class VideoDemoActivity  extends Activity implements OnClickListener{

	private Button videoViewDemoButton;
	private Button mediaPlayerDemoButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.video_demo_activity);
		
		videoViewDemoButton = (Button) findViewById(R.id.video_videoViewDemoButton);
		videoViewDemoButton.setOnClickListener(this);
		
		mediaPlayerDemoButton = (Button) findViewById(R.id.video_mediaPlayerDemoButton);
		mediaPlayerDemoButton.setOnClickListener(this);
		
		
	}
	
	@Override
	public void onClick(View view) {
		
		Intent intent = new Intent();
		
		switch (view.getId()) {
		case R.id.video_videoViewDemoButton:
			intent.setClass(this, VideoViewActivity.class);
			break;

		case R.id.video_mediaPlayerDemoButton:
			intent.setClass(this, VideoMediaPlayerActivity.class);
			break;
			
		default:
			break;
		}
		
		startActivity(intent);
	}

}
