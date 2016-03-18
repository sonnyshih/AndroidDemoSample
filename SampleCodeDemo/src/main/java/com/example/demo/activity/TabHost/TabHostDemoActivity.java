package com.example.demo.activity.TabHost;

import com.example.demo.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TabHostDemoActivity extends Activity implements OnClickListener{

	private Button tabhostAndViewpagerDemoButton;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabhost_demo_activity);
		tabhostAndViewpagerDemoButton = (Button) findViewById(R.id.tabHostDemo_tabhostAndViewpagerDemoButton);
		tabhostAndViewpagerDemoButton.setOnClickListener(this);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
	}

	@Override
	public void onClick(View view) {
		
		Intent intent = new Intent();
		 
		switch (view.getId()) {
		case R.id.tabHostDemo_tabhostAndViewpagerDemoButton:
			intent.setClass(this, TabhostAndViewpagerDemoActivity.class);
			startActivity(intent);

			break;

		default:
			break;
		}
	}

	
}
