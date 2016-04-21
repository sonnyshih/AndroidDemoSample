
package com.example.demo.activity.ManagerDemo;


import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.demo.R;

public class ManagerDemoActivity extends BaseNavigationDrawerActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_demo_activity);

        layoutActionBar();
    }

    private void layoutActionBar(){
        final Toolbar toolbar = getToolbar();
        toolbar.setLogo(R.drawable.ic_home);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        final DrawerLayout leftDrawer = (DrawerLayout) findViewById(R.id.baseNavigationDrawer_DrawerLayout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, leftDrawer, toolbar, R.string.app_name, R.string.app_name) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

            }
        };
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();
        leftDrawer.setDrawerListener(actionBarDrawerToggle);
        leftDrawer.setStatusBarBackgroundColor(getResources().getColor(R.color.dark_blue));
    }

    @Override
    protected GlobalLeftDrawerPage getCurrentPageType() {
        return GlobalLeftDrawerPage.Home;
    }

}
