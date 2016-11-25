
package com.example.demo.activity.RecycleViewDemo.RecycleViewItemAnimatorDemo.RecycleViewDefaultAnimatorDemo;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.demo.R;

import java.util.ArrayList;

public class RecyclerViewItemDefaultAnimatorAdapter extends
        RecyclerView.Adapter<RecyclerViewItemDefaultAnimatorAdapter.ViewHolder> {

    private ArrayList<String> mDataSet;

    public RecyclerViewItemDefaultAnimatorAdapter(ArrayList<String> mDataSet){
        this.mDataSet = mDataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycleview_item_default_animator_adapter, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final String name = mDataSet.get(position);
        holder.txtHeader.setText(mDataSet.get(position));
        holder.txtFooter.setText("Footer: " + mDataSet.get(position));
    }

    @Override
    public int getItemCount() {

        return mDataSet.size();
    }

//    public void add(int position, String item) {
//        mDataSet.add(position, item);
//        notifyItemInserted(position);
//    }
//
//    public void remove(String item) {
//        int position = mDataSet.indexOf(item);
//        mDataSet.remove(position);
//        notifyItemRemoved(position);
//    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtHeader;
        public TextView txtFooter;

        public ViewHolder(View v) {
            super(v);
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
            txtFooter = (TextView) v.findViewById(R.id.secondLine);
        }
    }
}
