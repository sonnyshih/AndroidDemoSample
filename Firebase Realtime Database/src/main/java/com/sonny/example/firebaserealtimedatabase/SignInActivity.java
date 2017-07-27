package com.sonny.example.firebaserealtimedatabase;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sonny.example.firebaserealtimedatabase.entity.User;

public class SignInActivity extends BaseActivity implements OnClickListener {

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;


    private EditText emailEditText;
    private EditText passwordEditText;

    private Button signInButton;
    private Button signOutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        emailEditText = (EditText) findViewById(R.id.signIn_emailEditText);
        passwordEditText = (EditText) findViewById(R.id.signIn_passwordEditText);

        signInButton = (Button) findViewById(R.id.signIn_signInButton);
        signInButton.setOnClickListener(this);

        signOutButton = (Button) findViewById(R.id.signIn_signOutButton);
        signOutButton.setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        // Check auth on Activity start
        if (mAuth.getCurrentUser() != null) {
            onAuthSuccess(mAuth.getCurrentUser());
        } else {
            layoutUserData(null);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void onSignInButtonClick() {
        if (!validateForm()) {
            return;
        }

        showProgressDialog();
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("Mylog", "signIn:onComplete:" + task.isSuccessful());
                        hideProgressDialog();

                        if (task.isSuccessful()) {
                            onAuthSuccess(task.getResult().getUser());
                            Toast.makeText(SignInActivity.this, "Sign In Successfully!",
                                    Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(SignInActivity.this, "Sign In Failed",
                                    Toast.LENGTH_SHORT).show();

                            Log.d("Mylog", "Fail getException:" + task.getException().toString());


                        }
                    }
                });
    }

    private void onSignOutButtonClick() {
        FirebaseAuth.getInstance().signOut();
        layoutUserData(null);
        Toast.makeText(SignInActivity.this, "sign out on Complete!",
                Toast.LENGTH_SHORT).show();

    }

    private boolean validateForm() {
        boolean result = true;
        if (TextUtils.isEmpty(emailEditText.getText().toString())) {
            emailEditText.setError("Required");
            result = false;
        } else {
            emailEditText.setError(null);
        }

        if (TextUtils.isEmpty(passwordEditText.getText().toString())) {
            passwordEditText.setError("Required");
            result = false;
        } else {
            passwordEditText.setError(null);
        }

        return result;
    }

    private void layoutUserData(FirebaseUser user) {

        TextView emailTextView = (TextView) findViewById(R.id.signIn_emailTextView);
        TextView statusTextView = (TextView) findViewById(R.id.signIn_statusTextView);
        TextView userIdTextView = (TextView) findViewById(R.id.signIn_userIdTextView);

        if (user != null) {
            emailTextView.setText(user.getEmail());
            statusTextView.setText(R.string.signed_in);
            userIdTextView.setText(user.getUid());

        } else {
            emailTextView.setText(R.string.emailpassword_title_text);
            statusTextView.setText(R.string.signed_out);
            userIdTextView.setText("");
        }

    }

    private void onAuthSuccess(FirebaseUser user) {
        String username = usernameFromEmail(user.getEmail());

        // Write new user
        writeNewUserToRealtimeDatabase(user.getUid(), username, user.getEmail());

        layoutUserData(user);

    }

    private String usernameFromEmail(String email) {
        if (email.contains("@")) {
            return email.split("@")[0];
        } else {
            return email;
        }
    }

    private void writeNewUserToRealtimeDatabase(String userId, String name, String email) {
        User user = new User(name, email);

        mDatabase.child("users").child(userId).setValue(user);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.signIn_signInButton:
                onSignInButtonClick();
                break;

            case R.id.signIn_signOutButton:
                onSignOutButtonClick();
                break;

            default:
                break;
        }
    }
}
