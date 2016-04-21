package com.example.demo.activity.GoogleSmartLockDemo;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.demo.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;

public class MyAccountActivity extends AppCompatActivity
        implements ConnectionCallbacks, OnConnectionFailedListener, OnClickListener {

    private static final String TAG = "Mylog";

    private GoogleApiClient googleApiClient;

    private Button logoutButton;
    private Button changeCredsButton;
    private Button disableAutoSignInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_account_activity);

        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .enableAutoManage(this, this)
                .addApi(Auth.CREDENTIALS_API)
                .build();

        logoutButton = (Button) findViewById(R.id.myAccount_logoutButton);
        logoutButton.setOnClickListener(this);

        changeCredsButton = (Button) findViewById(R.id.myAccount_changeCredsButton);
        changeCredsButton.setOnClickListener(this);

        disableAutoSignInButton = (Button) findViewById(R.id.myAccount_disableAutoSignInButton);
        disableAutoSignInButton.setOnClickListener(this);


    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.d(TAG, "GoogleApiClient connected");

    }

    @Override
    public void onConnectionSuspended(int cause) {
        Log.d(TAG, "GoogleApiClient is suspended with cause code: " + cause);
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d(TAG, "GoogleApiClient failed to connect: " + connectionResult);
    }

    private void onLogoutButtonClick(){
        Toast.makeText(this, "Completed to logout.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, GoogleSmartLockActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    private void onChangeCredsButtonClick(){
        startActivity(new Intent(this, AccountManageActivity.class));
    }

    private void onDisableAutoSignInButtonClick(){
        Auth.CredentialsApi.disableAutoSignIn(googleApiClient);
        Toast.makeText(this, "Disable Auto Sign In", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.myAccount_logoutButton:
                onLogoutButtonClick();
                break;

            case R.id.myAccount_changeCredsButton:
                onChangeCredsButtonClick();
                break;

            case R.id.myAccount_disableAutoSignInButton:
                onDisableAutoSignInButtonClick();
                break;

            default:
                break;
        }
    }
}
