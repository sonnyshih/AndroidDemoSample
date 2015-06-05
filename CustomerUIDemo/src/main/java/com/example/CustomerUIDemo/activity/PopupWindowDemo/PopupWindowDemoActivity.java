package com.example.CustomerUIDemo.activity.PopupWindowDemo;

import com.example.CustomerUIDemo.R;
import com.example.CustomerUIDemo.util.ScreenUtil;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.PopupWindow;

public class PopupWindowDemoActivity extends Activity implements
		OnClickListener {

	private Button standardPopupWindowDemoButton;
	private Button setPositionPopupWindowDemoButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.popup_window_demo_activity);

		standardPopupWindowDemoButton = (Button) findViewById(R.id.popupWindowDemo_standardPopupWindowDemoButton);
		standardPopupWindowDemoButton.setOnClickListener(this);

		setPositionPopupWindowDemoButton = (Button) findViewById(R.id.popupWindowDemo_setPositionPopupWindowDemoButton);
		setPositionPopupWindowDemoButton.setOnClickListener(this);

	}

	// Standard Popup Window
	private void onStandardPopupWindowClick(View view) {
		View popWinView = LayoutInflater.from(this).inflate(
				R.layout.popup_window_demo_form, null);
		
		PopupWindow popupWindow;

		final Resources res = this.getResources();

		// Set the width and the height of the popup window.
		int popupWindowWidth = res.getDimensionPixelSize(R.dimen.popup_window_width);
		int popupWindowHeight = res.getDimensionPixelSize(R.dimen.popup_window_height);

		// Set position of the popup window.
		int popupWindowPositionX = 100;
		int policyPopupWindowPositionY = -100;
		
		popupWindow = new PopupWindow(popWinView, popupWindowWidth, popupWindowHeight, true);
//		popupWindow = new PopupWindow(this);
//		popupWindow.setWidth(popupWindowWidth);
//		popupWindow.setHeight(popupWindowHeight);
//		popupWindow.setContentView(popWinView);
//		popupWindow.setFocusable(true);
		popupWindow.setBackgroundDrawable(new BitmapDrawable());
		popupWindow.setOutsideTouchable(true);

		popupWindow.showAsDropDown(view, popupWindowPositionX, policyPopupWindowPositionY);

	}

	private void onSetPositionPopupWindowClick(View view) {

		PopupWindow popupWindow;

		View popWinView = LayoutInflater.from(this).inflate(
				R.layout.popup_window_demo_form, null);

		// Set the width and the height of the popup window.
		final Resources resources = this.getResources();
		int popupWindowWidth = 0;
		int popupWindowHeight = 0;

		// Set position of the popup window.
		int popupWindowPositionX = 0;
		int policyPopupWindowPositionY = 0;

		switch (this.getResources().getConfiguration().orientation) {

		case Configuration.ORIENTATION_LANDSCAPE:
			popupWindowWidth = resources.getDimensionPixelSize(R.dimen.r450dp);
			popupWindowHeight = resources.getDimensionPixelSize(R.dimen.r325dp);

			popupWindowPositionX = ScreenUtil.getDpByPx(this, view.getWidth());
			policyPopupWindowPositionY = resources
					.getDimensionPixelSize(R.dimen.r150dp);
			break;

		case Configuration.ORIENTATION_PORTRAIT:
			popupWindowWidth = resources.getDimensionPixelSize(R.dimen.r585dp);
			popupWindowHeight = resources.getDimensionPixelSize(R.dimen.r325dp);

			popupWindowPositionX = ScreenUtil.getDpByPx(this,
					-resources.getDimensionPixelSize(R.dimen.r90dp));
			policyPopupWindowPositionY = resources
					.getDimensionPixelSize(R.dimen.r5dp);
			break;
		}

		popupWindow = new PopupWindow(popWinView, popupWindowWidth,
				popupWindowHeight, true);
		popupWindow.setBackgroundDrawable(new BitmapDrawable(getResources()));
		popupWindow.setOutsideTouchable(true);

		popupWindow.showAsDropDown(view,
				ScreenUtil.getPxByDp(this, popupWindowPositionX),
				-policyPopupWindowPositionY);

	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.popupWindowDemo_standardPopupWindowDemoButton:
			onStandardPopupWindowClick(view);
			break;

		case R.id.popupWindowDemo_setPositionPopupWindowDemoButton:
			onSetPositionPopupWindowClick(view);
			break;

		default:
			break;
		}
	}

}
