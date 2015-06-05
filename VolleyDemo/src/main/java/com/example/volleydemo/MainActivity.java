package com.example.volleydemo;

import com.example.volleydemo.R;
import com.example.volleydemo.CustomerGsonRequest.CustomerGsonRequestActivity;
import com.example.volleydemo.ImageLoader.ImageLoaderActivity;
import com.example.volleydemo.JsonOjbectRequest.JsonObjectRequestActivity;
import com.example.volleydemo.SimpleRequest.ShowSimpleRequest;
import com.example.volleydemo.SingletonPattern.UseSingletonPattern;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{
	private Button showSimpleRequestButton;
	private Button singletonPatternButton;
	private Button imageLoaderButton;
	private Button jsonObjectRequestButton;
	private Button customerRequestButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		
		showSimpleRequestButton = (Button) findViewById(R.id.main_showSimpleRequestButton);
		showSimpleRequestButton.setOnClickListener(this);

		singletonPatternButton = (Button) findViewById(R.id.main_singletonPatternButton);
		singletonPatternButton.setOnClickListener(this);
		
		imageLoaderButton = (Button) findViewById(R.id.main_imageLoaderButton);
		imageLoaderButton.setOnClickListener(this);
		
		jsonObjectRequestButton = (Button) findViewById(R.id.main_jsonObjectRequestButton);
		jsonObjectRequestButton.setOnClickListener(this);
		
		customerRequestButton = (Button) findViewById(R.id.main_customerGsonRequestButton);
		customerRequestButton.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View view) {
		
		Intent intent = new Intent();
		
		switch (view.getId()) {
		case R.id.main_showSimpleRequestButton:
			intent.setClass(this, ShowSimpleRequest.class);
			startActivity(intent);

			break;

		case R.id.main_singletonPatternButton:
			intent.setClass(this, UseSingletonPattern.class);
			startActivity(intent);

			break;

		case R.id.main_imageLoaderButton:
			intent.setClass(this, ImageLoaderActivity.class);
			startActivity(intent);

			break;

		case R.id.main_jsonObjectRequestButton:
			intent.setClass(this, JsonObjectRequestActivity.class);
			startActivity(intent);

			break;
			
		case R.id.main_customerGsonRequestButton:
			intent.setClass(this, CustomerGsonRequestActivity.class);
			startActivity(intent);

			break;
			
			
			
		default:
			break;
		}
	}

}
