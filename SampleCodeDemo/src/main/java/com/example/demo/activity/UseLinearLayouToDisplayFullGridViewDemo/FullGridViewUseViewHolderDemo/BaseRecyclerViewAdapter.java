package com.example.demo.activity.UseLinearLayouToDisplayFullGridViewDemo.FullGridViewUseViewHolderDemo;


import android.os.Handler;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecyclerViewAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    protected final List<ViewHolderInfo> viewHolderInfos = new ArrayList<>();
    private Handler handler = new Handler();


    @Override
    public int getItemViewType(int position) {
        return viewHolderInfos.get(position).getItemViewType();
    }

    @Override
    public int getItemCount() {
        return viewHolderInfos.size();
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.bindView(viewHolderInfos.get(position));
    }

    public void add(int index, ViewHolderInfo info) {
        add(index, 1, info);
    }

    public void add(int index, int spanCount, ViewHolderInfo info) {
        synchronized (viewHolderInfos) {
            viewHolderInfos.add(index, info);
            int positionStart = (index - spanCount) < 0 ? 0 : index - spanCount;
//            notifyItemRangeChanged(positionStart, spanCount * 2 + 1);
            notifyItemRangeChanged(positionStart, getItemCount());
        }
    }

    public void addAll(ViewHolderInfo holderInfo, List<ViewHolderInfo> addViewHolderList) {
        int position = viewHolderInfos.indexOf(holderInfo);
        if (position == -1) {
            return;
        }
        viewHolderInfos.addAll(position + 1, addViewHolderList);
    }

    public void add(ViewHolderInfo holderInfo, ViewHolderInfo addViewHolder) {
        int position = viewHolderInfos.indexOf(holderInfo);
        if (position == -1) {
            return;
        }
        viewHolderInfos.add(position + 1, addViewHolder);
    }

    public void addAll(List<ViewHolderInfo> addViewHolderList) {
        synchronized (viewHolderInfos) {
            viewHolderInfos.addAll(addViewHolderList);
            notifyDataSetChanged();
        }
    }

    public void remove(int index) {
        remove(index, 1);
    }

    public void remove(final int index, int spanCount) {
        synchronized (viewHolderInfos) {
            viewHolderInfos.remove(index);
            notifyItemRemoved(index);

            //to update layout, delayMillis is 120 because recycler view remove animation  duration - Blake.C.Deng 2015/09/24
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    notifyItemRangeChanged(index, getItemCount());
                }
            }, 120);
//            notifyItemRangeChanged(index - spanCount, spanCount * 2 + 1);
        }
    }

    public void remove(ViewHolderInfo holderInfo) {
        for (int i = 0; i < viewHolderInfos.size(); i++) {
            if (viewHolderInfos.get(i) == holderInfo) {
                remove(i);
                break;
            }
        }
    }

    public int getViewHolderPosition(ViewHolderInfo holderInfo){
        return viewHolderInfos == null ? 0 : viewHolderInfos.indexOf(holderInfo);
    }

    public void clear() {
        synchronized (viewHolderInfos) {
            viewHolderInfos.clear();
            notifyDataSetChanged();
        }
    }
}
