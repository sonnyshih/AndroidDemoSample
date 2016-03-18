package com.example.demo.activity.WebViewDemo;

import com.example.demo.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

public class WebViewDemoActivity extends Activity{
	private WebView webView;
	private ProgressBar progress;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.web_view_demo_activity);

		progress = (ProgressBar) findViewById(R.id.web_view_demo_progress);
		
		setWebView();
	}
	
	@Override
	protected void onDestroy() {
		if (webView != null) {
			webView.loadUrl("about:blank");
			webView.freeMemory();
			webView.destroy();
			webView = null;
		}
		
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (webView != null) {
			webView.onPause();
			webView.pauseTimers();
		}
		
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (webView != null) {
			webView.onResume();
			webView.resumeTimers();
		}
	}


	
	@SuppressLint("SetJavaScriptEnabled")
	private void setWebView(){
		
		String url = "http://tw.yahoo.com";
//		String url = "file:///android_asset/example.html"; //the html files is in asset
//		String url = "http://promotions.newegg.com/nepro/14-1062/index.html?icid=240075";
		
		webView = (WebView) findViewById(R.id.web_view_demo_webview);
		
		webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
		webView.setScrollbarFadingEnabled(false);
		webView.setInitialScale(1);
		
		WebSettings settings = webView.getSettings();
		settings.setJavaScriptEnabled(true);		// Enable to run Javascript
		
		// set width of web page is same as webview
		settings.setLoadWithOverviewMode(true);		
		settings.setUseWideViewPort(true);
		
		//set the webview can be zoom in/out
		settings.setSupportZoom(true);
		settings.setBuiltInZoomControls(true);
		
		int sdkVersion = android.os.Build.VERSION.SDK_INT;
		if (sdkVersion > 10) {
			settings.setDisplayZoomControls(true);	// Display zoom in/out tool
		}

		webView.setWebViewClient(webViewClient); 		// set the default browser
		webView.setWebChromeClient(webChromeClient);	// Display the tile in the title of Activity 
		webView.loadUrl(url);

	}
	
	WebViewClient webViewClient = new WebViewClient() {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			
			Toast.makeText(view.getContext(), "Click url is "+url, Toast.LENGTH_LONG).show();
			
			view.loadUrl(url);	//load the url which is clicked
			return true;
		}

		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {

			//run the javascript
			String js = "function doLoad() {"
					+ "HideLayer('cntr_us_header');"
					+ "HideLayer('cntr_footer');" + "}";
			js += "function HideLayer(layerid){"
					+ "var layer = document.getElementById(layerid);"
					+ "if(layer != null){" + "layer.style.display= 'none';"
					+ "}}";

			js = js + "if ( window.addEventListener ) { "
					+ "window.addEventListener( 'load', doLoad, false );"
					+ "} else if ( window.attachEvent ) { "
					+ "window.attachEvent( 'onload', doLoad );"
					+ " } else if ( window.onLoad ) {"
					+ "window.onload = doLoad;}";

			view.loadUrl("javascript:" + js + ";");
			
			super.onPageStarted(view, url, favicon);
		}
		
		
	};

	WebChromeClient webChromeClient = new WebChromeClient() {

		@Override
		public void onProgressChanged(WebView view, int newProgress) {
			super.onProgressChanged(view, newProgress);
			
			// show the progress bar when loading the web page
			progress.setProgress(newProgress);
			if (newProgress == 100) {
				progress.setVisibility(View.GONE);
			}

		}

		@Override
		public void onReceivedTitle(WebView view, String title) {
			if ((title != null) && (title.trim().length() != 0)) {
				setTitle(title);
			}
		}
	};

}
