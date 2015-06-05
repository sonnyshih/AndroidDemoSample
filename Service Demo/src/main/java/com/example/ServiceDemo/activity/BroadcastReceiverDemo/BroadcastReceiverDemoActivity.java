package com.example.ServiceDemo.activity.BroadcastReceiverDemo;

import com.example.ServiceDemo.R;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class BroadcastReceiverDemoActivity extends Activity implements OnClickListener {

	private static final String SPECIAL_INTENT_FILTER = "A user-defined action.";
	private Button useComponentButton;
	private Button setManifextFileButton;
	private Button useRegisterReceiverButton;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.broadcast_receiver_demo_activity);
		
		useComponentButton = (Button) findViewById(R.id.broadcastReceiverDemo_useComponentButton);
		useComponentButton.setOnClickListener(this);
		
		setManifextFileButton = (Button) findViewById(R.id.broadcastReceiverDemo_setManifextFileButton);
		setManifextFileButton.setOnClickListener(this);
		
		useRegisterReceiverButton = (Button) findViewById(R.id.broadcastReceiverDemo_useRegisterReceiverButton);
		useRegisterReceiverButton.setOnClickListener(this);

	}

	@Override
	public void onClick(View view) {
		
		switch (view.getId()) {
		
		case R.id.broadcastReceiverDemo_useComponentButton:
			
			// 指定組件 component 的調用方式
			Intent intent1 = new Intent(this, SimpleReceiver.class);
			intent1.putExtra("msg", "This is a broadcast invoked by component.");
			sendBroadcast(intent1);
			break;
			
		case R.id.broadcastReceiverDemo_setManifextFileButton:
			/**
			 * ### AndroidManifest.xml 設定 ###
			 * 在  Intent() 中 的 "com.example.broadcastreceiverdemo.action.rec"，
			 * 要與 <receiver> 中的 <intent-filter>裡的 
			 * <action android:name="com.example.broadcastreceiverdemo.action.rec" /> 相同
			 * */
			// 使用 Action 的調用方式
			Intent intent2 = new Intent("com.example.broadcastreceiverdemo.action.rec");
			intent2.putExtra("msg","This is a broadcast invoked by Action defined in .XML files.");
			sendBroadcast(intent2);
			break;
			
		case R.id.broadcastReceiverDemo_useRegisterReceiverButton:
			
			/**
			 * registerReceiver() 方法將某個 BroadcastReceiver 註冊到某個  IntentFilter 之 中，
			 * 其返回值 Intent 表示第一個滿足 IntentFilter 條件的  IntentFilter
			 * */
			// 使用註冊調用方式
			IntentFilter filter = new IntentFilter(SPECIAL_INTENT_FILTER);
			SimpleReceiver simpleReceiver = new SimpleReceiver();
			registerReceiver(simpleReceiver, filter);	//註冊廣播接收器
			
			Intent intent3 = new Intent(SPECIAL_INTENT_FILTER);
			intent3.putExtra("msg","This is a broadcast invoked by a user-defined IntentFilter");
			sendBroadcast(intent3);
			
			break;
			
		default:
			break;
		}
	}
}
