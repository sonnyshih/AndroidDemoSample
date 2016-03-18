package com.example.demo.activity.base;

import com.example.demo.R;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;

public class BaseFragment extends Fragment{

	private ProgressDialog loadingProgressDialog;
	private AlertDialog errorAlertDialog;

	protected AlertDialog showErrorAlertDialog(String errorMessage){
		
		if (errorAlertDialog == null) {
			errorAlertDialog = new AlertDialog.Builder(getActivity())
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
			loadingProgressDialog = new ProgressDialog(getActivity());
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
