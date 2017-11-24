package sonny.com.googlecaptcha;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;



public class MainActivity extends AppCompatActivity implements OnClickListener{

    /* 參考: https://developer.android.com/training/safetynet/recaptcha.html*/

    private static String API_SITE_KEY = "6Ld1NzoUAAAAANkMUNZYoj_F4bA0yRreXdBgcNLF";
    private TextView textView;
    private TextView tokenTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button sendButton =(Button) findViewById(R.id.main_sendButton);
        sendButton.setOnClickListener(this);

        textView = (TextView) findViewById(R.id.main_textView);
        tokenTextView = (TextView) findViewById(R.id.main_tokenTextView);
    }

    private void onSendCaptchaClick(){
        SafetyNet.getClient(this).verifyWithRecaptcha(API_SITE_KEY)
                .addOnSuccessListener(this, new OnSuccessListener<SafetyNetApi.RecaptchaTokenResponse>() {
                    @Override
                    public void onSuccess(SafetyNetApi.RecaptchaTokenResponse response) {
                        if (!response.getTokenResult().isEmpty()) {
                            //handleSiteVerify(response.getTokenResult());
                            textView.setText("onSuccess");
                            tokenTextView.setText(response.getTokenResult());
                        }
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if (e instanceof ApiException) {
                            ApiException apiException = (ApiException) e;

                            textView.setText("Error message: " + CommonStatusCodes.getStatusCodeString(apiException.getStatusCode()));
//                            Log.d("Mylog", "Error message: " + CommonStatusCodes.getStatusCodeString(apiException.getStatusCode()));
                        } else {
//                            Log.d("Mylog", "Unknown type of error: " + e.getMessage());
                            textView.setText("Unknown type of error: " + e.getMessage());
                        }
                    }
                });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_sendButton:
                onSendCaptchaClick();
                break;
            default:
                break;
        }
    }
}
