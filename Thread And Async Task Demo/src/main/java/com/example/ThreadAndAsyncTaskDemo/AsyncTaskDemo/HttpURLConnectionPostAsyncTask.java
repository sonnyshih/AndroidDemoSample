package com.example.ThreadAndAsyncTaskDemo.AsyncTaskDemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.os.AsyncTask;
import android.util.Log;

public class HttpURLConnectionPostAsyncTask extends AsyncTask<String, Integer, String>{

	@Override
	protected String doInBackground(String... params) {
		
		String outputString="";
		String urlString = params[0];
		String inputJSON = params[1];
		
		URL url;

		try {
			url = new URL(urlString);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url
					.openConnection();

			httpURLConnection.setDoOutput(true);
			httpURLConnection.setDoInput(true);
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setRequestProperty("neweggbox-server-version", "20140625");

			OutputStream outputStream = httpURLConnection.getOutputStream();
			byte[] outputBytes = inputJSON.getBytes("UTF-8");
			outputStream.write(outputBytes);
			outputStream.flush();
			outputStream.close();
			
			Log.d("Mylog", "httpURLConnection.getResponseCode()="+httpURLConnection.getResponseCode());
			StringBuilder builder = new StringBuilder();
			if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(
								httpURLConnection.getInputStream()));

				String line;
				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}

				outputString = builder.toString();
			}

			httpURLConnection.disconnect();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
			
		}

		return outputString;
	}

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		
		Log.d("Mylog", "Post - outputJSON = " + result);

	}
	
}
