package com.sonny.example.firebaseauthentication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AccountPasswordAuthActivity extends BaseActivity implements OnClickListener{
    private static final String TAG = "EmailPassword";

    private TextView statusTextView;
    private TextView detailTextView;
    private EditText emailEditText;
    private EditText passwordEditText;

    private Button signInButton;
    private Button createAccountButton;
    private Button signOutButton;
    private Button verifyEmailButton;

    private LinearLayout emailPasswordEditTextLayout;
    private LinearLayout emailPasswordButtonLayout;
    private LinearLayout signedInButtonLayout;



    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_password_auth);

        statusTextView = (TextView) findViewById(R.id.accountPasswordAuth_statusTextView);
        detailTextView = (TextView) findViewById(R.id.accountPasswordAuth_detailTextView);
        emailEditText = (EditText) findViewById(R.id.accountPasswordAuth_emailEditText);
        passwordEditText = (EditText) findViewById(R.id.accountPasswordAuth_passwordEditText);


        signInButton = (Button) findViewById(R.id.accountPasswordAuth_signInButton);
        signInButton.setOnClickListener(this);

        createAccountButton = (Button) findViewById(R.id.accountPasswordAuth_createAccountButton);
        createAccountButton.setOnClickListener(this);

        signOutButton = (Button) findViewById(R.id.accountPasswordAuth_signOutButton);
        signOutButton.setOnClickListener(this);

        verifyEmailButton = (Button) findViewById(R.id.accountPasswordAuth_verifyEmailButton);
        verifyEmailButton.setOnClickListener(this);

        emailPasswordEditTextLayout = (LinearLayout) findViewById(R.id.accountPasswordAuth_emailPasswordEditTextLayout);
        emailPasswordButtonLayout = (LinearLayout) findViewById(R.id.accountPasswordAuth_emailPasswordButtonLayout);
        signedInButtonLayout = (LinearLayout) findViewById(R.id.accountPasswordAuth_signedInButtonLayout);

        firebaseAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser user) {
        hideProgressDialog();

        if (user != null) {
            statusTextView.setText(getString(R.string.emailpassword_status_fmt,
                    user.getEmail(), user.isEmailVerified()));
            detailTextView.setText(getString(R.string.firebase_status_fmt, user.getUid()));

            emailPasswordButtonLayout.setVisibility(View.GONE);
            emailPasswordEditTextLayout.setVisibility(View.GONE);
            signedInButtonLayout.setVisibility(View.VISIBLE);
            verifyEmailButton.setEnabled(!user.isEmailVerified());

        } else {
            statusTextView.setText(R.string.signed_out);
            detailTextView.setText(null);

            emailPasswordButtonLayout.setVisibility(View.VISIBLE);
            emailPasswordEditTextLayout.setVisibility(View.VISIBLE);
            signedInButtonLayout.setVisibility(View.GONE);
        }
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = emailEditText.getText().toString();
        if (TextUtils.isEmpty(email)) {
            emailEditText.setError("Required.");
            valid = false;
        } else {
            emailEditText.setError(null);
        }

        String password = passwordEditText.getText().toString();
        if (TextUtils.isEmpty(password)) {
            passwordEditText.setError("Required.");
            valid = false;
        } else {
            passwordEditText.setError(null);
        }

        return valid;
    }


    private void createAccount(String email, String password) {
        Log.d(TAG, "createAccount:" + email);
        if (!validateForm()) {
            return;
        }

        showProgressDialog();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(AccountPasswordAuthActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        hideProgressDialog();
                    }
                });

    }

    private void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);
        if (!validateForm()) {
            return;
        }

        showProgressDialog();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(AccountPasswordAuthActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        if (!task.isSuccessful()) {
                            statusTextView.setText(R.string.auth_failed);
                        }
                        hideProgressDialog();
                    }
                });
    }

    private void signOut() {
        firebaseAuth.signOut();
        updateUI(null);
    }

    private void sendEmailVerification() {
        // Disable button
        verifyEmailButton.setEnabled(false);

        // Send verification email
        final FirebaseUser user = firebaseAuth.getCurrentUser();
        user.sendEmailVerification()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        // Re-enable button
                        verifyEmailButton.setEnabled(true);

                        if (task.isSuccessful()) {
                            Toast.makeText(AccountPasswordAuthActivity.this,
                                    "Verification email sent to " + user.getEmail(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Log.e(TAG, "sendEmailVerification", task.getException());
                            Toast.makeText(AccountPasswordAuthActivity.this, "Failed to send verification email.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.accountPasswordAuth_createAccountButton:
                createAccount(emailEditText.getText().toString(), passwordEditText.getText().toString());
                break;

            case R.id.accountPasswordAuth_signInButton:
                signIn(emailEditText.getText().toString(), passwordEditText.getText().toString());
                break;

            case R.id.accountPasswordAuth_signOutButton:
                signOut();
                break;

            case R.id.accountPasswordAuth_verifyEmailButton:
                sendEmailVerification();
                break;

            default:
                break;
        }
    }
}
