package com.example.ThreadAndAsyncTaskDemo.ThreadDemo;

import com.example.ThreadAndAsyncTaskDemo.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class BasicThreadDemo extends Activity implements OnClickListener{

	private Button showDemo1Button;
	
	private Button showDemo2Button;

	private TextView demo3TextView;
	private Button startThread1Button;
	private Button startThread2Button;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.basic_thread_demo_activity);
		
		showDemo1Button = (Button) findViewById(R.id.basic_thread_demo_showDemo1Button);
		showDemo1Button.setOnClickListener(this);
		
		showDemo2Button = (Button) findViewById(R.id.basic_thread_demo_showDemo2Button);
		showDemo2Button.setOnClickListener(this);
		
		demo3TextView = (TextView) findViewById(R.id.basic_thread_demo_demo3TextView);

		startThread1Button = (Button) findViewById(R.id.basic_thread_demo_startThread1Button);
		startThread1Button.setOnClickListener(this);
		
		startThread2Button = (Button) findViewById(R.id.basic_thread_demo_startThread2Button);
		startThread2Button.setOnClickListener(this);
		

	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.basic_thread_demo_showDemo1Button:
			onShowDemo1Click();
			break;

		case R.id.basic_thread_demo_showDemo2Button:
			onShowDemo2Click();
			break;

		case R.id.basic_thread_demo_startThread1Button:
			onStartThread1Click();
			break;

		case R.id.basic_thread_demo_startThread2Button:
			onStartThread2Click();
			break;

		default:
			break;
		}
	}

	private void onShowDemo1Click(){
		
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				int i = 0;
				
				while (i<5) {
					try {
						Log.d("Mylog", "count = " +i);
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					i++;
				}
			}
		});
		
		thread.start();
	}
	
	
	/* ####################### */
	/* ##### Show Demo 2 ##### */ 
	/* ####################### */
	private Handler handler = new Handler();
	
	private void onShowDemo2Click(){
		Demo2Thread demo2Thread = new Demo2Thread();
		handler.post(demo2Thread);
//		handler.postDelayed(demo2Thread, 1000);		// delay 10 seconds
	}
	
	public class Demo2Thread extends Thread{

		@Override
		public void run() {
			int i = 0;
			while (i<5) {
				try {
					Log.d("Mylog", "count = " +i);
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				i++;
			}
		}
	}

	/* ########################### */
	/* ##### Show Demo 2 End ##### */ 
	/* ########################### */

	
	/* ####################### */
	/* ##### Show Demo 3 ##### */ 
	/* ####################### */
	private void onStartThread1Click(){
		Thread thread1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				Message message = new Message();
				message.what = 1;
				demo3Handler.sendMessage(message);
			}
		});
		
		demo3Handler.post(thread1);
	}
	
	private void onStartThread2Click(){
		Thread thread2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				Message message = new Message();
				message.what = 2;
				demo3Handler.sendMessage(message);
			}
		});
		
		demo3Handler.post(thread2);
	}
	
	Handler demo3Handler =  new Handler(){

		@Override
		public void handleMessage(Message message) {
			super.handleMessage(message);
			
			switch (message.what) {
			case 1:
				demo3TextView.setText("From Thread 1");
				break;

			case 2:
				demo3TextView.setText("From Thread 2");
				break;

			default:
				break;
			}
		}
		
	};
	
	
	/* ########################### */
	/* ##### Show Demo 3 End ##### */ 
	/* ########################### */

	
}
