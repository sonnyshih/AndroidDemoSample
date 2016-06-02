
package com.example.demo.activity.SettingLanguageDemo.manager;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;

import com.example.demo.manage.ApplicationManager;

import java.util.Locale;

public class SettingLanguageManager {

    public static enum Language {
        None, English, Traditional, Simplified;

        public static Language toLanguageEnum(String enumString) {
            try {
                return valueOf(enumString);
            } catch (Exception ex) {
                // For error cases
                return None;
            }
        }
    }

    public static final String SHARE_PREFERNCE_SETTINGS_SETTING = "SHARE_PREFERNCE_SETTINGS_SETTING";
    public static final String SHARE_PREFERENCE_ENUM_LANGUAGE = "SHARE_PREFERENCE_ENUM_LANGUAGE";

    private static SettingLanguageManager instance;
    private Context context;
    private SharedPreferences settingSharedPreferences;
    private Language language;

    public SettingLanguageManager() {
        this.context = ApplicationManager.getInstance().getContext();
        settingSharedPreferences = context.getSharedPreferences(SHARE_PREFERNCE_SETTINGS_SETTING, Context.MODE_PRIVATE);
    }

    public static SettingLanguageManager getInstance() {
        if (instance == null) {
            instance = new SettingLanguageManager();
        }

        return instance;
    }

    public void setLanguage(Language language) {

        settingSharedPreferences.edit()
                .putString(SHARE_PREFERENCE_ENUM_LANGUAGE, language.toString()).commit();
    }

    public Language getLanguage() {
        String enumLanguageString = settingSharedPreferences.getString(
                SHARE_PREFERENCE_ENUM_LANGUAGE, "");

        return Language.toLanguageEnum(enumLanguageString);

    }


    public void changeLanguage(Language language) {
        setLanguage(language);

        switch (language) {
            case Simplified:
                changeLocaleConfig(Locale.CHINA);
                break;

            case Traditional:
                changeLocaleConfig(Locale.TAIWAN);
                break;

            case English:
            default:
                changeLocaleConfig(Locale.ENGLISH);
                break;
        }
    }

    private void changeLocaleConfig(Locale locale){

        Locale.setDefault(locale);
        Configuration config = new Configuration();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR1) {
            config.setLocale(locale);
        } else {
            config.locale = locale;
        }
        Resources resources = ApplicationManager.getInstance().getContext()
                .getResources();
        resources.updateConfiguration(config, resources.getDisplayMetrics());
    }
}
