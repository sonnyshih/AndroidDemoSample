
package com.example.demo.activity.CardViewDemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.demo.R;


public class CardViewRadiusAndElevationActivity extends Activity {
    private CardView mCardView;

    private SeekBar mRadiusSeekBar;
    private TextView radiusNumberTextView;

    private SeekBar mElevationSeekBar;
    private TextView elevationNumberTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cardview_radius_and_elevation_demo);
        init();
    }

    private void init() {
        mCardView = (CardView) findViewById(R.id.cardview);

        radiusNumberTextView = (TextView) findViewById(R.id.cardview_radiusNumberTextView);
        mRadiusSeekBar = (SeekBar) findViewById(R.id.cardview_radius_seekbar);
        mRadiusSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d("Mylog", String.format("SeekBar Radius progress : %d", progress));
                mCardView.setRadius(progress);
                radiusNumberTextView.setText(Integer.toString(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //Do nothing
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //Do nothing
            }
        });

        elevationNumberTextView = (TextView) findViewById(R.id.cardview_elevationNumberTextView);
        mElevationSeekBar = (SeekBar) findViewById(R.id.cardview_elevation_seekbar);
        mElevationSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d("Mylog", String.format("SeekBar Elevation progress : %d", progress));

                mCardView.setElevation(progress);
                elevationNumberTextView.setText(Integer.toString(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //Do nothing
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //Do nothing
            }
        });
    }
}
