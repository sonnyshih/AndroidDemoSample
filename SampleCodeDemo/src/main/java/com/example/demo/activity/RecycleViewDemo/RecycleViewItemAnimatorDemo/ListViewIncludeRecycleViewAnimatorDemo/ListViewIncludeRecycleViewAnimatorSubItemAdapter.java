
package com.example.demo.activity.RecycleViewDemo.RecycleViewItemAnimatorDemo.ListViewIncludeRecycleViewAnimatorDemo;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.demo.R;
import com.example.demo.activity.RecycleViewDemo.RecycleViewItemAnimatorDemo.ListViewIncludeRecycleViewAnimatorDemo.entity.SubItem;

import java.util.ArrayList;

public class ListViewIncludeRecycleViewAnimatorSubItemAdapter extends RecyclerView.Adapter<ListViewIncludeRecycleViewAnimatorSubItemAdapter.ViewHolder>{

    private ArrayList<SubItem> subItemList;

    // Provide a suitable constructor (depends on the kind of dataset)
    public ListViewIncludeRecycleViewAnimatorSubItemAdapter(ArrayList<SubItem> subItemList){
        this.subItemList = subItemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_subitem_row, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtHeader.setText(subItemList.get(position).getTitle());
        holder.txtFooter.setText("Footer: " + subItemList.get(position).getFooter());

    }

    @Override
    public int getItemCount() {
        return subItemList.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtHeader;
        public TextView txtFooter;

        public ViewHolder(View v) {
            super(v);
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
            txtFooter = (TextView) v.findViewById(R.id.secondLine);
        }
    }
}
