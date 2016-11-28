package com.example.demo.activity.RecycleViewDemo.RecycleViewItemAnimatorDemo.ListViewIncludeRecycleViewAnimatorDemo;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.demo.R;
import com.example.demo.activity.RecycleViewDemo.RecycleViewItemAnimatorDemo.ListViewIncludeRecycleViewAnimatorDemo.entity.MainItem;
import com.example.demo.activity.RecycleViewDemo.RecycleViewItemAnimatorDemo.ListViewIncludeRecycleViewAnimatorDemo.entity.SubItem;
import com.example.demo.activity.RecycleViewDemo.RecycleViewItemAnimatorDemo.animators.SlideInRightAnimator;
import com.example.demo.ui.FullyGridLayoutManager;

import java.util.ArrayList;

public class ListViewIncludeRecycleViewAnimatorAdapter extends BaseAdapter {

    private ArrayList<MainItem> mainItemList = new ArrayList<>();
    private LayoutInflater myInflater;
    private Context context;

//    private ViewHolder viewHolder;
//    private RecyclerView.Adapter mAdapter;

    public ListViewIncludeRecycleViewAnimatorAdapter(Context context, ArrayList<MainItem> mainItemList) {
        this.context = context;
        this.mainItemList = mainItemList;
        myInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mainItemList.size();
    }

    @Override
    public MainItem getItem(int position) {
        return mainItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder;

        if (convertView == null) {

            convertView = myInflater.inflate(R.layout.listview_include_recycleview_animator_adapter, null);
            viewHolder = new ViewHolder();
            viewHolder.setView(convertView);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.keyTextView.setText("Key item " + position);
        viewHolder.valueTextView.setText(mainItemList.get(position).getMainTitle());

        SlideInRightAnimator itemAnimator = new SlideInRightAnimator();
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


        return convertView;
    }


    private class ViewHolder {
        private TextView keyTextView;
        private TextView valueTextView;
        private RecyclerView recyclerView;
        private Button addButton;
        private Button deleteButton;

        private void setView(View convertView) {
            keyTextView = (TextView) convertView.findViewById(R.id.Key);
            valueTextView = (TextView) convertView.findViewById(R.id.value);
            recyclerView = (RecyclerView) convertView.findViewById(R.id.adpater_recycler_view);
            addButton = (Button) convertView.findViewById(R.id.addButton);
            deleteButton = (Button) convertView.findViewById(R.id.deleteButton);
        }

    }


}
