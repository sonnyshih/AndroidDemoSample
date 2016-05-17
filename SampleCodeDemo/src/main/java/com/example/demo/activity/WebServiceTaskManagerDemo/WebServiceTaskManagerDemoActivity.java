package com.example.demo.activity.WebServiceTaskManagerDemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.demo.R;
import com.example.demo.activity.VolleyDemo.CustomerGsonRequestDemo.entity.CustomerInfo.UILoginResultInfoEntity;
import com.example.demo.activity.VolleyDemo.CustomerGsonRequestDemo.entity.CustomerInfo.UIValidatorInfo;
import com.example.demo.activity.VolleyDemo.CustomerGsonRequestDemo.entity.VStoreNavigationItemInfoEntity;
import com.example.demo.activity.VolleyDemo.CustomerGsonRequestDemo.entity.country.TrackingCountryInfoEntity;
import com.example.demo.activity.WebServiceTaskManagerDemo.manager.WebServiceTaskManager;
import com.example.demo.activity.WebServiceTaskManagerDemo.task.GetUrlCountryIdTask;
import com.example.demo.activity.WebServiceTaskManagerDemo.task.GetUrlCountryIdTask.TrackingCountryIdWebServiceTaskListener;
import com.example.demo.activity.WebServiceTaskManagerDemo.task.GetUrlShopAllNavigationTask;
import com.example.demo.activity.WebServiceTaskManagerDemo.task.GetUrlShopAllNavigationTask.ShopAllNavigationWebServiceTaskResultListener;
import com.example.demo.activity.WebServiceTaskManagerDemo.task.PostUrlLoginTask;
import com.example.demo.activity.WebServiceTaskManagerDemo.task.PostUrlLoginTask.loginWebServiceTaskListener;
import com.example.demo.activity.WebServiceTaskManagerDemo.type.ErrorType;

import java.util.List;

public class WebServiceTaskManagerDemoActivity extends AppCompatActivity implements
        OnClickListener, ShopAllNavigationWebServiceTaskResultListener,
        TrackingCountryIdWebServiceTaskListener,
        loginWebServiceTaskListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_service_task_manager_demo_activity);

        Button getGetUrlShopAllNavigationJsonButton = (Button) findViewById(R.id.webServiceTaskManagerDemo_getGetUrlShopAllNavigationJsonButton);
        getGetUrlShopAllNavigationJsonButton.setOnClickListener(this);

        Button getGetUrlCountryIdJsonButton = (Button) findViewById(R.id.webServiceTaskManagerDemo_getGetUrlCountryIdJsonButton);
        getGetUrlCountryIdJsonButton.setOnClickListener(this);

        Button postJsonButton = (Button) findViewById(R.id.webServiceTaskManagerDemo_postJsonButton);
        postJsonButton.setOnClickListener(this);



    }

    @Override
    protected void onDestroy() {
        WebServiceTaskManager.cancelTasks(this);

        super.onDestroy();
    }

    private void OnGetGetUrlShopAllNavigationJson(){
        GetUrlShopAllNavigationTask getUrlShopAllNavigationTask = new GetUrlShopAllNavigationTask(this);
        WebServiceTaskManager.startTask(getUrlShopAllNavigationTask, this);
    }

    @Override
    public void onShopAllNavigationWebServiceTaskSucceed(List<VStoreNavigationItemInfoEntity> entity) {
        Toast.makeText(this, " GetUrlShopAllNavigationTask = " + entity.get(0).getDescription(),
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onShopAllNavigationWebServiceTaskFailed(ErrorType errorType) {

    }

    @Override
    public void onShopAllNavigationWebServiceTaskEmpty() {

    }

    private void onGetGetUrlCountryIdJsonButtonClick(){
        GetUrlCountryIdTask getUrlCountryIdTask = new GetUrlCountryIdTask(this);
        WebServiceTaskManager.startTask(getUrlCountryIdTask, this);
    }

    @Override
    public void onTrackingCountryIdWebServiceTaskSucceed(TrackingCountryInfoEntity trackingCountryInfo) {
        Toast.makeText(this, " GetUrlCountryIdTask = " + trackingCountryInfo.getCountryName(),
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onTrackingCountryIdWebServiceTaskFailed(ErrorType errorType) {

    }

    @Override
    public void onTrackingCountryIdWebServiceTaskEmpty() {

    }

    private void onPostJsonButtonClick(){
        String username = "c@thy.com";
        String password = "111111";
        PostUrlLoginTask postUrlLoginTask = new PostUrlLoginTask(username, password, this);
        WebServiceTaskManager.startTask(postUrlLoginTask, this);
    }

    @Override
    public void onLoginWebServiceTaskError(ErrorType errorType) {

    }

    @Override
    public void onLoginWebServiceTaskSucceed(UILoginResultInfoEntity entity) {
        Toast.makeText(this, " PostUrlLoginTask = " + entity.getCustomer().getCustomerNumber(),
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLoginWebServiceTaskFailed(UIValidatorInfo entity, String description) {

    }

    @Override
    public void onLoginWebServiceTaskFailedButNoDescription(UIValidatorInfo entity) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.webServiceTaskManagerDemo_getGetUrlShopAllNavigationJsonButton:
                OnGetGetUrlShopAllNavigationJson();
                break;

            case R.id.webServiceTaskManagerDemo_getGetUrlCountryIdJsonButton:
                onGetGetUrlCountryIdJsonButtonClick();
                break;

            case R.id.webServiceTaskManagerDemo_postJsonButton:
                onPostJsonButtonClick();
                break;

            default:
                break;
        }
    }



}
