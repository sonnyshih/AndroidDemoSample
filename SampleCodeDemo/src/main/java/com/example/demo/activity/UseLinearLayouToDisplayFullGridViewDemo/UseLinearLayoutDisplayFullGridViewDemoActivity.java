
package com.example.demo.activity.UseLinearLayouToDisplayFullGridViewDemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.demo.R;
import com.example.demo.activity.UseLinearLayouToDisplayFullGridViewDemo.CustomLinearLayoutDemo.CustomLinearLayoutDemoActivity;
import com.example.demo.activity.UseLinearLayouToDisplayFullGridViewDemo.FullGridViewUseViewDemo.FullGridViewUseViewActivity;
import com.example.demo.activity.UseLinearLayouToDisplayFullGridViewDemo.FullGridViewUseViewHolderDemo.FullGridViewUseViewHolderDemoActivity;

public class UseLinearLayoutDisplayFullGridViewDemoActivity extends AppCompatActivity
        implements OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.use_linear_layout_display_full_grid_view_demo);

        Button useViewButton =(Button) findViewById(R.id.useLinearLayoutDisplayFullGridViewDemo_useViewButton);
        useViewButton.setOnClickListener(this);

        Button useViewHolderButton =(Button) findViewById(R.id.useLinearLayoutDisplayFullGridViewDemo_useViewHolderButton);
        useViewHolderButton.setOnClickListener(this);

        Button customLinearLayoutButton =(Button) findViewById(R.id.useLinearLayoutDisplayFullGridViewDemo_customLinearLayoutButton);
        customLinearLayoutButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();

        switch (view.getId()) {
            case R.id.useLinearLayoutDisplayFullGridViewDemo_useViewButton:
                intent.setClass(this, FullGridViewUseViewActivity.class);
                break;

            case R.id.useLinearLayoutDisplayFullGridViewDemo_useViewHolderButton:
                intent.setClass(this, FullGridViewUseViewHolderDemoActivity.class);
                break;

            case R.id.useLinearLayoutDisplayFullGridViewDemo_customLinearLayoutButton:
                intent.setClass(this, CustomLinearLayoutDemoActivity.class);
                break;

            default:
                break;
        }

        startActivity(intent);
    }
}
