package com.example.ThreadAndAsyncTaskDemo;

import java.lang.reflect.Type;
import java.util.List;

import com.example.ThreadAndAsyncTaskDemo.AsyncTaskDemo.AsyncTaskDemoActivity;
import com.example.ThreadAndAsyncTaskDemo.ThreadDemo.ThreadDemoIndexActivity;
import com.example.ThreadAndAsyncTaskDemo.entity.FolderInfoEntity;
import com.example.ThreadAndAsyncTaskDemo.entity.ShopAllNavigationEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity implements OnClickListener {
	private Button asyncTaskDemoButton;
	private Button threadDemoButton;
	
	private Button translateDemo1Button;
	private Button translateDemo2Button;
	
	private Button base64Button;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		
		asyncTaskDemoButton = (Button) findViewById(R.id.main_asyncTaskDemoButton);
		asyncTaskDemoButton.setOnClickListener(this);
		
		threadDemoButton = (Button) findViewById(R.id.main_threadDemoButton);
		threadDemoButton.setOnClickListener(this);
		
		translateDemo1Button = (Button) findViewById(R.id.async_task_demo_translateDemo1Button);
		translateDemo1Button.setOnClickListener(this);
		
		translateDemo2Button = (Button) findViewById(R.id.async_task_demo_translateDemo2Button);
		translateDemo2Button.setOnClickListener(this);
		
		base64Button = (Button) findViewById(R.id.async_task_demo_base64Button);
		base64Button.setOnClickListener(this);
		
	}


	@Override
	public void onClick(View view) {
		 Intent intent = new Intent();

		switch (view.getId()) {
		case R.id.main_asyncTaskDemoButton:
			 intent = new Intent();
			 intent.setClass(this, AsyncTaskDemoActivity.class);
			 startActivity(intent);
			
			break;

		case R.id.main_threadDemoButton:
			 intent = new Intent();
			 intent.setClass(this, ThreadDemoIndexActivity.class);
			 startActivity(intent);
			
			break;

		case R.id.async_task_demo_translateDemo1Button:
			onTranslateDemo1Click();
			break;
						
		case R.id.async_task_demo_translateDemo2Button:
			onTranslateDemo2Click();
			break;
					
		case R.id.async_task_demo_base64Button:
			onBase64Click();
			break;
					
						
		default:
			break;
		}
	}

	private void onTranslateDemo1Click(){
		String jsonString = getString(R.string.browse);
		
		Type entityType = new TypeToken<List<ShopAllNavigationEntity>>() {}.getType();
		
		Gson gson = new Gson();
		
		List<ShopAllNavigationEntity> list = gson.fromJson(jsonString, entityType);
		
		Log.d("Mylog", "list="+list.get(0).getDescription());
		
		
	}

	private void onTranslateDemo2Click(){
		String jsonString = getString(R.string.file_info);

		Type entityType = new TypeToken<FolderInfoEntity>() {}.getType();
		
		Gson gson = new Gson();

		FolderInfoEntity folderInfoEntity = gson.fromJson(jsonString, entityType);
		
		Log.d("Mylog", "Folder Info="+folderInfoEntity.getFolders().get(0).getName());
		
	}

	private void onBase64Click(){
		String user ="test@test.com";
		String token = "3a9496ecdfe622577dc61ced089ed12c3fed7604191a6354ee240cfedf164435";
		String OrignAuth = user + ":" + token;
		String auth = Base64.encodeToString(OrignAuth.getBytes(), Base64.NO_WRAP);
		Log.d("Mylog", "auth="+auth);

	}
	
}
