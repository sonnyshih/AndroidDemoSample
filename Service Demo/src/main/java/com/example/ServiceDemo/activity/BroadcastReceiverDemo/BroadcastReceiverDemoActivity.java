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
			
			// ���w�ե� component ���եΤ覡
			Intent intent1 = new Intent(this, SimpleReceiver.class);
			intent1.putExtra("msg", "This is a broadcast invoked by component.");
			sendBroadcast(intent1);
			break;
			
		case R.id.broadcastReceiverDemo_setManifextFileButton:
			/**
			 * ### AndroidManifest.xml �]�w ###
			 * �b  Intent() �� �� "com.example.broadcastreceiverdemo.action.rec"�A
			 * �n�P <receiver> ���� <intent-filter>�̪� 
			 * <action android:name="com.example.broadcastreceiverdemo.action.rec" /> �ۦP
			 * */
			// �ϥ� Action ���եΤ覡
			Intent intent2 = new Intent("com.example.broadcastreceiverdemo.action.rec");
			intent2.putExtra("msg","This is a broadcast invoked by Action defined in .XML files.");
			sendBroadcast(intent2);
			break;
			
		case R.id.broadcastReceiverDemo_useRegisterReceiverButton:
			
			/**
			 * registerReceiver() ��k�N�Y�� BroadcastReceiver ���U��Y��  IntentFilter �� ���A
			 * ���^�� Intent ��ܲĤ@�Ӻ��� IntentFilter ����  IntentFilter
			 * */
			// �ϥε��U�եΤ覡
			IntentFilter filter = new IntentFilter(SPECIAL_INTENT_FILTER);
			SimpleReceiver simpleReceiver = new SimpleReceiver();
			registerReceiver(simpleReceiver, filter);	//���U�s��������
			
			Intent intent3 = new Intent(SPECIAL_INTENT_FILTER);
			intent3.putExtra("msg","This is a broadcast invoked by a user-defined IntentFilter");
			sendBroadcast(intent3);
			
			break;
			
		default:
			break;
		}
	}
}
