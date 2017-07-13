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

        Button phoneNumberButton = (Button) findViewById(R.id.main_phoneNumberButton);
        phoneNumberButton.setOnClickListener(this);

        Button googleSignInButton = (Button) findViewById(R.id.main_googleSignInButton);
        googleSignInButton.setOnClickListener(this);

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

            case R.id.main_googleSignInButton:
                intent.setClass(this, GoogleSignInActivity.class);
                break;

            default:
                break;
        }

        startActivity(intent);
    }
}
