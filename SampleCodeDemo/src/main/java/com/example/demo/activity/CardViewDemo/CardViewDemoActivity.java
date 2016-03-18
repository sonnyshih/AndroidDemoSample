
package com.example.demo.activity.CardViewDemo;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.demo.R;

public class CardViewDemoActivity extends Activity implements View.OnClickListener{

    private Button cardviewListDemoButton;
    private Button radiusAndElevationDemoButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cardview_demo);

        cardviewListDemoButton = (Button) findViewById(R.id.cardview_demo_CardviewListDemoButton);
        cardviewListDemoButton.setOnClickListener(this);

        radiusAndElevationDemoButton = (Button) findViewById(R.id.cardview_demo_radiusAndElevationDemoButton);
        radiusAndElevationDemoButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent();

        switch (view.getId()){
            case R.id.cardview_demo_CardviewListDemoButton:
                intent.setClass(this, CardViewListActivity.class);
                break;

            case R.id.cardview_demo_radiusAndElevationDemoButton:
                intent.setClass(this, CardViewRadiusAndElevationActivity.class);
                break;

            default:
                break;
        }

        startActivity(intent);
    }
}
