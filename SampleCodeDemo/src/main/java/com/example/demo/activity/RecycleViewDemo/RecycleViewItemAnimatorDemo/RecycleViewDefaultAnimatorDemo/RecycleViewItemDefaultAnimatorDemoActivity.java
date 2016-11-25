package com.example.demo.activity.RecycleViewDemo.RecycleViewItemAnimatorDemo.RecycleViewDefaultAnimatorDemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.demo.R;

import java.util.ArrayList;

public class RecycleViewItemDefaultAnimatorDemoActivity extends AppCompatActivity
        implements OnClickListener{

    private RecyclerView recyclerView;
    private LayoutManager layoutManager;
    private ArrayList<String> myDataSet;
    private RecyclerViewItemDefaultAnimatorAdapter adapter;

    private Button addButton;
    private Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycleview_item_default_animator_demo);

        recyclerView = (RecyclerView) findViewById(R.id.recycleViewItemDefaultAnimatorDemo_recyclerView);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        myDataSet = getMyDataSet();
        adapter = new RecyclerViewItemDefaultAnimatorAdapter(myDataSet);
        recyclerView.setAdapter(adapter);

        addButton = (Button) findViewById(R.id.recycleViewItemDefaultAnimatorDemo_addButton);
        addButton.setOnClickListener(this);

        deleteButton = (Button) findViewById(R.id.recycleViewItemDefaultAnimatorDemo_deleteButton);
        deleteButton.setOnClickListener(this);

    }

    private ArrayList<String> getMyDataSet() {
        ArrayList<String> myDataSet = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            myDataSet.add("Hello " + i);
        }
        return myDataSet;
    }

    private void onClickAddButton(){
        myDataSet.add("New Hello");
        adapter.notifyItemInserted(myDataSet.size());
        recyclerView.smoothScrollToPosition(recyclerView.getAdapter().getItemCount());
    }

    private void onClickDeleteButton(){
        if (myDataSet.size() == 0) {
            return;
        }

        myDataSet.remove(myDataSet.size() - 1);
        adapter.notifyItemRemoved(myDataSet.size());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.recycleViewItemDefaultAnimatorDemo_addButton:
                onClickAddButton();
                break;

            case R.id.recycleViewItemDefaultAnimatorDemo_deleteButton:
                onClickDeleteButton();
                break;

            default:
                break;
        }

    }
}
