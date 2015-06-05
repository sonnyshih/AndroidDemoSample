package com.example.ThreadAndAsyncTaskDemo.AsyncTaskDemo;

import java.io.FileNotFoundException;
import java.io.OutputStream;

import com.example.ThreadAndAsyncTaskDemo.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class AsyncTaskDemoActivity extends Activity implements OnClickListener {
	private Button httpURLConnectionGetButton;
	private Button httpURLConnectionPostButton;

	private Button httpGetButton;
	private Button httpPostButton;

	private ImageLoaderAsyncTask imageLoaderAsyncTask;
	private ImageView imageView;
	private Button loadImageButton;
	private Button cancelButton;

	private ImageLoaderToFileAsyncTask imageLoaderToFileAsyncTask;
	private ImageView fileImageView;
	private Button fileLoadImageButton;
	private Button fileCancelButton;
	private ProgressBar progressBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.async_task_demo_activity);

		httpURLConnectionGetButton = (Button) findViewById(R.id.async_task_demo_httpURLConnectionGetButton);
		httpURLConnectionGetButton.setOnClickListener(this);
		httpURLConnectionPostButton = (Button) findViewById(R.id.async_task_demo_httpURLConnectionPostButton);
		httpURLConnectionPostButton.setOnClickListener(this);

		httpGetButton = (Button) findViewById(R.id.async_task_demo_httpGetButton);
		httpGetButton.setOnClickListener(this);
		httpPostButton = (Button) findViewById(R.id.async_task_demo_httpPostButton);
		httpPostButton.setOnClickListener(this);

		imageView = (ImageView) findViewById(R.id.async_task_demo_imageView);
		loadImageButton = (Button) findViewById(R.id.async_task_demo_loadImageButton);
		loadImageButton.setOnClickListener(this);
		cancelButton = (Button) findViewById(R.id.async_task_demo_cancelButton);
		cancelButton.setOnClickListener(this);

		fileImageView = (ImageView) findViewById(R.id.async_task_demo_fileImageView);
		fileLoadImageButton = (Button) findViewById(R.id.async_task_demo_fileLoadImageButton);
		fileLoadImageButton.setOnClickListener(this);
		fileCancelButton = (Button) findViewById(R.id.async_task_demo_fileCancelButton);
		fileCancelButton.setOnClickListener(this);
		progressBar = (ProgressBar) findViewById(R.id.async_task_demo_progressBar);

	}

	
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
	}


	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.async_task_demo_httpURLConnectionGetButton:
			onHttpURLConnectionGetClick();
			break;

		case R.id.async_task_demo_httpURLConnectionPostButton:
			onHttpURLConnectionPostClick();
			break;

		case R.id.async_task_demo_httpGetButton:
			onGetClick();
			break;

		case R.id.async_task_demo_httpPostButton:
			onPostClick();
			break;

		case R.id.async_task_demo_loadImageButton:
			onLoadImageClick();
			break;

		case R.id.async_task_demo_cancelButton:
			imageLoaderAsyncTask.cancel(true);
			break;

		case R.id.async_task_demo_fileLoadImageButton:
			onimageLoaderToFileClick();
			break;

		case R.id.async_task_demo_fileCancelButton:
			imageLoaderToFileAsyncTask.cancel(true);
			break;

		default:
			break;
		}

	}

	private void onHttpURLConnectionGetClick() {

		String url = "http://www.ows.newegg.com/Stores.egg/ShopAllNavigation";

		HttpURLConnectionGetAsyncTask httpURLConnectionGetAsyncTask = new HttpURLConnectionGetAsyncTask();
		httpURLConnectionGetAsyncTask.execute(url);
	}

	private void onHttpURLConnectionPostClick() {

		String url = "http://202.153.189.188/user/login";
		String inputJSON = "{ \"user\": \"herdyboy\",  \"password\": \"123456\", \"device_id\":\"0900000015\"}";

		HttpURLConnectionPostAsyncTask httpURLConnectionPostAsyncTask = new HttpURLConnectionPostAsyncTask();
		httpURLConnectionPostAsyncTask.execute(url, inputJSON);

	}

	private void onGetClick() {
		String url = "http://www.ows.newegg.com/Stores.egg/ShopAllNavigation";

		GetAsyncTask getAsyncTask = new GetAsyncTask();
		getAsyncTask.execute(url);
	}

	private void onPostClick() {
		String url = "http://202.153.189.188/user/login";
		String inputJSON = "{ \"user\": \"herdyboy\",  \"password\": \"123456\", \"device_id\":\"0900000015\"}";

		PostAsyncTask postAsyncTask = new PostAsyncTask();
		postAsyncTask.execute(url, inputJSON);

	}

	private void onLoadImageClick() {
		String url = "http://developer.android.com/images/home/android-jellybean.png";
		imageLoaderAsyncTask = new ImageLoaderAsyncTask(imageView);
		imageLoaderAsyncTask.execute(url);
	}

	private void onimageLoaderToFileClick() {
		
		progressBar.setProgress(0);
		fileImageView.setImageDrawable(getResources().getDrawable(
				R.drawable.ic_launcher));
		
		String url = "http://developer.android.com/images/home/android-jellybean.png";
		
		final String filename = "local_temp_image";
		OutputStream outputStream = null;
		try {
			// the file will be created in /data/data/packageName/files/
			outputStream = openFileOutput(filename, Context.MODE_PRIVATE);	
			
			String path = getFileStreamPath(filename).getAbsolutePath();
			
			imageLoaderToFileAsyncTask = new ImageLoaderToFileAsyncTask(
					fileImageView, progressBar, path, outputStream);
			imageLoaderToFileAsyncTask.execute(url);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

}
