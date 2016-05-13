package com.example.demo.activity.GoogleSmartLockDemo;


import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.Toolbar.OnMenuItemClickListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.demo.R;
import com.example.demo.util.StringUtil;
import com.example.demo.util.SystemUtil;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.CredentialPickerConfig;
import com.google.android.gms.auth.api.credentials.CredentialRequest;
import com.google.android.gms.auth.api.credentials.CredentialRequestResult;
import com.google.android.gms.auth.api.credentials.HintRequest;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;


public class GoogleSmartLockActivity extends AppCompatActivity
        implements OnMenuItemClickListener, ConnectionCallbacks, OnConnectionFailedListener {

    private static final String TAG = "Mylog";

    private String SIGN_IN_FRAGMENT_TAG = "SIGN_IN_FRAGMENT_TAG";
    private String SIGN_UP_FRAGMENT_TAG = "SIGN_UP_FRAGMENT_TAG";

    private static final int RC_SAVE = 1;
    private static final int RC_READ = 3;
    private static final int RC_HINT = 4;

    private Toolbar toolbar;
    private GoogleApiClient googleApiClient;
    private SignInFragment signInFragment;
    private SignUpFragment signUpFragment;

    private FrameLayout loadingLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.google_smart_lock_activity);

        loadingLayout = (FrameLayout) findViewById(R.id.loadingLayout);

        showLoading();
        layoutActionBar();
        initFragment();
        initGoogleApiClient();

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        SystemUtil.hideSoftKeyboard(getCurrentFocus());


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        toolbar.inflateMenu(R.menu.login_menu);
        toolbar.setOnMenuItemClickListener(this);
        showMenuItem(R.id.loginMenu_signUp);

        return true;
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.loginMenu_signIn:
                onSignInMenuClick();
                break;

            case R.id.loginMenu_signUp:
                onSignUpMenuClick();
                break;
        }

        return false;
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
                    hiddenLoading();
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

            case RC_HINT:
                if (resultCode == RESULT_OK) {
                    Credential credential = data.getParcelableExtra(Credential.EXTRA_KEY);
                    autoFillAccountAndPassword(credential);

                } else {
                    Log.e(TAG, "Credential Read: NOT OK");
                    hiddenLoading();
                }
                break;

            default:
                break;
        }

    }

    private void layoutActionBar(){
        findViewById(R.id.baseNavigationDrawer_normalActionModeToolbarContainer).setVisibility(View.VISIBLE);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setVisibility(View.VISIBLE);
        toolbar.setTitleTextAppearance(this, R.style.Newegg_Toolbar_TitleText_Bold_White16);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

    }

    private void initGoogleApiClient(){
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .enableAutoManage(this, this)
                .addApi(Auth.CREDENTIALS_API)
                .build();
    }


    private void initFragment() {
        if (signInFragment == null) {
            signInFragment = new SignInFragment();
        }

        if (signUpFragment == null) {
            signUpFragment = new SignUpFragment();
        }

        replaceFragment(signInFragment, SIGN_IN_FRAGMENT_TAG);
    }


    private void onSignInMenuClick() {

//        getSupportActionBar().setTitle("Sign In");
        showMenuItem(R.id.loginMenu_signUp);
        replaceFragment(signInFragment, SIGN_IN_FRAGMENT_TAG);
        SystemUtil.hideSoftKeyboard(getCurrentFocus());
    }

    private void onSignUpMenuClick() {
//        getSupportActionBar().setTitle("Sign Up");
        showMenuItem(R.id.loginMenu_signIn);
        replaceFragment(signUpFragment, SIGN_UP_FRAGMENT_TAG);
        SystemUtil.hideSoftKeyboard(getCurrentFocus());
    }

    private void showMenuItem(int id){
        switch (id) {
            case R.id.loginMenu_signIn:
                toolbar.getMenu().findItem(R.id.loginMenu_signIn).setVisible(true);
                toolbar.getMenu().findItem(R.id.loginMenu_signUp).setVisible(false);
                break;

            case R.id.loginMenu_signUp:
                toolbar.getMenu().findItem(R.id.loginMenu_signIn).setVisible(false);
                toolbar.getMenu().findItem(R.id.loginMenu_signUp).setVisible(true);
                break;

            default:
                toolbar.getMenu().findItem(R.id.loginMenu_signIn).setVisible(true);
                toolbar.getMenu().findItem(R.id.loginMenu_signUp).setVisible(false);
                break;
        }
    }

    public void replaceFragment(Fragment fragment, String tag){

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.sliding_in, R.anim.sliding_out);

//        fragmentTransaction.setCustomAnimations(R.anim.slide_in_up, R.anim.slide_out_up);
//        fragmentTransaction.setCustomAnimations(R.anim.sliding_in_alpha, R.anim.sliding_out_alpha);

        fragmentTransaction.replace(R.id.googleSmartLock_fragmentContainer, fragment, tag)
                .commitAllowingStateLoss();
    }

    private void showLoading(){
        loadingLayout.setVisibility(View.VISIBLE);
    }

    private void hiddenLoading(){
        loadingLayout.setVisibility(View.GONE);
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


    private void requestCredentials() {

        CredentialRequest credentialRequest = new CredentialRequest.Builder()
                .setPasswordLoginSupported(true)
                .build();

        Auth.CredentialsApi.request(googleApiClient, credentialRequest).setResultCallback(
                new ResultCallback<CredentialRequestResult>() {
                    @Override
                    public void onResult(CredentialRequestResult credentialRequestResult) {
                        hiddenLoading();

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
                                retrieveHints();

                            } else {
                                Log.w(TAG, "Unrecognized status code: " + status.getStatusCode());
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

    private void retrieveHints(){
        HintRequest hintRequest = new HintRequest.Builder()
                .setHintPickerConfig(new CredentialPickerConfig.Builder()
                        .setShowCancelButton(true)
                        .build())
                .setEmailAddressIdentifierSupported(true)
                .build();

        PendingIntent intent =
                Auth.CredentialsApi.getHintPickerIntent(googleApiClient, hintRequest);
        try {
            startIntentSenderForResult(intent.getIntentSender(), RC_HINT, null, 0, 0, 0);
        } catch (IntentSender.SendIntentException e) {
            Log.e(TAG, "Could not start hint picker Intent", e);
        }
    }

    private void autoFillAccountAndPassword(Credential credential){

        if (credential != null) {
            String username = credential.getId();
            String password = credential.getPassword();

            if (!StringUtil.isEmpty(username)) {
                signInFragment.getUsernameEditText().setText(username);
            }

            if (!StringUtil.isEmpty(password)) {
                signInFragment.getPasswordEditText().setText(password);
            }

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
