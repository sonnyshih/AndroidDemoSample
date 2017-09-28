package com.example.demo.activity.BottomSheetDemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import com.example.demo.R;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;

public class BottomSheetDemoActivity extends AppCompatActivity implements OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_sheet_demo_activity);

        Button showOptionBottomSheetButton = (Button) findViewById(R.id.BottomSheetDemo_showOptionBottomSheetButton);
        showOptionBottomSheetButton.setOnClickListener(this);
    }

    private void layoutBottomSheet(){
        final List<String> options = new ArrayList<String>();
        options.add("Hello");
        options.add("Hi World");
        options.add("Good");

        final BottomSheet optionDialog = new BottomSheet(this);

        OptionsBottomSheetAdapter adapter = new OptionsBottomSheetAdapter(this, options);
        optionDialog.setAdapter(adapter);
        optionDialog.setListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showToast(options.get(position));
                optionDialog.dismiss();
            }
        });

        optionDialog.show();
    }

    private void showToast(String message){
        Toast.makeText(this, message, LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.BottomSheetDemo_showOptionBottomSheetButton:
                layoutBottomSheet();
                break;
        }

    }
}
