package com.sonny.firebasecloudmessage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;

public class MainActivity extends AppCompatActivity implements OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button getTokenButton = (Button) findViewById(R.id.main_getTokenButton);
        getTokenButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.main_getTokenButton:
//                FirebaseApp.initializeApp(this);
//             Get token
                String token = FirebaseInstanceId.getInstance().getToken();

//                 Log and toast
                String msg = getString(R.string.msg_token_fmt, token);
                Log.d("Mylog", msg);
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }

    }
}
