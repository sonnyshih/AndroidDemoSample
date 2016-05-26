package com.example.ThreadAndAsyncTaskDemo.AsyncTaskDemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageLoaderToFileAsyncTask extends
		AsyncTask<String, Integer, Bitmap> {

	private String path;
	private ImageView imageView;
	private ProgressBar progressBar;
	private OutputStream outputStream;

	public ImageLoaderToFileAsyncTask(ImageView imageView,
			ProgressBar progressBar, String path, OutputStream outputStream) {
		this.imageView = imageView;
		this.progressBar = progressBar;
		this.outputStream = outputStream;
		this.path = path;
	}

	@Override
	protected Bitmap doInBackground(String... params) {

		URL url = null;
		HttpURLConnection httpURLConnection = null;
		InputStream inputStream = null;

		try {

			try {
				url = new URL(params[0]);
				httpURLConnection = (HttpURLConnection) url.openConnection();
				httpURLConnection.setDoInput(true);
				httpURLConnection.setDoOutput(false);
				httpURLConnection.setConnectTimeout(20 * 1000);

				switch (httpURLConnection.getResponseCode()) {

					case HttpURLConnection.HTTP_OK:
						inputStream = httpURLConnection.getInputStream();
						break;

					// Redirect URL
					case HttpURLConnection.HTTP_MOVED_PERM:
					case HttpURLConnection.HTTP_MOVED_TEMP:
						String newUrl = httpURLConnection.getHeaderField("Location");
						url = new URL(newUrl);
						httpURLConnection = (HttpURLConnection) url.openConnection();
						httpURLConnection.setDoInput(true);
						httpURLConnection.setDoOutput(false);
						httpURLConnection.setConnectTimeout(20 * 1000);
						inputStream = httpURLConnection.getInputStream();

						break;

					default:
						break;

				}

				if (inputStream != null) {
					byte[] buf = new byte[8196];
					int seg = 0;
					final long total = httpURLConnection.getContentLength();
					long current = 0;
					while (!isCancelled() && (seg = inputStream.read(buf)) != -1) {
						outputStream.write(buf, 0, seg);
						current += seg;
						int progress = (int) ((float) current / (float) total * 100f);
						publishProgress(progress);
						SystemClock.sleep(1000);
					}
				}


			} finally {
				if (httpURLConnection != null) {
					httpURLConnection.disconnect();
				}
				if (inputStream != null) {
					inputStream.close();
				}
				if (outputStream != null) {
					outputStream.close();
				}
			}

			return BitmapFactory.decodeFile(path);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// String urlString = params[0];
		// HttpGet httpRequest = new HttpGet(urlString);
		//
		// HttpClient httpClient = new DefaultHttpClient();
		// InputStream inputStream = null;
		// OutputStream out = null;
		// final String filename = "local_temp_image";
		//
		// try {
		// HttpResponse httpResponse = httpClient.execute(httpRequest);
		// StatusLine statusLine = httpResponse.getStatusLine();
		// int statusCode = statusLine.getStatusCode();
		//
		// if (statusCode == HttpURLConnection.HTTP_OK) {
		//
		// HttpEntity entity = httpResponse.getEntity();
		// inputStream = entity.getContent();
		//
		// ByteArrayOutputStream content = new ByteArrayOutputStream();
		//
		// // Read response into a buffered stream
		// int readBytes = 0;
		// byte[] sBuffer = new byte[512];
		// while ((readBytes = inputStream.read(sBuffer)) != -1) {
		// content.write(sBuffer, 0, readBytes);
		// }
		//
		// // inputStream = entity.getContent();
		// // Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
		// //
		// // return bitmap;
		// }
		//
		// } catch (ClientProtocolException e) {
		// e.printStackTrace();
		// } catch (IOException e) {
		// e.printStackTrace();
		// } finally {
		// if (inputStream!=null) {
		// try {
		// inputStream.close();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// }
		// }

		return null;
	}

	@Override
	protected void onProgressUpdate(Integer... progress) {
		super.onProgressUpdate(progress);
		Log.d("Mylog", "progress="+progress[0]);
		progressBar.setProgress(progress[0]);
	}

	@Override
	protected void onPostExecute(Bitmap bitmap) {
		super.onPostExecute(bitmap);

		imageView.setImageBitmap(bitmap);
	}

}
