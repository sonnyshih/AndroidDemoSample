package com.example.CustomerUIDemo.activity.home;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.CustomerUIDemo.R;
import com.example.CustomerUIDemo.activity.AlarmManagerDemo.AlarmManagerDemoActivity;
import com.example.CustomerUIDemo.activity.AlertDialogDemo.AlertDialogDemoActivity;
import com.example.CustomerUIDemo.activity.AnimationDemo.AnmationDemoActivity;
import com.example.CustomerUIDemo.activity.CardViewDemo.CardViewDemoActivity;
import com.example.CustomerUIDemo.activity.CheckPermissionDemoMoreThanAndroidV60.CheckPermissionDemoActivity;
import com.example.CustomerUIDemo.activity.DrawerDemo.DrawerDemoActivity;
import com.example.CustomerUIDemo.activity.ExpandableListviewDemo.ExpandableListviewDemoActivity;
import com.example.CustomerUIDemo.activity.GCMDemo.GCMDemoActivity;
import com.example.CustomerUIDemo.activity.GoogleCredentialDemo.GoogleCredentialActivity;
import com.example.CustomerUIDemo.activity.ImageViewDemo.ImageViewDemoActivity;
import com.example.CustomerUIDemo.activity.IntentFilterDemo.IntentFilterDemoActivity;
import com.example.CustomerUIDemo.activity.MenuAndActionBarDemo.MenuAndActionBarDemoActivity;
import com.example.CustomerUIDemo.activity.PopupWindowDemo.PopupWindowDemoActivity;
import com.example.CustomerUIDemo.activity.RecycleViewDemo.RecycleViewDemoActivity;
import com.example.CustomerUIDemo.activity.SendNotificationDemo.SendNotificationDemoActivity;
import com.example.CustomerUIDemo.activity.TabHost.TabHostDemoActivity;
import com.example.CustomerUIDemo.activity.TextviewEditTextDemo.TextViewAndEditViewDemoActivity;
import com.example.CustomerUIDemo.activity.UseLinearLayouToDisplayFullGridViewDemo.FullGridViewActivity;
import com.example.CustomerUIDemo.activity.Video.VideoDemoActivity;
import com.example.CustomerUIDemo.activity.ViewHolderDemo.ViewHolderDemoActvity;
import com.example.CustomerUIDemo.activity.WebViewDemo.WebViewDemoActivity;
import com.example.CustomerUIDemo.activity.swipeRefreshDemo.SwipeRefreshDemoActivity;

public class MainActivity extends Activity implements OnClickListener {

    private Button textviewAndEditTextDemoButton;
    private Button imageViewDemoButton;
    private Button tabhostDemoButton;
    private Button drawerDemoButton;
    private Button viewHolderDemoButton;
    private Button expandableListviewDemoButton;
    private Button menuAndActionBarDemoButton;
    private Button alertDialogDemoButton;
    private Button popupWindowDemoButton;
    private Button sendNotificationDemoButton;
    private Button webViewDemoButton;
    private Button animationDemoButton;
    private Button videoDemoButton;
    private Button swipeRefreshDemoButton;
    private Button recycleViewDemoButton;
    private Button cardviewDemoButton;
    private Button intentFilterDemoButton;
    private Button gcmDemoButton;
    private Button checkPremissionDemoButton;
    private Button fullGridViewDemoButton;
    private Button alarmManagerDemoButton;
    private Button googleCredentialDemoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        textviewAndEditTextDemoButton = (Button) findViewById(R.id.main_textviewAndEditTextDemoButton);
        textviewAndEditTextDemoButton.setOnClickListener(this);

        imageViewDemoButton = (Button) findViewById(R.id.main_imageViewDemoButton);
        imageViewDemoButton.setOnClickListener(this);

        tabhostDemoButton = (Button) findViewById(R.id.main_tabhostDemoButton);
        tabhostDemoButton.setOnClickListener(this);

        drawerDemoButton = (Button) findViewById(R.id.main_drawerDemoButton);
        drawerDemoButton.setOnClickListener(this);

        viewHolderDemoButton = (Button) findViewById(R.id.main_viewHolderDemoButton);
        viewHolderDemoButton.setOnClickListener(this);

        expandableListviewDemoButton = (Button) findViewById(R.id.main_expandableListviewDemoButton);
        expandableListviewDemoButton.setOnClickListener(this);

        menuAndActionBarDemoButton = (Button) findViewById(R.id.main_menuAndActionBarDemoButton);
        menuAndActionBarDemoButton.setOnClickListener(this);

        alertDialogDemoButton = (Button) findViewById(R.id.main_alertDialogDemoButton);
        alertDialogDemoButton.setOnClickListener(this);

        popupWindowDemoButton = (Button) findViewById(R.id.main_popupWindowDemoButton);
        popupWindowDemoButton.setOnClickListener(this);

