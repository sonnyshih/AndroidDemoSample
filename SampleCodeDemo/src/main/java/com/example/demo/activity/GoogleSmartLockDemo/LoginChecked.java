package com.example.demo.activity.GoogleSmartLockDemo;


import com.google.android.gms.auth.api.credentials.Credential;

public class LoginChecked {

    public static boolean isValidCredential(Credential credential) {
        String username = credential.getId();
        String password = credential.getPassword();
        return isValidLoigin(username, password);
    }

    public static boolean isValidLoigin(String username, String password) {
        if ((username.equals(UsernamesAndPasswords.username1) && password.equals(UsernamesAndPasswords.password1)) ||
                (username.equals(UsernamesAndPasswords.username2) && password.equals(UsernamesAndPasswords.password2)) ||
                (username.equals(UsernamesAndPasswords.username3) && password.equals(UsernamesAndPasswords.password3))) {
            return true;
        }
        return false;
    }
}
