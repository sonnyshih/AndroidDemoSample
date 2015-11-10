package com.example.CustomerUIDemo.activity.IntentFilterDemo;


import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.CustomerUIDemo.R;

public class IntentTest2Activity extends Activity{
    private String localURL = "file:///android_res/raw/test1.html";
//    private String localURL = "file:///android_asset/test.html";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent_test2_activity);

        layoutWebView(localURL);
    }

    private void layoutWebView(String localURL) {
        WebView helpPageContentWebView = (WebView) findViewById(R.id.intentTest2_WebView);
        // Disable focus cursor
        helpPageContentWebView.setFocusable(false);
        helpPageContentWebView.setFocusableInTouchMode(false);
        helpPageContentWebView.loadUrl(localURL);
    }

}
