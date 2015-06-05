package com.example.fragmentdemo;

import com.example.fragmentdemo.DynamicDemo.DynamicFragmentActivity;
import com.example.fragmentdemo.FragmentPagerDemo.FragmentPagerActivity;
import com.example.fragmentdemo.StaticDemo.StaticFragmentActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{
	private Button dynamicButton;
	private Button staticButton;
	private Button fragmentPagerButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		
		dynamicButton = (Button) findViewById(R.id.main_dynamicButton);
		dynamicButton.setOnClickListener(this);
		
		staticButton = (Button) findViewById(R.id.main_staticButton);
		staticButton.setOnClickListener(this);
		
		fragmentPagerButton = (Button) findViewById(R.id.main_fragmentPagerButton);
		fragmentPagerButton.setOnClickListener(this);

	}


	@Override
	public void onClick(View view) {
		
		Intent intent = new Intent();
		
		switch (view.getId()) {
		case R.id.main_dynamicButton:
			intent.setClass(this, DynamicFragmentActivity.class);
			startActivity(intent);

			break;

		case R.id.main_staticButton:
			intent.setClass(this, StaticFragmentActivity.class);
			startActivity(intent);

			break;

		case R.id.main_fragmentPagerButton:
			intent.setClass(this, FragmentPagerActivity.class);
			startActivity(intent);

			break;

		default:
			break;
		}
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
//		return true;
//	}
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// Handle action bar item clicks here. The action bar will
//		// automatically handle clicks on the Home/Up button, so long
//		// as you specify a parent activity in AndroidManifest.xml.
//		int id = item.getItemId();
//		if (id == R.id.action_settings) {
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}
}
