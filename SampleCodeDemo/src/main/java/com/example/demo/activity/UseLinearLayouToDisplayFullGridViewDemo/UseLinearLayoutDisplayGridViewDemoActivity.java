
package com.example.demo.activity.UseLinearLayouToDisplayFullGridViewDemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.demo.R;
import com.example.demo.activity.UseLinearLayouToDisplayFullGridViewDemo.CustomLinearLayoutDemo.CustomLinearLayoutDemoActivity;
import com.example.demo.activity.UseLinearLayouToDisplayFullGridViewDemo.GridViewUseViewHolderDemo.GridViewUseViewHolderDemoActivity;

public class UseLinearLayoutDisplayGridViewDemoActivity extends AppCompatActivity
        implements OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.use_linear_layout_display_grid_view_demo);

        Button useViewHolderButton =(Button) findViewById(R.id.useLinearLayoutDisplayGridViewDemo_useViewHolderButton);
        useViewHolderButton.setOnClickListener(this);

        Button customLinearLayoutButton =(Button) findViewById(R.id.useLinearLayoutDisplayGridViewDemo_customLinearLayoutButton);
        customLinearLayoutButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();

        switch (view.getId()) {
            case R.id.useLinearLayoutDisplayGridViewDemo_useViewHolderButton:
                intent.setClass(this, GridViewUseViewHolderDemoActivity.class);
                break;

            case R.id.useLinearLayoutDisplayGridViewDemo_customLinearLayoutButton:
                intent.setClass(this, CustomLinearLayoutDemoActivity.class);
                break;

            default:
                break;
        }

        startActivity(intent);
    }
}
