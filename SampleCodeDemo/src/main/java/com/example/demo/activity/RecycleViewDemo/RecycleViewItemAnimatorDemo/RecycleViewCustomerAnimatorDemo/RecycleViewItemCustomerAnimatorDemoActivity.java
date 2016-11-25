package com.example.demo.activity.RecycleViewDemo.RecycleViewItemAnimatorDemo.RecycleViewCustomerAnimatorDemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.demo.R;
import com.example.demo.activity.RecycleViewDemo.RecycleViewItemAnimatorDemo.animators.FadeInAnimator;
import com.example.demo.activity.RecycleViewDemo.RecycleViewItemAnimatorDemo.animators.FadeInDownAnimator;
import com.example.demo.activity.RecycleViewDemo.RecycleViewItemAnimatorDemo.animators.FadeInLeftAnimator;
import com.example.demo.activity.RecycleViewDemo.RecycleViewItemAnimatorDemo.animators.FlipInTopXAnimator;
import com.example.demo.activity.RecycleViewDemo.RecycleViewItemAnimatorDemo.animators.LandingAnimator;
import com.example.demo.activity.RecycleViewDemo.RecycleViewItemAnimatorDemo.animators.OvershootInRightAnimator;
import com.example.demo.activity.RecycleViewDemo.RecycleViewItemAnimatorDemo.animators.ScaleInAnimator;
import com.example.demo.activity.RecycleViewDemo.RecycleViewItemAnimatorDemo.animators.ScaleInBottomAnimator;
import com.example.demo.activity.RecycleViewDemo.RecycleViewItemAnimatorDemo.animators.SlideInUpAnimator;

import java.util.ArrayList;

public class RecycleViewItemCustomerAnimatorDemoActivity extends AppCompatActivity
        implements OnClickListener{

    private RecyclerView recyclerView;
    private LayoutManager layoutManager;
    private ArrayList<String> myDataSet;
    private RecyclerViewItemCustomerAnimatorAdapter adapter;

    private Button addButton;
    private Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycleview_item_customer_animator_demo);

        recyclerView = (RecyclerView) findViewById(R.id.recycleViewItemCustomerAnimatorDemo_recyclerView);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // There are many Animator in animators packet.
        recyclerView.setItemAnimator(new SlideInUpAnimator());

        myDataSet = getMyDataSet();
        adapter = new RecyclerViewItemCustomerAnimatorAdapter(myDataSet);
        recyclerView.setAdapter(adapter);

        addButton = (Button) findViewById(R.id.recycleViewItemCustomerAnimatorDemo_addButton);
        addButton.setOnClickListener(this);

        deleteButton = (Button) findViewById(R.id.recycleViewItemCustomerAnimatorDemo_deleteButton);
        deleteButton.setOnClickListener(this);

    }

    private ArrayList<String> getMyDataSet() {
        ArrayList<String> myDataSet = new ArrayList<String>();
        for (int i = 0; i < 5; i++) {
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
            case R.id.recycleViewItemCustomerAnimatorDemo_addButton:
                onClickAddButton();
                break;

            case R.id.recycleViewItemCustomerAnimatorDemo_deleteButton:
                onClickDeleteButton();
                break;

            default:
                break;
        }

    }
}
