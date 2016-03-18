package com.example.demo.activity.FragmentDemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.demo.R;
import com.example.demo.activity.FragmentDemo.DynamicDemo.DynamicFragmentActivity;
import com.example.demo.activity.FragmentDemo.FragmentPagerDemo.FragmentPagerActivity;
import com.example.demo.activity.FragmentDemo.StaticDemo.StaticFragmentActivity;

public class FragmentDemoActivity extends AppCompatActivity implements OnClickListener{

    private Button dynamicButton;
    private Button staticButton;
    private Button fragmentPagerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_demo_activity);

        dynamicButton = (Button) findViewById(R.id.main_dynamicButton);
        dynamicButton.setOnClickListener(this);

        staticButton = (Button) findViewById(R.id.main_staticButton);
        staticButton.setOnClickListener(this);

        fragmentPagerButton = (Button) findViewById(R.id.main_fragmentPagerButton);
        fragmentPagerButton.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {

        Intent intent = new Intent();

        switch (view.getId()) {
            case R.id.main_dynamicButton:
                intent.setClass(this, DynamicFragmentActivity.class);
                startActivity(intent);

                break;

            case R.id.main_staticButton:
                intent.setClass(this, StaticFragmentActivity.class);
                startActivity(intent);

                break;

            case R.id.main_fragmentPagerButton:
                intent.setClass(this, FragmentPagerActivity.class);
                startActivity(intent);

                break;

            default:
                break;
        }
    }
}
