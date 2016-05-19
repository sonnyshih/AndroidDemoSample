package com.example.ThreadAndAsyncTaskDemo.AsyncTaskDemo;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpURLConnectionGetAsyncTask extends AsyncTask<String, Integer, String>{

	@Override
	protected String doInBackground(String... params) {
		
		String outputString="";
		String urlString = params[0];
		
		URL url;

		try {
			url = new URL(urlString);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url
					.openConnection();

			httpURLConnection.setRequestMethod("GET");
			httpURLConnection.setRequestProperty("User-Agent", "Android App / 3.2.1");

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
		
		Log.d("Mylog", "Get - outputJSON = " + result);

	}
	
}
