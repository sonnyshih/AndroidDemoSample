package com.example.demo.util;


import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class SystemUtil {
    public static void hideSoftKeyboard(View view) {
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
