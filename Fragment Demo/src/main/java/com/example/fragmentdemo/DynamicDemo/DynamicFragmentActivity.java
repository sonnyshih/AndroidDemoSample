package com.example.fragmentdemo.DynamicDemo;

import com.example.fragmentdemo.R;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DynamicFragmentActivity extends FragmentActivity implements OnClickListener{

	private Button dynamicButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dynamic_fragment_activity);
		
		dynamicButton = (Button) findViewById(R.id.dynamicFragment_dynamicButton);
		dynamicButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.dynamicFragment_dynamicButton:
			FragmentManager fragmentManager = getSupportFragmentManager();
			FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
			NewFragment newFragment = new NewFragment();
			fragmentTransaction.add(R.id.dynamic_layout, newFragment);
			fragmentTransaction.commit(); // Must commit
			break;

		default:
			break;
		}
	}

	
}
