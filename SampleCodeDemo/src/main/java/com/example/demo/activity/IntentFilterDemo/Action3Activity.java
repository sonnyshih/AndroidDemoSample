
package com.example.demo.activity.IntentFilterDemo;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Action3Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(layoutParams);

        TextView helloTextView = new TextView(this);
        helloTextView.setText("Click the login in the web page");
        linearLayout.addView(helloTextView);

        TextView uriTextView = new TextView(this);
        linearLayout.addView(uriTextView);

        TextView searialNumberTextview = new TextView(this);
        linearLayout.addView(searialNumberTextview);

        TextView typeTextView = new TextView(this);
        linearLayout.addView(typeTextView);

        setContentView(linearLayout);


        Intent intent = getIntent();
        if (Intent.ACTION_VIEW.equals(intent.getAction())) {
            Uri uri = intent.getData();
            uriTextView.setText("uri = " + uri.toString());
            searialNumberTextview.setText("searialNumber = " + uri.getQueryParameter("searialNumber"));
            typeTextView.setText("type = " + uri.getQueryParameter("type"));
        }

    }
}
