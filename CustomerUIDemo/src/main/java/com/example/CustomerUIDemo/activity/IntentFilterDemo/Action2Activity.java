
package com.example.CustomerUIDemo.activity.IntentFilterDemo;


import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Action2Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView text=new TextView(this);
        text.setText("From the web page");
        setContentView(text);
    }
}
