package com.example.demo.activity.UseLinearLayouToDisplayFullGridViewDemo.CustomLinearLayoutDemo;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.demo.R;
import com.example.demo.activity.UseLinearLayouToDisplayFullGridViewDemo.entity.Data;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{
    private ArrayList<Data> list = new ArrayList<Data>();

    public CustomAdapter(ArrayList<Data> list) {
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.full_grid_view_adapter1, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.titleTextView.setText(list.get(position).getTitle());
        holder.descriptionTextView.setText(list.get(position).getDescription());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private TextView descriptionTextView;

        public MyViewHolder(View itemView) {
            super(itemView);

            titleTextView = (TextView) itemView.findViewById(R.id.fullGridView_titleTextView);
            descriptionTextView = (TextView) itemView.findViewById(R.id.fullGridView_descriptionTextView);
        }
    }
}
