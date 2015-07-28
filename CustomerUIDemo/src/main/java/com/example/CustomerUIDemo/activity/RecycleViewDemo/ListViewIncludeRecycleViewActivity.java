
package com.example.CustomerUIDemo.activity.RecycleViewDemo;


import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.CustomerUIDemo.R;
import com.example.CustomerUIDemo.ui.adapter.ListViewIncludeRecycleViewItemAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class ListViewIncludeRecycleViewActivity extends Activity{

    private ListView myListView;
    private ArrayList<HashMap<String, Object>> myList = new ArrayList<HashMap<String, Object>>();
    private ListViewIncludeRecycleViewItemAdapter itemAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_include_recycleview_actvity);


        for (int i = 0; i < 1000; i++) {
            HashMap<String, Object> item = new HashMap<String, Object>();
            item.put("Key "+i, "Value="+i);
            myList.add(item);
        }

        itemAdapter =  new ListViewIncludeRecycleViewItemAdapter(this, myList);

        myListView = (ListView) findViewById(R.id.myListView);
        myListView.setAdapter(itemAdapter);
    }
}
