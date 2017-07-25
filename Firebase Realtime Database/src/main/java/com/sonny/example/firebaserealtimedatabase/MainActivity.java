package com.sonny.example.firebaserealtimedatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button signInButton = (Button) findViewById(R.id.main_signInButton);
        signInButton.setOnClickListener(this);

        Button postNewPostButton = (Button) findViewById(R.id.main_postNewPostButton);
        postNewPostButton.setOnClickListener(this);

        Button listMessageButton = (Button) findViewById(R.id.main_listMessageButton);
        listMessageButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();

        switch (view.getId()) {
            case R.id.main_signInButton:
                intent.setClass(this, SignInActivity.class);
                break;

            case R.id.main_postNewPostButton:
                intent.setClass(this, PostNewMessageActivity.class);
                break;

            case R.id.main_listMessageButton:
                intent.setClass(this, ListMessageActivity.class);
                break;

            default:
                break;
        }
        startActivity(intent);
    }
}
