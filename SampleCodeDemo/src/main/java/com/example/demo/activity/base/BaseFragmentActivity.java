package com.example.demo.activity.base;

import com.example.demo.R;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class BaseFragmentActivity extends FragmentActivity{

	private ProgressDialog loadingProgressDialog;
	private AlertDialog errorAlertDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	protected AlertDialog showErrorAlertDialog(String errorMessage){
		
		if (errorAlertDialog == null) {
			errorAlertDialog = new AlertDialog.Builder(this)
					.setTitle(R.string.app_name)
					.setIcon(android.R.drawable.ic_menu_info_details)
					.setPositiveButton(R.string.ok,
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									dialog.dismiss();
								}
							}).setCancelable(false).create();
		}

		if (errorAlertDialog.isShowing()) {
			return errorAlertDialog;
		}

		errorAlertDialog.setMessage(errorMessage);
		errorAlertDialog.show();
		return errorAlertDialog;
	}
	
	protected void dismissErrorAlertDialog(){
		if (errorAlertDialog != null && errorAlertDialog.isShowing()) {
			errorAlertDialog.dismiss();
		}
	}
	
	protected void showProgressDialog(String title, String message){
		
		if (loadingProgressDialog == null) {
			loadingProgressDialog = new ProgressDialog(this);
			loadingProgressDialog.setIndeterminate(true);
			loadingProgressDialog.setCanceledOnTouchOutside(false);
		}
		
		if (title !=null && !title.isEmpty()) {
			loadingProgressDialog.setTitle(title);
		}
		
		if (message !=null && !message.isEmpty()) {
			loadingProgressDialog.setMessage(message);
		}

		loadingProgressDialog.show();
	}
	
	protected void dismissProgressDialog(){
		if (loadingProgressDialog != null && loadingProgressDialog.isShowing()) {
			loadingProgressDialog.dismiss();
		}
		
	}
	
	
}
