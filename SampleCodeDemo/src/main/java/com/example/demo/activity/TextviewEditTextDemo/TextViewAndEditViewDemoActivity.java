package com.example.demo.activity.TextviewEditTextDemo;

import com.example.demo.R;
import com.example.demo.ui.CustomerEditText;
import com.example.demo.ui.CustomerTextView;
import com.example.demo.ui.MarqueeTextView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TextViewAndEditViewDemoActivity extends Activity implements
		OnClickListener {

	public static final int REQUEST_CODE_CHARTLET = 1;
	private static final String START_CHAR = "[";
	private static final String END_CHAR = "]";

	private Button openChatletButton;
	private CustomerEditText customerEditText;

	private Button sendButton;
	private CustomerTextView customerTextView;

	public MarqueeTextView marqueeTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.textview_and_editview_demo);

		marqueeTextView = (MarqueeTextView) findViewById(R.id.main_helloTextView);
		marqueeTextView
				.setText("Hello World!! Hi World!! Test World!! good World!!.... ");
		marqueeTextView.init();

		customerEditText = (CustomerEditText) findViewById(R.id.main_customerEditText);

		openChatletButton = (Button) findViewById(R.id.main_openChatletButton);
		openChatletButton.setOnClickListener(this);

		sendButton = (Button) findViewById(R.id.main_sendButton);
		sendButton.setOnClickListener(this);

		customerTextView = (CustomerTextView) findViewById(R.id.main_customerTextView);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);

		switch (requestCode) {
		case REQUEST_CODE_CHARTLET:

			if (resultCode == RESULT_OK) {
				Bundle bundle = intent.getExtras();
				String ChartletName = bundle
						.getString(ChartletActivity.BUNDLE_STRING_CHARTLET_NAME);
				ChartletName = START_CHAR + ChartletName + END_CHAR;
				String message = customerEditText.getText().toString();

				message += ChartletName;
				customerEditText.setText(message);
				customerEditText.setSelection(message.length());
			}

			break;

		default:
			break;
		}

	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.main_openChatletButton:
			Intent intent = new Intent();
			intent.setClass(this, ChartletActivity.class);
			startActivityForResult(intent, REQUEST_CODE_CHARTLET);

			break;

		case R.id.main_sendButton:
			String message = customerEditText.getText().toString();
			customerTextView.setText(message);

			break;

		default:
			break;
		}
	}

}
