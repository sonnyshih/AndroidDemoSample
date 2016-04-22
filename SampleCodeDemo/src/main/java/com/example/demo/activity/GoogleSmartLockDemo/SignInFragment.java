package com.example.demo.activity.GoogleSmartLockDemo;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.demo.R;
import com.example.demo.util.StringUtil;
import com.google.android.gms.auth.api.credentials.Credential;

public class SignInFragment extends Fragment implements OnClickListener, OnCheckedChangeListener {
    private ScrollView scrollView;
    private ProgressBar signInProgressBar;

    private TextInputLayout usernameTextInputLayout;
    private TextInputLayout passwordTextInputLayout;

    private EditText usernameEditText;
    private EditText passwordEditText;

    private ToggleButton showPasswordToggleButton;

    private Button signInButton;
    private Button clearButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.signin_fragment, container, false);

        scrollView = (ScrollView) view.findViewById(R.id.signUpFragment_mainScrollView);
        signInProgressBar = (ProgressBar) view.findViewById(R.id.signUpFragment_signInProgressBar);

        usernameTextInputLayout = (TextInputLayout) view.findViewById(R.id.signInFragment_usernameTextInputLayout);
        passwordTextInputLayout = (TextInputLayout) view.findViewById(R.id.signInFragment_passwordTextInputLayout);

        usernameEditText = (EditText) view.findViewById(R.id.signInFragment_usernameEditText);
        passwordEditText = (EditText) view.findViewById(R.id.signInFragment_passwordEditText);

        showPasswordToggleButton = (ToggleButton) view.findViewById(R.id.signInFragment_showPasswordToggleButton);
        showPasswordToggleButton.setOnCheckedChangeListener(this);

        signInButton = (Button) view.findViewById(R.id.signInFragment_signInButton);
        signInButton.setOnClickListener(this);

        clearButton = (Button) view.findViewById(R.id.signInFragment_clearButton);
        clearButton.setOnClickListener(this);

        return view;
    }

    public EditText getUsernameEditText() {
        return usernameEditText;
    }

    public EditText getPasswordEditText() {
        return passwordEditText;
    }


    public void showLoading(){
        signInProgressBar.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);
    }

    public void hiddenLoading(){
        signInProgressBar.setVisibility(View.GONE);
        scrollView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.signInFragment_signInButton:
                showLoading();
                onSignInButtonClick();
                break;

            case R.id.signInFragment_clearButton:
                onClearButtonClick();
                break;

            default:
                break;
        }

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.signInFragment_showPasswordToggleButton:
                displayPasswordTransformationMethod(isChecked);
                break;

            default:
                break;
        }
    }

    private void displayPasswordTransformationMethod(boolean showPassword) {

        if (showPassword) {
            passwordEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {
            passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        passwordEditText.setSelection(passwordEditText.getText().length());
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
            ((GoogleSmartLockActivity) getActivity()).saveCredential(credential);
        } else {
            hiddenLoading();
            Log.d("Mylog", "Credentials are invalid. Username or password are incorrect.");
            Toast.makeText(getContext(), "Credentials are invalid",
                    Toast.LENGTH_SHORT).show();
        }

    }

    private void onClearButtonClick() {
        usernameTextInputLayout.getEditText().setText("");
        passwordTextInputLayout.getEditText().setText("");

    }


}
