package com.example.CustomerUIDemo.ui.adapter;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.CustomerUIDemo.R;
import com.example.CustomerUIDemo.ui.FullyGridLayoutManager;

import java.util.ArrayList;
import java.util.HashMap;

public class ListViewIncludeRecycleViewItemAdapter extends BaseAdapter {

    private ArrayList<HashMap<String, Object>> myList = new ArrayList<HashMap<String, Object>>();
    private LayoutInflater myInflater;
    private Context context;

    public ListViewIncludeRecycleViewItemAdapter(Context context,
                                                 ArrayList<HashMap<String, Object>> myList) {
        this.context = context;
        this.myList = myList;
        myInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return myList.size();
    }

    @Override
    public HashMap<String, Object> getItem(int position) {
        return myList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {

            convertView = myInflater.inflate(R.layout.listview_include_recycleview_item_adapter, null);
            viewHolder = new ViewHolder();
            viewHolder.setView(convertView);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.keyTextView.setText("Key item " + position);
        viewHolder.valueTextView.setText(myList.get(position).get("Key " + position).toString());

        // Setting the column on portrait or landscape.
        int column = 1;
        Configuration config = context.getResources().getConfiguration();
        if (config.orientation == Configuration.ORIENTATION_PORTRAIT) {
            column = 2;
        } else if (config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            column = 4;
        }

//        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, column);
        viewHolder.recyclerView.setItemAnimator(new DefaultItemAnimator());
        viewHolder.recyclerView.setHasFixedSize(true);

        // By Grid type to show the recycle view
        viewHolder.recyclerView.setLayoutManager(new FullyGridLayoutManager(context, column));

        // By Linear type to show the recycle view
//        viewHolder.recyclerView.setLayoutManager(new FullyLinearLayoutManager(context));



        ArrayList<String> myDataset = getMyDataset();
        RecyclerView.Adapter mAdapter = new ListViewIncludeRecycyleViewSubitemAdapter(myDataset);
        viewHolder.recyclerView.setAdapter(mAdapter);

        // Setting the recycleView height
//        int total = myDataset.size() / column;
//        if (myDataset.size() % column > 0) {
//            total++;
//        }
//
//        viewHolder.recyclerView.getLayoutParams().height = total * ScreenUtil.getPxByDp(context, 70);

        return convertView;
    }


    private ArrayList<String> getMyDataset() {
        ArrayList<String> myDataset = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            myDataset.add("Hello " + i);
        }
        return myDataset;
    }

    private class ViewHolder {
        private TextView keyTextView;
        private TextView valueTextView;
        private RecyclerView recyclerView;

        private void setView(View convertView) {
            keyTextView = (TextView) convertView.findViewById(R.id.Key);
            valueTextView = (TextView) convertView.findViewById(R.id.value);
            recyclerView = (RecyclerView) convertView.findViewById(R.id.adpater_recycler_view);
        }

    }


}

