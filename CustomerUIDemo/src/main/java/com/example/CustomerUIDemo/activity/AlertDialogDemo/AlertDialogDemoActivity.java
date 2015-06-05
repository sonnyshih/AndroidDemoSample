package com.example.CustomerUIDemo.activity.AlertDialogDemo;

import com.example.CustomerUIDemo.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class AlertDialogDemoActivity extends Activity implements OnClickListener{
	
	private Button standardAlertDialogDemoButton;
	private Button customerAlertDialogDemoButton;
	private Button listAlertDialogDemoButton;
	private Button progressAlertDialogDemoButton;
	private Button radioBoxAlertDialogDemoButton;
	private Button checkBoxAlertDialogDemoButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.alert_dialog_demo_activity);
		
		standardAlertDialogDemoButton = (Button) findViewById(R.id.alertDialog_standardAlertDialogDemoButton);
		standardAlertDialogDemoButton.setOnClickListener(this);
		
		customerAlertDialogDemoButton = (Button) findViewById(R.id.alertControllerDemo_repeatAlertButton);
		customerAlertDialogDemoButton.setOnClickListener(this);
		
		listAlertDialogDemoButton = (Button) findViewById(R.id.alertDialog_listAlertDialogDemoButton);
		listAlertDialogDemoButton.setOnClickListener(this);
		
		progressAlertDialogDemoButton = (Button) findViewById(R.id.alertDialog_progressAlertDialogDemoButton);
		progressAlertDialogDemoButton.setOnClickListener(this);
		
		radioBoxAlertDialogDemoButton = (Button) findViewById(R.id.alertDialog_radioBoxAlertDialogDemoButton);
		radioBoxAlertDialogDemoButton.setOnClickListener(this);
		
		checkBoxAlertDialogDemoButton = (Button) findViewById(R.id.alertDialog_checkBoxAlertDialogDemoButton);
		checkBoxAlertDialogDemoButton.setOnClickListener(this);
		
		
	}

	// Standard Alert Dialog
	// Alert Dialog provide three Buttons (setNegativeButton, setNeutralButton, setPositiveButton)
	private void onStandardAlertDialogClick(){
		
		/** AlertDialog.THEME_DEVICE_DEFAULT_DARK: setting which one theme */
		// AlertDialog.Builder standartAlertDialog = new
		// AlertDialog.Builder(this, AlertDialog.THEME_DEVICE_DEFAULT_DARK);

		
		//Use the default theme
		AlertDialog.Builder standartAlertDialog = new AlertDialog.Builder(this);	
		standartAlertDialog.setCancelable(false);	// setting that click outside to close Alert Dialog or not.
		standartAlertDialog.setTitle("Confirm Window");
		standartAlertDialog.setIcon(android.R.drawable.ic_menu_info_details);
		standartAlertDialog.setMessage(R.string.alertDialogDemo_standardAlertDialogDemoMessage);
		
		// Left Button
		standartAlertDialog.setNegativeButton("Left Button", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
			}
		});

		// Middle Button
		standartAlertDialog.setNeutralButton("Middle Button", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {

            }
        });
		
		// Right Button
		standartAlertDialog.setPositiveButton("Right Button", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int position) {
				
			}
		});
		
		standartAlertDialog.show();
		
		
	}
	
	// Customer Alert Dialog
	private void onCustomerAlertDialogClick(){
		
		LayoutInflater layoutInflater = LayoutInflater.from(this);
		View textEntryView = layoutInflater.inflate(R.layout.alert_dialog_demo_login_form, null);
		
		AlertDialog.Builder customerAlertDialog = new AlertDialog.Builder(this);
		customerAlertDialog.setTitle("Login Info");
		customerAlertDialog.setIcon(android.R.drawable.ic_menu_info_details);
		customerAlertDialog.setView(textEntryView);
		
		// Setting Middle Button
		customerAlertDialog.setNeutralButton("Login", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
            }
        });
		customerAlertDialog.show();

	}
	
	// List Alert Dialog
	private void onListAlertDialogClick(){
		final String[] items = {"Command one", "Command two", "Command three", "Command four"};
		
		AlertDialog.Builder listAlertDialog = new AlertDialog.Builder(this);
		listAlertDialog.setTitle("List Info");
		listAlertDialog.setIcon(android.R.drawable.ic_menu_info_details);
		listAlertDialog.setItems(items, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int position) {
				Toast.makeText(getApplicationContext(),
						"You selected:" + position + " - " + items[position],
						Toast.LENGTH_SHORT).show();
			}
		});
		
		listAlertDialog.show();
		
	}
	
	// Progress Alert Dialog
	private ProgressDialog progressAlertDialog;
	private static final int MAX_PROGRESS = 100;
	private int progress;
	private Handler progressHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (progress >= MAX_PROGRESS) {
            	progressAlertDialog.dismiss();
            } else {
                progress++;
                progressAlertDialog.incrementProgressBy(1);
                progressHandler.sendEmptyMessageDelayed(0, 100);
            }
        }
    };
    

	private void onProgressAlertDialogClick(){
		
		progressAlertDialog = new ProgressDialog(this);
		progressAlertDialog.setTitle("Copy Progress");
		progressAlertDialog.setIcon(android.R.drawable.ic_menu_info_details);
		progressAlertDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		progressAlertDialog.setMax(MAX_PROGRESS);

		progressAlertDialog.setButton(DialogInterface.BUTTON_POSITIVE,"Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

            }
        });
		
		progressAlertDialog.setButton(DialogInterface.BUTTON_NEGATIVE,"Hide", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

            }
        });
		
		progressAlertDialog.show();
		
	    progress = 0;
	    progressAlertDialog.setProgress(0);
	    progressHandler.sendEmptyMessage(0);

	}
	
	// RadioBox Alert Dialog
	private void onRadioBoxAlertDialogClick(){
		final String[] chices = {"Map", "Satellite", "Traffic", "Street view"};
		AlertDialog.Builder radioBoxChoiceAlertDialog = new AlertDialog.Builder(this);
		radioBoxChoiceAlertDialog.setTitle("RadioBox List");
		radioBoxChoiceAlertDialog.setIcon(android.R.drawable.ic_menu_info_details);
		radioBoxChoiceAlertDialog.setSingleChoiceItems(chices, 0 , new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int position) {
				Toast.makeText(getApplicationContext(),
						"You choose:" + position + " - " + chices[position],
						Toast.LENGTH_SHORT).show();
				dialog.dismiss();
			}
		});
		
		radioBoxChoiceAlertDialog.show();

	}
	
	// CheckBox Alert Dialog
	private void onCheckBoxAlertDialogClick(){
		final String[] checkBoxItems = {"Map", "Satellite", "Traffic", "Street view"};
		final boolean[] isCheckBoxChecked = {false, true, false, true};
		
		AlertDialog.Builder checkBoxChoiceAlertDialog = new AlertDialog.Builder(this);
		checkBoxChoiceAlertDialog.setTitle("CheckBox List");
		checkBoxChoiceAlertDialog.setIcon(android.R.drawable.ic_menu_info_details);
		
		checkBoxChoiceAlertDialog.setMultiChoiceItems(checkBoxItems,
				isCheckBoxChecked,
				new DialogInterface.OnMultiChoiceClickListener() {
					public void onClick(DialogInterface dialog, int poision,
							boolean isChecked) {
						/* User clicked on a check box do some stuff */
						isCheckBoxChecked[poision] = isChecked;
						Log.d("Mylog", "poision = " + poision + "##Check="+isChecked);
					}
				});

		// Middle Button
		checkBoxChoiceAlertDialog.setNeutralButton("Send", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				for (boolean isChecked : isCheckBoxChecked) {
					Log.d("Mylog", "isCheck="+isChecked);
				}
            }
        });
		
		checkBoxChoiceAlertDialog.show();

		
	}
	
	
	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.alertDialog_standardAlertDialogDemoButton:
			onStandardAlertDialogClick();
			break;

		case R.id.alertControllerDemo_repeatAlertButton:
			onCustomerAlertDialogClick();
			break;

		case R.id.alertDialog_listAlertDialogDemoButton:
			onListAlertDialogClick();
			break;

		case R.id.alertDialog_progressAlertDialogDemoButton:
			onProgressAlertDialogClick();
			break;

		case R.id.alertDialog_radioBoxAlertDialogDemoButton:
			onRadioBoxAlertDialogClick();
			break;

		case R.id.alertDialog_checkBoxAlertDialogDemoButton:
			onCheckBoxAlertDialogClick();
			break;

										
		default:
			break;
		}
	}

}
