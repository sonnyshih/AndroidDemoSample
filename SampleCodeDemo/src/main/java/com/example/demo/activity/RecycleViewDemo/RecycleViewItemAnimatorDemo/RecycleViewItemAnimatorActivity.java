
package com.example.demo.activity.RecycleViewDemo.RecycleViewItemAnimatorDemo;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.demo.R;
import com.example.demo.ui.adapter.RecycyleViewAdapter;

import java.util.ArrayList;

public class RecycleViewItemAnimatorActivity extends AppCompatActivity {

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
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        myDataset = getMyDataset();
        // specify an adapter (see also next example)
        mAdapter = new SampleRecyclerAdapter(myDataset);
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
