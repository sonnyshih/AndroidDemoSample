
package com.example.demo.activity.IntentFilterDemo;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.demo.R;

public class IntentFilterDemoActivity extends Activity implements OnClickListener {

    public static final String BUNDL_KEY_SEARIAL_NUMBER = "BUNDL_KEY_SEARIAL_NUMBER";
    public static final String BUNDLE_SERILIZABLE_MESSAGE = "BUNDLE_SERILIZABLE_MESSAGE";

    private Button intentTest1Demo;
    private Button intentTest2Demo;
    private Button intentTest3Demo;
    private Button intentTest4Demo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent_filter_demo_activity);

        intentTest1Demo = (Button) findViewById(R.id.intentFilterDemo_intentTest1Demo);
        intentTest1Demo.setOnClickListener(this);

        intentTest2Demo = (Button) findViewById(R.id.intentFilterDemo_intentTest2Demo);
        intentTest2Demo.setOnClickListener(this);

        intentTest3Demo = (Button) findViewById(R.id.intentFilterDemo_intentTest3Demo);
        intentTest3Demo.setOnClickListener(this);

        intentTest4Demo = (Button) findViewById(R.id.intentFilterDemo_intentTest4Demo);
        intentTest4Demo.setOnClickListener(this);

    }

    private void BroadcastStringMessage(){
        String ACTION_VIEW = "com.example.intent.ReceiverDemo1.ACTION_VIEW";
        Intent intent = new Intent(ACTION_VIEW);
        Bundle bundle = new Bundle();
        bundle.putString(BUNDL_KEY_SEARIAL_NUMBER, "abc_1234");
        intent.putExtras(bundle);

        sendBroadcast(intent);
    }

    private void BroadcastEntityMessage(){
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setFrom("Taipei");
        messageEntity.setTo("Taichung");
        messageEntity.setMessage("Hello World");

        String ACTION_VIEW = "com.example.intent.ReceiverDemo2.ACTION_VIEW";
        Intent intent = new Intent(ACTION_VIEW);
        intent.putExtra(BUNDLE_SERILIZABLE_MESSAGE, messageEntity);

        sendBroadcast(intent);
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

            case R.id.intentFilterDemo_intentTest3Demo:
                BroadcastStringMessage();
                break;

            case R.id.intentFilterDemo_intentTest4Demo:
                BroadcastEntityMessage();
                break;

            default:
                break;
        }
    }
}
