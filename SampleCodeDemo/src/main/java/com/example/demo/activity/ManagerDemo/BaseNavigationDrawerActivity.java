
package com.example.demo.activity.ManagerDemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.demo.R;
import com.example.demo.activity.ManagerDemo.manager.LoginAppManager;
import com.example.demo.activity.ManagerDemo.manager.SettingManager;

public abstract class BaseNavigationDrawerActivity extends BaseActivity implements OnClickListener {

    private Toolbar toolbar;
    private ViewGroup navigationDrawerLayout;
    private ViewGroup contentView;
    private ViewGroup mainView;
    private DrawerLayout drawerLayout;
    private LinearLayout leftDrawerLayout;
//    private AlertDialog changeCountryDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        registerBroadcastReceiver();
    }


    @Override
    public void setContentView(int layoutResID) {
        if (navigationDrawerLayout == null) {
            navigationDrawerLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.base_navigation_drawer, null);
            contentView = (FrameLayout) navigationDrawerLayout.findViewById(R.id.baseNavigationDrawer_contentView);
            super.setContentView(navigationDrawerLayout);
        }
        contentView.removeAllViews();
        mainView = (ViewGroup) LayoutInflater.from(this).inflate(
                layoutResID, contentView, true);
        initBaseView(mainView);
        initNavigationDrawer();
        disableCurrentPageEntrance();
    }

    private void disableCurrentPageEntrance() {
        View disableView = null;
        switch (getCurrentPageType()) {
            case Home:
                disableView = navigationDrawerLayout.findViewById(R.id.baseNavigationDrawer_homeLinearLayout);
                break;

            case MyPage:
                disableView = navigationDrawerLayout.findViewById(R.id.baseNavigationDrawer_myAccountLinearLayout);
                break;

            case Setting:
                disableView = navigationDrawerLayout.findViewById(R.id.baseNavigationDrawer_settingLinearLayout);
                break;

            default:
                break;
        }

        // every activity only has one type
        if (disableView != null) {
            disableView.setEnabled(false);
        }

    }

    private void initNavigationDrawer() {
//        drawerLayout = (DrawerLayout) navigationDrawerLayout.findViewById(R.id.baseNavigationDrawer_DrawerLayout);
//        leftDrawerLayout = (LinearLayout) navigationDrawerLayout.findViewById(R.id.baseNavigationDrawer_leftDrawerLayout);

        // Page Entrance
        navigationDrawerLayout.findViewById(R.id.baseNavigationDrawer_homeLinearLayout).setOnClickListener(this);
        navigationDrawerLayout.findViewById(R.id.baseNavigationDrawer_myAccountLinearLayout).setOnClickListener(this);
        navigationDrawerLayout.findViewById(R.id.baseNavigationDrawer_settingLinearLayout).setOnClickListener(this);

        // Preference Setting
//        navigationDrawerLayout.findViewById(R.id.baseNavigationDrawer_settingsTextView).setOnClickListener(this);
//        navigationDrawerLayout.findViewById(R.id.baseNavigationDrawer_myAccountTextView).setOnClickListener(this);
//        navigationDrawerLayout.findViewById(R.id.baseNavigationDrawer_contactUsTextView).setOnClickListener(this);

//        layoutChangeCountry();
//        layoutCart();
//        layoutLogin();
    }

    protected Toolbar getToolbar() {
        if (toolbar == null) {
            initToolbar();
        }
        return toolbar;
    }

    protected void initToolbar() {
        int resId = R.id.baseNavigationDrawer_normalActionModeToolbarContainer;
        View toolbarContainer = navigationDrawerLayout.findViewById(resId);
        toolbarContainer.setVisibility(View.VISIBLE);
        toolbar = (Toolbar) toolbarContainer.findViewById(R.id.toolbar);
        toolbar.setVisibility(View.VISIBLE);
        toolbar.setTitleTextAppearance(this, R.style.Newegg_Toolbar_TitleText_Bold_White16);
        setSupportActionBar(toolbar);
    }

    // After Logout, do something.
    protected void onLogoutAction() {
    }

    // After Login, do something.
    protected void onLoginAction() {
    }


    private void layoutChangeCountry() {

    }


    private void registerBroadcastReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(LoginAppManager.NOTIFY_LOGIN);
        filter.addAction(LoginAppManager.NOTIFY_LOGOUT);
        filter.addAction(SettingManager.NOTIFY_COUNTRY_CHANGE);
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, filter);
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equalsIgnoreCase(LoginAppManager.NOTIFY_LOGIN)) {
                onLoginAction();

            } else if (intent.getAction().equalsIgnoreCase(LoginAppManager.NOTIFY_LOGOUT)) {
                onLogoutAction();


            } else if (intent.getAction().equalsIgnoreCase(SettingManager.NOTIFY_COUNTRY_CHANGE)) {
                layoutChangeCountry();

            }
        }
    };

    protected abstract GlobalLeftDrawerPage getCurrentPageType();

    protected enum GlobalLeftDrawerPage {
        Home, MyPage, Setting, None
    }

    protected void onHomeMenuItemClick() {
        // can't back to previous page cause SingleTask
        startActivity(new Intent(this, ManagerDemoActivity.class));
    }

    protected void onMyAccountMenuItemClick() {
        startActivity(new Intent(this, MyPageActivity.class));
    }

    protected void onSettingMenuItemClick() {
        startActivity(new Intent(this, SettingActivity.class));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            // Page Entrance
            case R.id.baseNavigationDrawer_homeLinearLayout:
                onHomeMenuItemClick();
                break;

            case R.id.baseNavigationDrawer_myAccountLinearLayout:
                onMyAccountMenuItemClick();
                break;

            case R.id.baseNavigationDrawer_settingLinearLayout:
                onSettingMenuItemClick();
                break;

            default:
                break;
        }

        drawerLayout.closeDrawer(leftDrawerLayout);
    }
}
