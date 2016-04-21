package com.example.demo.activity.home;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.demo.R;
import com.example.demo.activity.AlarmManagerDemo.AlarmManagerDemoActivity;
import com.example.demo.activity.AlertDialogDemo.AlertDialogDemoActivity;
import com.example.demo.activity.AnimationDemo.AnmationDemoActivity;
import com.example.demo.activity.CardViewDemo.CardViewDemoActivity;
import com.example.demo.activity.CheckPermissionDemoMoreThanAndroidV60.CheckPermissionDemoActivity;
import com.example.demo.activity.DrawerDemo.DrawerDemoActivity;
import com.example.demo.activity.ExpandableListviewDemo.ExpandableListviewDemoActivity;
import com.example.demo.activity.FragmentDemo.FragmentDemoActivity;
import com.example.demo.activity.GCMDemo.GCMDemoActivity;
import com.example.demo.activity.GoogleSmartLockDemo.GoogleSmartLockActivity;
import com.example.demo.activity.ImageViewDemo.ImageViewDemoActivity;
import com.example.demo.activity.IntentFilterDemo.IntentFilterDemoActivity;
import com.example.demo.activity.ManagerDemo.ManagerDemoActivity;
import com.example.demo.activity.MenuAndActionBarDemo.MenuAndActionBarDemoActivity;
import com.example.demo.activity.PopupWindowDemo.PopupWindowDemoActivity;
import com.example.demo.activity.RecycleViewDemo.RecycleViewDemoActivity;
import com.example.demo.activity.SendNotificationDemo.SendNotificationDemoActivity;
import com.example.demo.activity.TabHost.TabHostDemoActivity;
import com.example.demo.activity.TextviewEditTextDemo.TextViewAndEditViewDemoActivity;
import com.example.demo.activity.UseLinearLayouToDisplayFullGridViewDemo.FullGridViewActivity;
import com.example.demo.activity.Video.VideoDemoActivity;
import com.example.demo.activity.ViewHolderDemo.ViewHolderDemoActvity;
import com.example.demo.activity.VolleyDemo.VolleyDemoActivity;
import com.example.demo.activity.WebViewDemo.WebViewDemoActivity;
import com.example.demo.activity.swipeRefreshDemo.SwipeRefreshDemoActivity;

public class MainActivity extends Activity implements OnClickListener {

//    private Button fragmentDemoButton;
//    private Button textviewAndEditTextDemoButton;
//    private Button imageViewDemoButton;
//    private Button tabhostDemoButton;
//    private Button drawerDemoButton;
//    private Button viewHolderDemoButton;
//    private Button expandableListviewDemoButton;
//    private Button menuAndActionBarDemoButton;
//    private Button alertDialogDemoButton;
//    private Button popupWindowDemoButton;
//    private Button sendNotificationDemoButton;
//    private Button webViewDemoButton;
//    private Button animationDemoButton;
//    private Button videoDemoButton;
//    private Button swipeRefreshDemoButton;
//    private Button recycleViewDemoButton;
//    private Button cardviewDemoButton;
//    private Button intentFilterDemoButton;
//    private Button gcmDemoButton;
//    private Button checkPremissionDemoButton;
//    private Button fullGridViewDemoButton;
//    private Button alarmManagerDemoButton;
//    private Button googleCredentialDemoButton;
//    private Button volleyDemoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        Button fragmentDemoButton = (Button) findViewById(R.id.main_fragmentDemoButton);
        fragmentDemoButton.setOnClickListener(this);

        Button textviewAndEditTextDemoButton = (Button) findViewById(R.id.main_textviewAndEditTextDemoButton);
        textviewAndEditTextDemoButton.setOnClickListener(this);

        Button imageViewDemoButton = (Button) findViewById(R.id.main_imageViewDemoButton);
        imageViewDemoButton.setOnClickListener(this);

        Button tabhostDemoButton = (Button) findViewById(R.id.main_tabhostDemoButton);
        tabhostDemoButton.setOnClickListener(this);

