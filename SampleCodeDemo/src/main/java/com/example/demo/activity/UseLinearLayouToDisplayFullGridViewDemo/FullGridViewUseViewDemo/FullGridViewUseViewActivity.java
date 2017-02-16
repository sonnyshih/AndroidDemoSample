
package com.example.demo.activity.UseLinearLayouToDisplayFullGridViewDemo.FullGridViewUseViewDemo;


import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo.R;
import com.example.demo.activity.UseLinearLayouToDisplayFullGridViewDemo.entity.Data;

import java.util.ArrayList;

public class FullGridViewUseViewActivity extends Activity implements OnClickListener {

    private ArrayList<Data> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_grid_view_use_view_activity);

        list = generateList();
        layoutFullGridView();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        layoutFullGridView();

    }

    private void layoutFullGridView() {

        int column = Integer.valueOf(getResources().getString(R.string.column_number));
        final LinearLayout mainLinearLayout = (LinearLayout) findViewById(R.id.fullGridViewUseView_mainLinear);

        FullyGridLinearLayout fullyGridLinearLayout = new FullyGridLinearLayout(this,
                mainLinearLayout,
                R.layout.full_grid_view_adapter1,
                new FullyGridLinearLayout.FullyGridLinearLayoutListener() {

                    @Override
                    public void setSubItemListener(View convertView, int position,
                                                   ViewGroup.LayoutParams columnLayoutParams,
                                                   int viewType) {

                        String title = "";
                        String description = "";
                        if (position < list.size()) {
                            title = list.get(position).getTitle();
                            description = list.get(position).getDescription();
                        }

                        LinearLayout layout = (LinearLayout) convertView.findViewById(R.id.fullGridView_itemLayout);
                        layout.setLayoutParams(columnLayoutParams);

                        TextView titleTextView = (TextView) convertView.findViewById(R.id.fullGridView_titleTextView);
                        titleTextView.setText(title);
                        titleTextView.setVisibility(viewType);

                        TextView descriptionTextView = (TextView) convertView.findViewById(R.id.fullGridView_descriptionTextView);
                        descriptionTextView.setText(description);
                        descriptionTextView.setVisibility(viewType);

                    }
                }
                , list, column);

        fullyGridLinearLayout.setSubItemOnClickListener(this);
        fullyGridLinearLayout.startLayout();

    }

    private ArrayList<Data> generateList() {

        ArrayList<Data> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Data data = new Data();
            data.setTitle("Title " + i);
            data.setDescription("Description " + i);
            list.add(data);
        }

        return list;

    }

    @Override
    public void onClick(View view) {
        int position = (int) view.getTag();
        Toast.makeText(this, "position = " + position,
                Toast.LENGTH_SHORT).show();
    }

}
