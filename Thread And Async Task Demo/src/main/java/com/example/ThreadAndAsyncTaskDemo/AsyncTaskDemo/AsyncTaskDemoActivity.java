package com.example.ThreadAndAsyncTaskDemo.AsyncTaskDemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.ThreadAndAsyncTaskDemo.R;

import java.io.FileNotFoundException;
import java.io.OutputStream;

public class AsyncTaskDemoActivity extends Activity implements OnClickListener {

	private ImageLoaderAsyncTask imageLoaderAsyncTask;
	private ImageView imageView;

	private ImageLoaderToFileAsyncTask imageLoaderToFileAsyncTask;
	private ImageView fileImageView;
	private ProgressBar progressBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.async_task_demo_activity);

		Button httpURLConnectionGetButton = (Button) findViewById(R.id.async_task_demo_httpURLConnectionGetButton);
		httpURLConnectionGetButton.setOnClickListener(this);
		Button httpURLConnectionPostButton = (Button) findViewById(R.id.async_task_demo_httpURLConnectionPostButton);
		httpURLConnectionPostButton.setOnClickListener(this);

		Button httpGetButton = (Button) findViewById(R.id.async_task_demo_httpGetButton);
		httpGetButton.setOnClickListener(this);
		Button httpPostButton = (Button) findViewById(R.id.async_task_demo_httpPostButton);
		httpPostButton.setOnClickListener(this);

		imageView = (ImageView) findViewById(R.id.async_task_demo_imageView);
		Button loadImageButton = (Button) findViewById(R.id.async_task_demo_loadImageButton);
		loadImageButton.setOnClickListener(this);
		Button cancelButton = (Button) findViewById(R.id.async_task_demo_cancelButton);
		cancelButton.setOnClickListener(this);

		fileImageView = (ImageView) findViewById(R.id.async_task_demo_fileImageView);
		Button fileLoadImageButton = (Button) findViewById(R.id.async_task_demo_fileLoadImageButton);
		fileLoadImageButton.setOnClickListener(this);
		Button fileCancelButton = (Button) findViewById(R.id.async_task_demo_fileCancelButton);
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

		String url = "http://175.98.136.168:4080/Stores.egg/ShopAllNavigation";

		HttpURLConnectionGetAsyncTask httpURLConnectionGetAsyncTask = new HttpURLConnectionGetAsyncTask();
		httpURLConnectionGetAsyncTask.execute(url);
	}

	private void onHttpURLConnectionPostClick() {

		String url = "http://175.98.136.168:4000/user/login";
		String inputJSON = "{ \"user\": \"herdyboy\",  \"password\": \"123456\", \"device_id\":\"0900000015\"}";

		HttpURLConnectionPostAsyncTask httpURLConnectionPostAsyncTask = new HttpURLConnectionPostAsyncTask();
		httpURLConnectionPostAsyncTask.execute(url, inputJSON);

	}

	private void onGetClick() {
		String url = "http://175.98.136.168:4080/Stores.egg/ShopAllNavigation";

		GetAsyncTask getAsyncTask = new GetAsyncTask();
		getAsyncTask.execute(url);
	}

	private void onPostClick() {
		String url = "http://175.98.136.168:4000/user/login";
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
