package com.sonny.firebaseanalytics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.google.firebase.analytics.FirebaseAnalytics;

public class MainActivity extends AppCompatActivity implements OnClickListener{
    private FirebaseAnalytics firebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAnalytics = FirebaseAnalytics.getInstance(this);

        Button defaultLogEventButton = (Button) findViewById(R.id.main_defaultLogEventButton);
        defaultLogEventButton.setOnClickListener(this);

        Button customerLogEventButton = (Button) findViewById(R.id.main_customerLogEventButton);
        customerLogEventButton.setOnClickListener(this);

        Button userPropertyButton = (Button) findViewById(R.id.main_userPropertyButton);
        userPropertyButton.setOnClickListener(this);

    }

    private String getMaxLengthString(String stringValue){

        if (stringValue == null){
            return "";
        }

        // The max length is 36 in the Analytics.- Sonny Shih 2017/07/23
        if (stringValue.length() > 36) {
            stringValue = stringValue.substring(0, 36);
        }

        return stringValue;
    }

    private void onDefaultLogEventButtonClick(){
        String quantity = "1";
        String itemId = "aaa-123-qqq";
        String itemName = "hello world~ hello Good, nice notebook, phone";
        String itemCategory = "Sony , Acer";
        String currency = "USD";
        String price = "200";
        String value = "300";

        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.QUANTITY, quantity);
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, itemId);
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, getMaxLengthString(itemName));
        bundle.putString(FirebaseAnalytics.Param.ITEM_CATEGORY, getMaxLengthString(itemCategory));
        bundle.putString(FirebaseAnalytics.Param.CURRENCY, currency);
        bundle.putString(FirebaseAnalytics.Param.PRICE, price);
        bundle.putString(FirebaseAnalytics.Param.VALUE, value);
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.ADD_TO_CART, bundle);
    }

    private void onCustomerLogEventButtonClick(){
        String name = "good news";
        String text = "I'd love you to hear about " + name;

        Bundle params = new Bundle();
        params.putString("image_name", name);
        params.putString("full_text", text);
        firebaseAnalytics.logEvent("share_image", params);
    }

    private void onUserPropertyButtonClick(){
        String favoriteFood = "Hot Dogs";
        firebaseAnalytics.setUserProperty("favorite_food", favoriteFood);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_defaultLogEventButton:
                onDefaultLogEventButtonClick();
                break;

            case R.id.main_customerLogEventButton:
                onCustomerLogEventButtonClick();
                break;

            case R.id.main_userPropertyButton:
                onUserPropertyButtonClick();
                break;

            default:
                break;
        }
    }
}
