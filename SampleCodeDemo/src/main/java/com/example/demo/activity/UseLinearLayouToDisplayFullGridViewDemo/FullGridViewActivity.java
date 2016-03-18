
package com.example.demo.activity.UseLinearLayouToDisplayFullGridViewDemo;


import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.demo.R;

import java.util.ArrayList;

public class FullGridViewActivity extends Activity {

    private ArrayList<Data> list;

    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_grid_view_activity);
        inflater = LayoutInflater.from(this);
        list = generateList();

        final LinearLayout mainLinearLayout = (LinearLayout) findViewById(R.id.fullGridView_mainLinear);
        mainLinearLayout.post(new Runnable() {

            @Override
            public void run() {
                layoutView(mainLinearLayout.getWidth());
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        final LinearLayout mainLinearLayout = (LinearLayout) findViewById(R.id.fullGridView_mainLinear);
        mainLinearLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                layoutView(mainLinearLayout.getWidth());
            }


        }, 500);


    }

    private void layoutView(double width) {
        int column = Integer.valueOf(getResources().getString(R.string.column_number));

        LinearLayout mainLinearLayout = (LinearLayout) findViewById(R.id.fullGridView_mainLinear);
        mainLinearLayout.removeAllViews();

        int row = 0;

        int b = list.size() % column;

        if (b > 0) {
            row = list.size() / column + 1;
        } else {
            row = list.size() / column;
        }

        for (int i = 0; i < row; i++) {

            LinearLayout.LayoutParams myLayoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout.setLayoutParams(myLayoutParams);
            linearLayout.setBackgroundColor(Color.GREEN);

            for (int j = 0; j < column; j++) {

                int index = i * column + j;

                View convertView = inflater.inflate(R.layout.full_grid_view_adapter, null);
                final LinearLayout layout = (LinearLayout) convertView.findViewById(R.id.fullGridView_itemLayout);

                ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams((int) width/column, ViewGroup.LayoutParams.MATCH_PARENT);
                layout.setLayoutParams(layoutParams);

                TextView titleTextView = (TextView) convertView.findViewById(R.id.fullGridView_titleTextView);
                TextView descriptionTextView = (TextView) convertView.findViewById(R.id.fullGridView_descriptionTextView);

                if (index < list.size()) {

                    if (index ==0 || index==3 || index==5 || index==9 || index== 11) {
                        titleTextView.setText("asdfadfasdfasdfasdfasdfasdfasdfasdfssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss");

                    } else {
                        titleTextView.setText(list.get(index).getTitle());
                    }

                    descriptionTextView.setText(list.get(index).getDescription());

                } else {
                    titleTextView.setVisibility(View.GONE);
                    descriptionTextView.setVisibility(View.GONE);
                }

                linearLayout.addView(convertView);

            }


            mainLinearLayout.addView(linearLayout);
        }

    }


    private ArrayList<Data> generateList() {

        ArrayList<Data> list = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            Data data = new Data();
            data.setTitle("Title " + i);
            data.setDescription("Description " + i);
            list.add(data);
        }

        return list;

    }


    public class Data {
        private String title;
        private String description;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