        Button drawerDemoButton = (Button) findViewById(R.id.main_drawerDemoButton);
        drawerDemoButton.setOnClickListener(this);

        Button viewHolderDemoButton = (Button) findViewById(R.id.main_viewHolderDemoButton);
        viewHolderDemoButton.setOnClickListener(this);

        Button expandableListviewDemoButton = (Button) findViewById(R.id.main_expandableListviewDemoButton);
        expandableListviewDemoButton.setOnClickListener(this);

        Button menuAndActionBarDemoButton = (Button) findViewById(R.id.main_menuAndActionBarDemoButton);
        menuAndActionBarDemoButton.setOnClickListener(this);

        Button alertDialogDemoButton = (Button) findViewById(R.id.main_alertDialogDemoButton);
        alertDialogDemoButton.setOnClickListener(this);

        Button popupWindowDemoButton = (Button) findViewById(R.id.main_popupWindowDemoButton);
        popupWindowDemoButton.setOnClickListener(this);

        Button sendNotificationDemoButton = (Button) findViewById(R.id.main_sendNotificationDemoButton);
        sendNotificationDemoButton.setOnClickListener(this);

        Button webViewDemoButton = (Button) findViewById(R.id.main_webViewDemoButton);
        webViewDemoButton.setOnClickListener(this);

        Button animationDemoButton = (Button) findViewById(R.id.main_animationDemoButton);
        animationDemoButton.setOnClickListener(this);

        Button videoDemoButton = (Button) findViewById(R.id.main_videoDemoButton);
        videoDemoButton.setOnClickListener(this);

        Button swipeRefreshDemoButton = (Button) findViewById(R.id.main_swipeRefreshDemoButton);
        swipeRefreshDemoButton.setOnClickListener(this);

        Button recycleViewDemoButton = (Button) findViewById(R.id.main_RecycleViewDemoButton);
        recycleViewDemoButton.setOnClickListener(this);

        Button cardviewDemoButton = (Button) findViewById(R.id.main_CardviewDemoButton);
        cardviewDemoButton.setOnClickListener(this);

        Button intentFilterDemoButton = (Button) findViewById(R.id.main_intentFilterDemoButton);
        intentFilterDemoButton.setOnClickListener(this);

        Button gcmDemoButton = (Button) findViewById(R.id.main_gcmDemoButton);
        gcmDemoButton.setOnClickListener(this);

        Button checkPremissionDemoButton = (Button) findViewById(R.id.main_checkPremissionDemoButton);
        checkPremissionDemoButton.setOnClickListener(this);

        Button fullGridViewDemoButton = (Button) findViewById(R.id.main_fullGridViewDemoButton);
        fullGridViewDemoButton.setOnClickListener(this);

        Button alarmManagerDemoButton = (Button) findViewById(R.id.main_alarmManagerDemoButton);
        alarmManagerDemoButton.setOnClickListener(this);

        Button googleCredentialDemoButton = (Button) findViewById(R.id.main_googleCredentialDemoButton);
        googleCredentialDemoButton.setOnClickListener(this);

        Button volleyDemoButton = (Button) findViewById(R.id.main_volleyDemoButton);
        volleyDemoButton.setOnClickListener(this);

        Button managerDemoButton = (Button) findViewById(R.id.main_managerDemoButton);
        managerDemoButton.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {

        Intent intent = new Intent();

        switch (view.getId()) {

            case R.id.main_fragmentDemoButton:
                intent.setClass(this, FragmentDemoActivity.class);
                break;

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
                intent.setClass(this, GoogleSmartLockActivity.class);
                break;

            case R.id.main_volleyDemoButton:
                intent.setClass(this, VolleyDemoActivity.class);
                break;

            case R.id.main_managerDemoButton:
                intent.setClass(this, ManagerDemoActivity.class);
                break;

            default:
                break;
        }

        startActivity(intent);

    }

}
