package com.example.demo.activity.RecycleViewDemo.RecycleViewItemAnimatorDemo.ListViewIncludeRecycleViewAnimatorDemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import com.example.demo.R;
import com.example.demo.activity.RecycleViewDemo.RecycleViewItemAnimatorDemo.ListViewIncludeRecycleViewAnimatorDemo.entity.MainItem;
import com.example.demo.activity.RecycleViewDemo.RecycleViewItemAnimatorDemo.ListViewIncludeRecycleViewAnimatorDemo.entity.SubItem;

import java.util.ArrayList;

public class ListViewIncludeRecycleViewAnimatorActivity extends AppCompatActivity{

    private ListView myListView;
    private ListViewIncludeRecycleViewAnimatorAdapter itemAdapter;

    private ArrayList<MainItem> mainItemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_include_recycleview_actvity);

        for (int i=0; i<10; i++) {
            MainItem mainItem = new MainItem();
            mainItem.setMainTitle("Main Title " + i);
            mainItem.setSubItemList(getSubItems());
            mainItemList.add(mainItem);
        }

        itemAdapter =  new ListViewIncludeRecycleViewAnimatorAdapter(this, mainItemList);

        myListView = (ListView) findViewById(R.id.myListView);
        myListView.setAdapter(itemAdapter);
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
