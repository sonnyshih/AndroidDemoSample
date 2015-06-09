package com.example.CustomerUIDemo.activity.RecycleViewDemo;


import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.CustomerUIDemo.R;
import com.example.CustomerUIDemo.ui.adapter.RecycyleViewAdapter;

import java.util.ArrayList;

public class RecycleViewActivity extends Activity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<String> myDataset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycleview);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(mLayoutManager);

        myDataset = getMyDataset();
        // specify an adapter (see also next example)
        mAdapter = new RecycyleViewAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);
    }

    private ArrayList<String> getMyDataset() {
        ArrayList<String> myDataset = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            myDataset.add("Hello " + i);
        }
        return myDataset;
    }
}
