package com.example.demo.activity.BottomSheetDemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.demo.R;

import java.util.List;

public class OptionsBottomSheetAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<String> options;

    public OptionsBottomSheetAdapter(Context context, List<String> options){
        this.context = context;
        this.options = options;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return options.size();
    }

    @Override
    public Object getItem(int position) {
        return options.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {

            convertView = inflater.inflate(
                    R.layout.options_bottom_sheet_adapter, parent, false);
            viewHolder = new OptionsBottomSheetAdapter.ViewHolder();
            viewHolder.optionTextView = (TextView) convertView
                    .findViewById(R.id.appFeedbackOption_optionTextView);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (OptionsBottomSheetAdapter.ViewHolder) convertView.getTag();
        }

        layoutOption(viewHolder, position);

        return convertView;
    }

    private void layoutOption(ViewHolder viewHolder, int position){
        viewHolder.optionTextView.setText(options.get(position));
    }

    private class ViewHolder {
        TextView optionTextView;
    }
}

