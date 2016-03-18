package com.example.demo.activity.RecycleViewDemo;


import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.demo.R;
import com.example.demo.ui.adapter.RecycyleViewAdapter;

import java.util.ArrayList;

public class GridLayoutRecycleViewActivity extends Activity {

    private GridLayoutManager gridLayoutManager;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<String> myDataset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycleview);

        layoutGridLayoutManager();
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        myDataset = getMyDataset();
        // specify an adapter (see also next example)
        mAdapter = new RecycyleViewAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        layoutGridLayoutManager();
    }

    private void layoutGridLayoutManager() {
        int dashboardColumn = Integer.valueOf(
                getResources().getString(R.string.column_number));

        if (gridLayoutManager == null) {
            gridLayoutManager = new GridLayoutManager(this, dashboardColumn);
        } else {
            gridLayoutManager.setSpanCount(dashboardColumn);
        }

    }

    private ArrayList<String> getMyDataset() {
        ArrayList<String> myDataset = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            myDataset.add("Hello " + i);
        }
        return myDataset;
    }
}
