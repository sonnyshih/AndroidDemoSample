package com.example.ThreadAndAsyncTaskDemo.AsyncTaskDemo;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

public class ImageLoaderAsyncTask extends AsyncTask<String, Integer, Bitmap>{
	private ImageView imageView;
	
	public ImageLoaderAsyncTask(ImageView imageView){
		this.imageView = imageView;
	}
	
	@Override
	protected Bitmap doInBackground(String... params) {
		String urlString = params[0];		
		HttpGet httpRequest = new HttpGet(urlString);

		HttpClient httpClient = new DefaultHttpClient();
		InputStream inputStream = null;
		
		try {
			HttpResponse httpResponse = httpClient.execute(httpRequest);
			StatusLine statusLine = httpResponse.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			
			if (statusCode == HttpURLConnection.HTTP_OK) {

				HttpEntity entity = httpResponse.getEntity();
				inputStream = entity.getContent();
				Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
				
				return bitmap;
			}
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream!=null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return null;
	}
	
	@Override
	protected void onPostExecute(Bitmap bitmap) {
		super.onPostExecute(bitmap);
		imageView.setImageBitmap(bitmap);
	}

	
}
