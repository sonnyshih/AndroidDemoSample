package com.example.ThreadAndAsyncTaskDemo.AsyncTaskDemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.util.Log;

public class PostAsyncTask extends AsyncTask<String, Integer, String>{

	@Override
	protected String doInBackground(String... params) {
		
		StringBuilder builder = new StringBuilder();
		String outputString="";
		String urlString = params[0];
		String inputJSON = params[1];

		StringEntity se = null;
		try {
			se = new StringEntity(inputJSON);
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		HttpPost httpRequest = new HttpPost(urlString);
		httpRequest.setEntity(se);

		httpRequest.addHeader("User-Agent", "Newegg Android App / 3.2.1");
		httpRequest.addHeader("Accept", "application/json");
		httpRequest.addHeader("Accept-Charset", "utf-8");
		
		HttpClient httpClient = new DefaultHttpClient();
		InputStream inputStream = null;

		try {
			HttpResponse httpResponse = httpClient.execute(httpRequest);
			StatusLine statusLine = httpResponse.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			
			if (statusCode == HttpURLConnection.HTTP_OK) {

				HttpEntity entity = httpResponse.getEntity();
				inputStream = entity.getContent();
				BufferedReader reader = new BufferedReader(	new InputStreamReader(inputStream));
				
				String line;
				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}
				
				outputString = builder.toString();
			}//End if

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}					
		}//End try catch
		
		return outputString;
	}

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		
		Log.d("Mylog", "Post - outputJSON = " + result);

	}
	
}
