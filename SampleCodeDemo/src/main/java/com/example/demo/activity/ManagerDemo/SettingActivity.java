
package com.example.demo.activity.ManagerDemo;


import android.os.Bundle;
import android.view.MenuItem;

import com.example.demo.R;

public class SettingActivity extends BaseNavigationDrawerActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_activity);
        overridePendingTransition(R.anim.sliding_in_right, R.anim.sliding_out_right);
        initToolbarNavigationHome(0, "", true, true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.sliding_in_left, R.anim.sliding_out_left);
    }

    @Override
    protected GlobalLeftDrawerPage getCurrentPageType() {
        return GlobalLeftDrawerPage.Setting;
    }
}
