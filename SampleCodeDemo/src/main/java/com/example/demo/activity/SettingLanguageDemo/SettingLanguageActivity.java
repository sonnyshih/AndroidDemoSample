package com.example.demo.activity.SettingLanguageDemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.example.demo.R;
import com.example.demo.activity.SettingLanguageDemo.manager.SettingLanguageManager;
import com.example.demo.activity.SettingLanguageDemo.manager.SettingLanguageManager.Language;
import com.example.demo.activity.home.MainActivity;

public class SettingLanguageActivity extends AppCompatActivity implements OnCheckedChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_language_activity);

        // Setting the default language(setLanguage()) in the SampleCodeDemoApp.java.
        initView();
    }

    private void initView() {
        initLanguageRadioGroup();
    }

    private void initLanguageRadioGroup() {

        Language language = SettingLanguageManager.getInstance().getLanguage();

        switch (language) {
            case Simplified:
                break;

            case Traditional:
                RadioButton traditionRadioButton = (RadioButton) findViewById(R.id.setting_traditionalRadioButton);
                traditionRadioButton.setChecked(true);
                break;

            case English:
            default:
                RadioButton englishRadioButton = (RadioButton) findViewById(R.id.setting_englishRadioButton);
                englishRadioButton.setChecked(true);
                break;
        }

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.setting_radioGroup);
        radioGroup.setOnCheckedChangeListener(this);
    }

    private void changeLanguage(Language language){
        SettingLanguageManager.getInstance().changeLanguage(language);
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(MainActivity.BUNDLE_BOOLEAN_IS_CHANGE_LANGUAGE, true);
        startActivity(intent);
        finish();
    }



    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.setting_englishRadioButton:
                changeLanguage(Language.English);
                break;

            case R.id.setting_traditionalRadioButton:
                changeLanguage(Language.Traditional);
                break;

            default:
                break;
        }
    }
}
