package com.sonny.example.firebaseauthentication;

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

        Button accountPasswordButton = (Button) findViewById(R.id.main_accountPasswordButton);
        accountPasswordButton.setOnClickListener(this);

        Button main_phoneNumberButton = (Button) findViewById(R.id.main_phoneNumberButton);
        main_phoneNumberButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent();

        switch (view.getId()){
            case R.id.main_accountPasswordButton:
                intent.setClass(this, AccountPasswordAuthActivity.class);
                break;

            case R.id.main_phoneNumberButton:
                intent.setClass(this, PhoneNumberAuthActivity.class);
                break;

            default:
                break;
        }

        startActivity(intent);
    }
}
