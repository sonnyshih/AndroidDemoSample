package com.example.demo.activity.CheckPermissionDemoMoreThanAndroidV60;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo.R;

/*
*  Normal and Dangerous Permissions
*  http://developer.android.com/intl/zh-tw/guide/topics/security/permissions.html#normal-dangerous
* */
public class CheckPermissionDemoActivity extends AppCompatActivity implements OnClickListener {

    private static final int REQUEST_CALL_PHONE = 0;
    private static final int REQUEST_PHONE_STATE = 1;

    private static final int REQUEST_CAMERA = 0;

    private LinearLayout rootLayout;
    private EditText phoneNumberEditText;
    private Button callButton;
    private Button getIdButton;
    private Button cameraButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_permission_demo_activity);

        rootLayout = (LinearLayout) findViewById(R.id.checkPermissionDemo_rootLayout);

        phoneNumberEditText = (EditText) findViewById(R.id.checkPermissionDemo_phoneNumberEditText);

        callButton = (Button) findViewById(R.id.checkPermissionDemo_callButton);
        callButton.setOnClickListener(this);

        getIdButton = (Button) findViewById(R.id.checkPermissionDemo_getIdButton);
        getIdButton.setOnClickListener(this);

        cameraButton = (Button) findViewById(R.id.checkPermissionDemo_cameraButton);
        cameraButton.setOnClickListener(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ImageView cameraImageView = (ImageView) findViewById(R.id.checkPermissionDemo_cameraImageView);
        if (resultCode != RESULT_OK) {
            return;
        }

        switch (requestCode) {
            case REQUEST_CAMERA:
                Bundle extras = data.getExtras();
                Bitmap bmp = (Bitmap) extras.get("data");
                cameraImageView.setImageBitmap(bmp);
                cameraImageView.setVisibility(View.VISIBLE);
                break;

            default:
                break;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CALL_PHONE:
                onCallPhoneRequest(grantResults);
                break;

            case REQUEST_PHONE_STATE:
                onPhoneStateRequest(grantResults);
                break;

            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }
    }

    private boolean hasCallPhonePermission() {

        int permissionCheck = ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);

        switch (permissionCheck) {
            case PackageManager.PERMISSION_GRANTED:
                return true;

            case PackageManager.PERMISSION_DENIED:
                return false;

            default:
                return false;
        }

    }

    private void startPhoneCall() {
        String phoneNumber = phoneNumberEditText.getText().toString();
        Intent intentDial = new Intent("android.intent.action.CALL", Uri.parse("tel:" + phoneNumber));
        startActivity(intentDial);
    }

    private void checkPermission() {
        if (shouldShowExplain()) {
            showExplainSnackBar();
        } else {
            requestCallPhonePermission();
        }
    }

    private boolean shouldShowExplain() {
        return ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE);
    }

    private void showExplainSnackBar() {
        Snackbar.make(rootLayout, R.string.check_premission_rationale, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.ok, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        requestCallPhonePermission();
                    }
                }).show();
    }

    private void onCallPhoneRequest(int[] grantResults) {
        if (isRequestPermissionGranted(grantResults)) {
            startPhoneCall();
        } else {
            showWarningSnackBar();
        }
    }

    private boolean isRequestPermissionGranted(int[] grantResults) {
        return grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED;
    }

    private void showWarningSnackBar() {
        Snackbar.make(rootLayout, R.string.check_premission_warning_permission, Snackbar.LENGTH_SHORT).show();
    }

    private void requestCallPhonePermission() {
        ActivityCompat.requestPermissions(this
                , new String[]{Manifest.permission.CALL_PHONE}
                , REQUEST_CALL_PHONE);
    }


    private boolean hasPhoneStatePermission() {
        int permissionCheck = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);

        switch (permissionCheck) {
            case PackageManager.PERMISSION_GRANTED:
                return true;

            case PackageManager.PERMISSION_DENIED:
                return false;

            default:
                return false;
        }

    }

    private void requestPhoneStatePermission() {
        ActivityCompat.requestPermissions(this
                , new String[]{Manifest.permission.READ_PHONE_STATE}
                , REQUEST_PHONE_STATE);
    }

    private void showIDNumber() {
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String deviceId = tm.getDeviceId();
        if (deviceId.isEmpty()) {
            Toast.makeText(this, "Id is Empty", Toast.LENGTH_SHORT).show();
            return;
        }

        TextView idTextView = (TextView) findViewById(R.id.checkPermissionDemo_idTextView);
        idTextView.setText(deviceId);
    }

    private void onPhoneStateRequest(int[] grantResults) {
        if (isRequestPermissionGranted(grantResults)) {
            showIDNumber();
        } else {
            showWarningSnackBar();
        }
    }

    private void onCallButtonClick() {

        if (hasCallPhonePermission()) {
            startPhoneCall();
            return;
        }

        checkPermission();
    }

    private void onGetIdButtonClick() {
        if (!hasPhoneStatePermission()) {
            requestPhoneStatePermission();
            return;
        }

        showIDNumber();
    }


    private void onCameraButtonClick() {
        Intent intent_camera = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent_camera, REQUEST_CAMERA);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.checkPermissionDemo_callButton:
                onCallButtonClick();
                break;

            case R.id.checkPermissionDemo_getIdButton:
                onGetIdButtonClick();
                break;

            case R.id.checkPermissionDemo_cameraButton:
                onCameraButtonClick();
                break;

            default:
                break;
        }

    }
}