        sendNotificationDemoButton = (Button) findViewById(R.id.main_sendNotificationDemoButton);
        sendNotificationDemoButton.setOnClickListener(this);

        webViewDemoButton = (Button) findViewById(R.id.main_webViewDemoButton);
        webViewDemoButton.setOnClickListener(this);

        animationDemoButton = (Button) findViewById(R.id.main_animationDemoButton);
        animationDemoButton.setOnClickListener(this);

        videoDemoButton = (Button) findViewById(R.id.main_videoDemoButton);
        videoDemoButton.setOnClickListener(this);

        swipeRefreshDemoButton = (Button) findViewById(R.id.main_swipeRefreshDemoButton);
        swipeRefreshDemoButton.setOnClickListener(this);

        recycleViewDemoButton = (Button) findViewById(R.id.main_RecycleViewDemoButton);
        recycleViewDemoButton.setOnClickListener(this);

        cardviewDemoButton = (Button) findViewById(R.id.main_CardviewDemoButton);
        cardviewDemoButton.setOnClickListener(this);

        intentFilterDemoButton = (Button) findViewById(R.id.main_intentFilterDemoButton);
        intentFilterDemoButton.setOnClickListener(this);

        gcmDemoButton = (Button) findViewById(R.id.main_gcmDemoButton);
        gcmDemoButton.setOnClickListener(this);

        checkPremissionDemoButton = (Button) findViewById(R.id.main_checkPremissionDemoButton);
        checkPremissionDemoButton.setOnClickListener(this);

        fullGridViewDemoButton = (Button) findViewById(R.id.main_fullGridViewDemoButton);
        fullGridViewDemoButton.setOnClickListener(this);

        alarmManagerDemoButton = (Button) findViewById(R.id.main_alarmManagerDemoButton);
        alarmManagerDemoButton.setOnClickListener(this);

        googleCredentialDemoButton = (Button) findViewById(R.id.main_googleCredentialDemoButton);
        googleCredentialDemoButton.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {

        Intent intent = new Intent();

        switch (view.getId()) {
            case R.id.main_textviewAndEditTextDemoButton:
                intent.setClass(this, TextViewAndEditViewDemoActivity.class);
                break;

            case R.id.main_imageViewDemoButton:
                intent.setClass(this, ImageViewDemoActivity.class);
                break;

            case R.id.main_tabhostDemoButton:
                intent.setClass(this, TabHostDemoActivity.class);
                break;

            case R.id.main_drawerDemoButton:
                intent.setClass(this, DrawerDemoActivity.class);
                break;

            case R.id.main_expandableListviewDemoButton:
                intent.setClass(this, ExpandableListviewDemoActivity.class);
                break;

            case R.id.main_viewHolderDemoButton:
                intent.setClass(this, ViewHolderDemoActvity.class);
                break;

            case R.id.main_menuAndActionBarDemoButton:
                intent.setClass(this, MenuAndActionBarDemoActivity.class);
                break;

            case R.id.main_alertDialogDemoButton:
                intent.setClass(this, AlertDialogDemoActivity.class);
                break;

            case R.id.main_popupWindowDemoButton:
                intent.setClass(this, PopupWindowDemoActivity.class);
                break;

            case R.id.main_sendNotificationDemoButton:
                intent.setClass(this, SendNotificationDemoActivity.class);
                break;

            case R.id.main_webViewDemoButton:
                intent.setClass(this, WebViewDemoActivity.class);
                break;

            case R.id.main_animationDemoButton:
                intent.setClass(this, AnmationDemoActivity.class);
                break;

            case R.id.main_videoDemoButton:
                intent.setClass(this, VideoDemoActivity.class);
                break;

            case R.id.main_swipeRefreshDemoButton:
                intent.setClass(this, SwipeRefreshDemoActivity.class);
                break;

            case R.id.main_RecycleViewDemoButton:
                intent.setClass(this, RecycleViewDemoActivity.class);
                break;

            case R.id.main_CardviewDemoButton:
                intent.setClass(this, CardViewDemoActivity.class);
                break;

            case R.id.main_intentFilterDemoButton:
                intent.setClass(this, IntentFilterDemoActivity.class);
                break;

            case R.id.main_gcmDemoButton:
                intent.setClass(this, GCMDemoActivity.class);
                break;

            case R.id.main_checkPremissionDemoButton:
                intent.setClass(this, CheckPermissionDemoActivity.class);
                break;

            case R.id.main_fullGridViewDemoButton:
                intent.setClass(this, FullGridViewActivity.class);
                break;

            case R.id.main_alarmManagerDemoButton:
                intent.setClass(this, AlarmManagerDemoActivity.class);
                break;

            case R.id.main_googleCredentialDemoButton:
                intent.setClass(this, GoogleCredentialActivity.class);
                break;

            default:
                break;
        }

        startActivity(intent);

    }

}
