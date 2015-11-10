
package com.example.CustomerUIDemo.activity.IntentFilterDemo;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.CustomerUIDemo.R;

public class IntentFilterDemoActivity extends Activity implements OnClickListener {

    private Button intentTest1Demo;
    private Button intentTest2Demo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent_filter_demo_activity);

        intentTest1Demo = (Button) findViewById(R.id.intentFilterDemo_intentTest1Demo);
        intentTest1Demo.setOnClickListener(this);

        intentTest2Demo = (Button) findViewById(R.id.intentFilterDemo_intentTest2Demo);
        intentTest2Demo.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.intentFilterDemo_intentTest1Demo:
                startActivity(new Intent(this, IntentTest1Activity.class));
                break;

            case R.id.intentFilterDemo_intentTest2Demo:
                startActivity(new Intent(this, IntentTest2Activity.class));
                break;

            default:
                break;
        }
    }
}
