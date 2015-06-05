package com.example.ThreadAndAsyncTaskDemo.ThreadDemo;

import com.example.ThreadAndAsyncTaskDemo.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ThreadDemoIndexActivity extends Activity implements OnClickListener{

	private Button basicThreadDemoButton;
	private Button newFixedThreadPoolDemoButton;
	private Button threadPoolExecutorDemoButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.thread_demo_index_activity);
	
		basicThreadDemoButton = (Button) findViewById(R.id.thread_demo_basicThreadDemoButton);
		basicThreadDemoButton.setOnClickListener(this);
		
		newFixedThreadPoolDemoButton = (Button) findViewById(R.id.thread_demo_newFixedThreadPoolDemoButton);
		newFixedThreadPoolDemoButton.setOnClickListener(this);
		
		threadPoolExecutorDemoButton = (Button) findViewById(R.id.thread_demo_threadPoolExecutorDemoButton);
		threadPoolExecutorDemoButton.setOnClickListener(this);
		
		
	}

	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
	}


	@Override
	public void onClick(View view) {
		Intent intent;
		
		switch (view.getId()) {
		case R.id.thread_demo_basicThreadDemoButton:
			intent = new Intent();
			intent.setClass(this, BasicThreadDemo.class);
			startActivity(intent);
			
			break;

		case R.id.thread_demo_newFixedThreadPoolDemoButton:
			intent = new Intent();
			intent.setClass(this, NewFixedThreadPoolDemoActivity.class);
			startActivity(intent);
			
			break;

		case R.id.thread_demo_threadPoolExecutorDemoButton:
			intent = new Intent();
			intent.setClass(this, ThreadPoolExecutorDemoActivity.class);
			startActivity(intent);
			
			break;

		default:
			break;
		}
	}
	
	

}
