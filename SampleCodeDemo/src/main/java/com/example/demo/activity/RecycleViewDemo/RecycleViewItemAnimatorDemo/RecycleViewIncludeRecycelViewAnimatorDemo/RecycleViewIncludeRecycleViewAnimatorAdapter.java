package com.example.demo.activity.RecycleViewDemo.RecycleViewItemAnimatorDemo.RecycleViewIncludeRecycelViewAnimatorDemo;


import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.demo.R;
import com.example.demo.activity.RecycleViewDemo.RecycleViewItemAnimatorDemo.ListViewIncludeRecycleViewAnimatorDemo.ListViewIncludeRecycleViewAnimatorSubItemAdapter;
import com.example.demo.activity.RecycleViewDemo.RecycleViewItemAnimatorDemo.ListViewIncludeRecycleViewAnimatorDemo.entity.MainItem;
import com.example.demo.activity.RecycleViewDemo.RecycleViewItemAnimatorDemo.ListViewIncludeRecycleViewAnimatorDemo.entity.SubItem;
import com.example.demo.activity.RecycleViewDemo.RecycleViewItemAnimatorDemo.animators.ShockAnimator;
import com.example.demo.ui.FullyGridLayoutManager;

import java.util.ArrayList;

public class RecycleViewIncludeRecycleViewAnimatorAdapter extends
        RecyclerView.Adapter<RecycleViewIncludeRecycleViewAnimatorAdapter.ViewHolder>{

    private Context context;
    private ArrayList<MainItem> mainItemList = new ArrayList<>();

    public RecycleViewIncludeRecycleViewAnimatorAdapter(Context context, ArrayList<MainItem> mainItemList){
        this.context = context;
        this.mainItemList = mainItemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_include_recycleview_animator_adapter, parent, false);
        // set the view's size, margins, paddings and layout parameters
        RecycleViewIncludeRecycleViewAnimatorAdapter.ViewHolder vh = new RecycleViewIncludeRecycleViewAnimatorAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {

        viewHolder.keyTextView.setText("Key item " + position);
        viewHolder.valueTextView.setText(mainItemList.get(position).getMainTitle());

        ShockAnimator itemAnimator = new ShockAnimator(context);
        itemAnimator.setAddDuration(500);
        itemAnimator.setRemoveDuration(1000);
//        itemAnimator.setAddDuration(500);
        viewHolder.recyclerView.setItemAnimator(itemAnimator);
        viewHolder.recyclerView.setHasFixedSize(true);

        // By Grid type to show the recycle view
        // Setting the column on portrait or landscape.
        int column = 1;
        viewHolder.recyclerView.setLayoutManager(new FullyGridLayoutManager(context, column));


        final ArrayList<SubItem> subItemList = mainItemList.get(position).getSubItemList();
        final RecyclerView.Adapter mAdapter = new ListViewIncludeRecycleViewAnimatorSubItemAdapter(subItemList);
        viewHolder.recyclerView.setAdapter(mAdapter);


        viewHolder.addButton.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view) {
                                                        SubItem subItem = new SubItem();
                                                        subItem.setTitle("Hello");
                                                        subItem.setFooter("hello footer");

                                                        subItemList.add(subItem);
                                                        mAdapter.notifyItemInserted(viewHolder.recyclerView.getAdapter().getItemCount());
                                                        viewHolder.recyclerView.smoothScrollToPosition(viewHolder.recyclerView.getAdapter().getItemCount());
                                                        Handler handler = new Handler();
                                                        Runnable runnable = new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                viewHolder.recyclerView.setLayoutManager(viewHolder.recyclerView.getLayoutManager());
                                                                viewHolder.recyclerView.setAdapter(mAdapter);

                                                            }
                                                        };

                                                        handler.postDelayed(runnable, 1000);

                                                    }
                                                }

        );

        viewHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
                                                       @Override
                                                       public void onClick(View view) {

                                                           if (subItemList.size() == 0) {
                                                               return;
                                                           }
                                                           subItemList.remove(subItemList.size() - 1);
                                                           mAdapter.notifyItemRemoved(viewHolder.recyclerView.getAdapter().getItemCount());
                                                           viewHolder.recyclerView.smoothScrollToPosition(viewHolder.recyclerView.getAdapter().getItemCount());

                                                           Handler handler = new Handler();
                                                           Runnable runnable = new Runnable() {
                                                               @Override
                                                               public void run() {
                                                                   viewHolder.recyclerView.setLayoutManager(viewHolder.recyclerView.getLayoutManager());
                                                                   viewHolder.recyclerView.setAdapter(mAdapter);

                                                               }
                                                           };

                                                           handler.postDelayed(runnable, 1000);

                                                       }
                                                   }

        );

    }

    @Override
    public int getItemCount() {
        return mainItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView keyTextView;
        private TextView valueTextView;
        private RecyclerView recyclerView;
        private Button addButton;
        private Button deleteButton;

        public ViewHolder(View view) {
            super(view);
            keyTextView = (TextView) view.findViewById(R.id.Key);
            valueTextView = (TextView) view.findViewById(R.id.value);
            recyclerView = (RecyclerView) view.findViewById(R.id.adpater_recycler_view);
            addButton = (Button) view.findViewById(R.id.addButton);
            deleteButton = (Button) view.findViewById(R.id.deleteButton);
        }

    }
}
