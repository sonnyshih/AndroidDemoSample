
package com.example.demo.activity.IntentFilterDemo;


import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ActionActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView text=new TextView(this);
        text.setText("the Intent Filter of Activity has set an Action");
        setContentView(text);
    }
}
