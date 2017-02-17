
package com.example.demo.activity.UseLinearLayouToDisplayFullGridViewDemo.FullGridViewUseViewHolderDemo;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.demo.R;

public class MyAdapter extends BaseRecyclerViewAdapter{



    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case 1:
                return new ViewHolder1(inflater.inflate(R.layout.full_grid_view_adapter1, parent, false));

            case 2:
                return new ViewHolder2(inflater.inflate(R.layout.full_grid_view_adapter2, parent, false));

            default:
                return null;
        }
    }

}
