package com.example.demo.activity.swipeRefreshDemo;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.demo.R;

public class SwipeRefreshDemoActivity extends Activity {
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swipe_refresh_layout);

        initView();
    }

    private void initView() {
//        final int start = 150;
//        final int end = 220;

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(onSwipeToRefresh);
        swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_red_light,
                android.R.color.holo_blue_light,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light);

//        swipeRefreshLayout.setProgressViewOffset(true, start, end);

        ListView lstData = (ListView) findViewById(R.id.lstData);
        lstData.setAdapter(getAdapter());
        lstData.setOnScrollListener(onListScroll);
    }


    private SwipeRefreshLayout.OnRefreshListener onSwipeToRefresh = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            swipeRefreshLayout.setRefreshing(true);     // Start refreshing.

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    swipeRefreshLayout.setRefreshing(false);    // Stop refreshing
                    Toast.makeText(getApplicationContext(), "Refresh done!", Toast.LENGTH_SHORT).show();
                }
            }, 3000);


        }
    };

    private ArrayAdapter<String> getAdapter() {
        //fake data
        String[] data = new String[20];
        int len = data.length;
        for (int i = 0; i < len; i++) {
            data[i] = Double.toString(Math.random() * 1000);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String> (this,
                android.R.layout.simple_list_item_1, data);
        return adapter;
    }

    private OnScrollListener onListScroll = new OnScrollListener() {

        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {

        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem,
                             int visibleItemCount, int totalItemCount) {
            if (firstVisibleItem == 0) {
                swipeRefreshLayout.setEnabled(true);
            } else {
                swipeRefreshLayout.setEnabled(false);
            }
        }
    };

}
