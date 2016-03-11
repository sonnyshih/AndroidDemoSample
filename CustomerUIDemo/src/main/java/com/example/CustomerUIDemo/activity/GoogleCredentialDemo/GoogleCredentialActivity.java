package com.example.CustomerUIDemo.activity.GoogleCredentialDemo;


import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.CustomerUIDemo.R;
import com.example.CustomerUIDemo.util.StringUtil;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.CredentialRequest;
import com.google.android.gms.auth.api.credentials.CredentialRequestResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;


public class GoogleCredentialActivity extends AppCompatActivity
        implements ConnectionCallbacks, OnConnectionFailedListener, OnClickListener {

    private static final String TAG = "Mylog";

    private static final int RC_SAVE = 1;
    private static final int RC_READ = 3;


    private GoogleApiClient googleApiClient;

    private LinearLayout mainLayout;
    private ProgressBar signInProgressBar;

    private TextInputLayout usernameTextInputLayout;
    private TextInputLayout passwordTextInputLayout;

    private Button signInButton;
    private Button clearButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.google_credential_activity);

        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .enableAutoManage(this, this)
                .addApi(Auth.CREDENTIALS_API)
                .build();

        mainLayout = (LinearLayout) findViewById(R.id.googleCredential_mainLayout);
        mainLayout.setVisibility(View.GONE);

        signInProgressBar = (ProgressBar) findViewById(R.id.googleCredential_signInProgressBar);
        signInProgressBar.setVisibility(View.VISIBLE);

        usernameTextInputLayout = (TextInputLayout) findViewById(R.id.googleCredential_usernameTextInputLayout);
        passwordTextInputLayout = (TextInputLayout) findViewById(R.id.googleCredential_passwordTextInputLayout);

        signInButton = (Button) findViewById(R.id.googleCredential_signInButton);
        signInButton.setOnClickListener(this);

        clearButton = (Button) findViewById(R.id.googleCredential_clearButton);
        clearButton.setOnClickListener(this);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult:" + requestCode + ":" + resultCode + ":" + data);

        switch (requestCode) {
            case RC_READ:
                if (resultCode == RESULT_OK) {
                    Credential credential = data.getParcelableExtra(Credential.EXTRA_KEY);
//                    retrievedCredential(credential);
                    autoFillAccountAndPassword(credential);

                } else {
                    Log.e(TAG, "Credential Read: NOT OK");
                    mainLayout.setVisibility(View.VISIBLE);
                    signInProgressBar.setVisibility(View.GONE);
                }

                break;

            case RC_SAVE:
                Log.d(TAG, "Result code: " + resultCode);
                if (resultCode == RESULT_OK) {
                    Log.d(TAG, "Credential Save: OK");
                } else {
                    Log.e(TAG, "Credential Save Failed");
                }
                goToMyAccount();

                break;

            default:
                break;
        }

    }


    @Override
    public void onConnected(Bundle bundle) {

        requestCredentials();
    }

    @Override
    public void onConnectionSuspended(int cause) {

        Log.d(TAG, "onConnectionSuspended: " + cause);
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

        Log.d(TAG, "onConnectionFailed: " + connectionResult);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.googleCredential_signInButton:
                onSignInButtonClick();
                break;

            case R.id.googleCredential_clearButton:
                onClearButtonClick();
                break;

            default:
                break;
        }

    }

    private void onSignInButtonClick() {
        String username = usernameTextInputLayout.getEditText().getText().toString();
        String password = passwordTextInputLayout.getEditText().getText().toString();

        if (StringUtil.isEmpty(username)) {
            usernameTextInputLayout.setError("username is empty");
            return;
        }

        if (StringUtil.isEmpty(password)) {
            passwordTextInputLayout.setError("password is empty");
            return;
        }


        Credential credential = new Credential.Builder(username)
                .setPassword(password)
                .build();

        if (LoginChecked.isValidCredential(credential)) {
            saveCredential(credential);
        } else {
            Log.d(TAG, "Credentials are invalid. Username or password are " +
                    "incorrect.");
            Toast.makeText(this, "Credentials are invalid",
                    Toast.LENGTH_SHORT).show();
        }

    }

    private void onClearButtonClick() {
        usernameTextInputLayout.getEditText().setText("");
        passwordTextInputLayout.getEditText().setText("");

    }

    private void requestCredentials() {

        CredentialRequest credentialRequest = new CredentialRequest.Builder()
                .setPasswordLoginSupported(true)
                .build();

        Auth.CredentialsApi.request(googleApiClient, credentialRequest).setResultCallback(
                new ResultCallback<CredentialRequestResult>() {
                    @Override
                    public void onResult(CredentialRequestResult credentialRequestResult) {

                        Status status = credentialRequestResult.getStatus();
                        if (credentialRequestResult.getStatus().isSuccess()) {
                            // Successfully read the credential without any user interaction, this
                            // means there was only a single credential and the user has auto
                            // sign-in enabled.
                            Log.d(TAG, "auto sign-in");
                            Credential credential = credentialRequestResult.getCredential();
                            retrievedCredential(credential);

                        } else {

                            if (status.getStatusCode() == CommonStatusCodes.RESOLUTION_REQUIRED) {
                                // This is most likely the case where the user has multiple saved
                                // credentials and needs to pick one.
                                Log.d(TAG, "Resolution Required");
                                resolveResult(status, RC_READ);

                            } else if (status.getStatusCode() == CommonStatusCodes.SIGN_IN_REQUIRED) {
                                // This is most likely the case where the user does not currently
                                // have any saved credentials and thus needs to provide a username
                                // and password to sign in.
                                Log.d(TAG, "Sign in required");
                                mainLayout.setVisibility(View.VISIBLE);
                                signInProgressBar.setVisibility(View.GONE);

                            } else {
                                Log.w(TAG, "Unrecognized status code: " + status.getStatusCode());
                                mainLayout.setVisibility(View.VISIBLE);
                                signInProgressBar.setVisibility(View.GONE);
                            }

                        }

                    }
                }
        );

    }

    private void retrievedCredential(Credential credential) {
        if (LoginChecked.isValidCredential(credential)) {
            goToMyAccount();

        } else {
            // This is likely due to the credential being changed outside of
            // Smart Lock,
            // ie: away from Android or Chrome. The credential should be deleted
            // and the user allowed to enter a valid credential.
            Log.d(TAG, "Retrieved credential invalid, so delete retrieved credential.");
            Toast.makeText(this, "Retrieved credentials are invalid, so will be deleted.", Toast.LENGTH_LONG).show();
            deleteCredential(credential);
//            Auth.CredentialsApi.disableAutoSignIn(googleApiClient);
            requestCredentials();
        }

    }

    private void autoFillAccountAndPassword(Credential credential){

        if (credential != null) {
            String username = credential.getId();
            String password = credential.getPassword();

            mainLayout.setVisibility(View.VISIBLE);
            signInProgressBar.setVisibility(View.GONE);

            usernameTextInputLayout.getEditText().setText(username);
            passwordTextInputLayout.getEditText().setText(password);
        }
    }

    private void resolveResult(Status status, int requestCode) {

        Log.d(TAG, "Resolving: " + status);

        if (status.hasResolution()) {
            Log.d(TAG, "STATUS: RESOLVING");

            try {
                status.startResolutionForResult(this, requestCode);
            } catch (IntentSender.SendIntentException e) {
                Log.e(TAG, "STATUS: Failed to send resolution.", e);
            }

        } else {
            goToMyAccount();
        }
    }

    protected void saveCredential(Credential credential) {
        // Credential is valid so save it.
        Auth.CredentialsApi.save(googleApiClient, credential)
                .setResultCallback(new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {

                        if (status.isSuccess()) {
                            Log.d(TAG, "Credential saved");
                            goToMyAccount();

                        } else {

                            Log.d(TAG, "Attempt to save credential failed " +
                                    status.getStatusMessage() + " " +
                                    status.getStatusCode());
                            resolveResult(status, RC_SAVE);
                        }

                    }
                });
    }

    private void deleteCredential(Credential credential) {
        Auth.CredentialsApi.delete(googleApiClient, credential)
                .setResultCallback(new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        if (status.isSuccess()) {
                            Log.d(TAG, "Credential successfully deleted.");
                        } else {
                            // This may be due to the credential not existing, possibly
                            // already deleted via another device/app.
                            Log.d(TAG, "Credential not deleted successfully.");
                        }
                    }
                });
    }

    protected void goToMyAccount() {
        startActivity(new Intent(this, MyAccountActivity.class));
        finish();
    }


}
