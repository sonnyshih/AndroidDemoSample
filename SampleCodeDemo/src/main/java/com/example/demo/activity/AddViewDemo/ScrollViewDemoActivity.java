package com.example.demo.activity.AddViewDemo;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.demo.R;

public class ScrollViewDemoActivity extends AppCompatActivity implements OnClickListener {

    private ScrollView mainScrollView;
    private LinearLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scrollview_demo_activity);

        mainScrollView = (ScrollView) findViewById(R.id.scrollView_mainScrollView);
        Button goToButton = (Button) findViewById(R.id.scrollView_goToButton);
        goToButton.setOnClickListener(this);


        mainLayout = (LinearLayout) findViewById(R.id.scrollView_mainLayout);
        mainLayout.removeAllViews();

        for (int i = 0; i < 20; i++) {
            layoutContent(i);
        }

    }

    private void layoutContent(int position) {
        View questionView = LayoutInflater.from(this).inflate(R.layout.scrollview_demo_question, null);
        TextView positionTextView = (TextView) questionView.findViewById(R.id.scrollviewDemoQuestion_positionTextView);
        positionTextView.setText("Position: " + position);

        questionView.setTag(position);

        mainLayout.addView(questionView);
    }

    private void onGoToClickButton(){
        View view = mainLayout.findViewWithTag(15);
        Log.d("Mylog", "x="+view.getX());
        Log.d("Mylog", "y="+view.getY());
        mainScrollView.smoothScrollTo(0, Math.round(view.getY()));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.scrollView_goToButton:
                onGoToClickButton();
                break;

            default:
                break;
        }
    }
}
