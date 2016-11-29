package com.example.demo.activity.RecycleViewDemo.RecycleViewItemAnimatorDemo.RecycleViewIncludeRecycelViewAnimatorDemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import com.example.demo.R;
import com.example.demo.activity.RecycleViewDemo.RecycleViewItemAnimatorDemo.ListViewIncludeRecycleViewAnimatorDemo.ListViewIncludeRecycleViewAnimatorAdapter;
import com.example.demo.activity.RecycleViewDemo.RecycleViewItemAnimatorDemo.ListViewIncludeRecycleViewAnimatorDemo.entity.MainItem;
import com.example.demo.activity.RecycleViewDemo.RecycleViewItemAnimatorDemo.ListViewIncludeRecycleViewAnimatorDemo.entity.SubItem;
import com.example.demo.ui.FullyGridLayoutManager;

import java.util.ArrayList;

public class RecycleViewIncludeRecycleViewAnimatorActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecycleViewIncludeRecycleViewAnimatorAdapter adapter;

    private ArrayList<MainItem> mainItemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycleview_include_recycleview_animator_actvity);

        for (int i=0; i<10; i++) {
            MainItem mainItem = new MainItem();
            mainItem.setMainTitle("Main Title " + i);
            mainItem.setSubItemList(getSubItems());
            mainItemList.add(mainItem);
        }

        adapter =  new RecycleViewIncludeRecycleViewAnimatorAdapter(this, mainItemList);

        recyclerView = (RecyclerView) findViewById(R.id.adpater_recycler_view);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        int column = 1;
        recyclerView.setLayoutManager(new GridLayoutManager(this, column));
        recyclerView.setAdapter(adapter);

    }

    private ArrayList<SubItem> getSubItems(){
        ArrayList<SubItem> list = new ArrayList<>();

        for (int i=0; i<5; i++){
            SubItem subItem = new SubItem();
            subItem.setTitle("Title " + i);
            subItem.setFooter("Footer " + i);
            list.add(subItem);
        }

        return list;
    }
}
