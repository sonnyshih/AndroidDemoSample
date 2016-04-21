package com.example.demo.activity.ManagerDemo;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.demo.R;
import com.example.demo.util.StringUtil;

public class BaseActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ViewGroup mainView;
    private ProgressDialog mShowLoadingDialog;
    private FrameLayout mLoadingView;
    private View mErrorView;
    private LinearLayout mEmptyLayout;
    private TextView mEmptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    protected void initBaseView(View mainView) {
        mLoadingView = (FrameLayout) mainView.findViewById(R.id.loadingLayout);
        mErrorView = mainView.findViewById(R.id.error_view);
        mEmptyLayout = (LinearLayout) mainView.findViewById(R.id.empty_layout);
        mEmptyView = (TextView) mainView.findViewById(R.id.empty_textview);
    }


    /**
     * @param resId               The home icon. (No icon, resId set 0)
     * @param title               The toolbar title.
     * @param isHomeAsUpEnabled   Enable/disable the homeAsUp.
     * @param isHomeButtonEnabled Enable/disable the home button.
     */
    protected void initToolbarNavigationHome(int resId, String title,
                                             boolean isHomeAsUpEnabled, boolean isHomeButtonEnabled) {

        final Toolbar toolbar = getToolbar();

        // Setting title - Sonny Shih 2015/07/01
        if (!StringUtil.isEmpty(title)) {
            toolbar.setTitle(title);
        }

        // Setting the home icon - Sonny Shih 2015/07/01
        if (resId > 0) {
            toolbar.setNavigationIcon(resId);
        }

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(isHomeAsUpEnabled);
        getSupportActionBar().setHomeButtonEnabled(isHomeButtonEnabled);


    }

    protected Toolbar getToolbar() {
        if (toolbar == null) {
            initToolbar();
        }
        return toolbar;
    }

    protected void initToolbar() {
        View contentView = findViewById(android.R.id.content);
        if (contentView instanceof ViewGroup) {
            int resId = R.layout.toolbar_layout;
            View toolbarLayout = LayoutInflater.from(this).inflate(resId, null);
            ((ViewGroup) contentView).addView(toolbarLayout);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) mainView.getLayoutParams();
            layoutParams.topMargin = getResources().getDimensionPixelOffset(R.dimen.height_56dp);
        }
        toolbar = (Toolbar) contentView.findViewById(R.id.toolbar);
        toolbar.setVisibility(View.VISIBLE);
        toolbar.setTitleTextAppearance(this, R.style.Newegg_Toolbar_TitleText_Bold_White16);
        setSupportActionBar(toolbar);
    }

    protected void showKeyboard(EditText view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

            int sdkVersion = android.os.Build.VERSION.SDK_INT;

            if (sdkVersion < 11) {
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
                        InputMethodManager.HIDE_IMPLICIT_ONLY);
            } else {
                imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
            }
        }
    }


    protected void hideKeyboard() {
        Object object = getCurrentFocus();
        if (object != null) {
            ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                    .hideSoftInputFromWindow(
                            getCurrentFocus().getWindowToken(),
                            InputMethodManager.RESULT_UNCHANGED_SHOWN);
        }
    }

    /*
     * Show empty view.
    */
    protected void showEmptyTextView(String emptyText) {
        if (mEmptyLayout != null && mEmptyView != null) {
            mEmptyLayout.setVisibility(View.VISIBLE);
            mEmptyView.setText(emptyText != null ? emptyText : "");
        }
    }

    /*
     * Hide empty view.
     */
    protected void hideEmptyTextView() {
        if (mEmptyLayout != null) {
            mEmptyLayout.setVisibility(View.GONE);
        }
    }

    /*
     * Show loading view,if loading_layout.xml has noe been included , this
     * method will show a ProgressDialog.
     */
    protected void showLoading() {
        if (mLoadingView != null) {
            mLoadingView.setVisibility(View.VISIBLE);
        } else {
            hideKeyboard();
            showProgressDialog();
        }
    }

    protected void showLoading(int colorId) {
        mLoadingView.setBackgroundColor(ContextCompat.getColor(this, colorId));
        showLoading();
    }

    /*
     * Hide loading view.
     */
    protected void hiddenLoadding() {
        if (mLoadingView != null) {
            mLoadingView.setVisibility(View.GONE);
        } else {
            hideProgressDialog();
        }
    }

    /*
     * Set up Progress Dialog.
     */
    private void setupProgressDialog() {
        mShowLoadingDialog = new ProgressDialog(this);
        mShowLoadingDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mShowLoadingDialog.setIndeterminate(true);
        mShowLoadingDialog.setCancelable(true);
        mShowLoadingDialog.setMessage("Loading...");
        mShowLoadingDialog.setCanceledOnTouchOutside(false);
    }

    /*
     * Show Progress Dialog , used when layout file did not include
     * loading_layout.xml or specifal loading.
     */
    protected ProgressDialog showProgressDialog() {
        if (mShowLoadingDialog == null) {
            setupProgressDialog();
        }
        hideKeyboard();
        mShowLoadingDialog.show();
        return mShowLoadingDialog;
    }

    /*
     * Hide ProgressDialog if it is showing.
     */
    protected void hideProgressDialog() {
        if (mShowLoadingDialog != null && mShowLoadingDialog.isShowing()) {
            mShowLoadingDialog.dismiss();
        }
    }

//    protected void showErrorView(String errorString,
//                                 onRetryClickedListener listener) {
//        mRetryClickedListener = listener;
//        hiddenLoadding();
//        if (mErrorView != null) {
//            setErrorRetryEvent();
//            mErrorView.setVisibility(View.VISIBLE);
//            TextView errorTextView = (TextView) mErrorView
//                    .findViewById(R.id.error_textview);
//            errorTextView.setText(errorString);
//        }
//
//    }

    protected void hideErrorView() {
        if (mErrorView != null) {
            mErrorView.setVisibility(View.GONE);
        }
    }


//    protected String getErrorString(ErrorType errorType) {
//        return StringUtil.getServiceErrorString(errorType, this);
//    }
//
//
//    private void setErrorRetryEvent() {
//        if (mErrorView != null) {
//            Button retryButton = (Button) mErrorView.findViewById(R.id.retry);
//            retryButton.setOnClickListener(new View.OnClickListener() {
//
//                @Override
//                public void onClick(View v) {
//                    if (mRetryClickedListener != null) {
//                        mRetryClickedListener.onRetryClick();
//                    }
//                }
//            });
//        }
//    }
}
