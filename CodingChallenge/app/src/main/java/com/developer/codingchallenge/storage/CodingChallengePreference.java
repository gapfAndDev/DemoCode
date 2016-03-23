package com.developer.codingchallenge.storage;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class CodingChallengePreference {

    private static final String NAME = "userData";
    private static final String DATE_NAME = "dateName";
    private static final String TIME_NAME = "timeName";
    private static final String NUMBER_NAME = "numberName";
    private static final String USER_NAME = "userName";
    private static final String FOOD_NAME = "foodName";
    private static final String ID_FOOD_NAME = "idFoodName";
    private static CodingChallengePreference INSTANCE;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    private CodingChallengePreference(Application application) {
        mSharedPreferences = application.getSharedPreferences(NAME, Context.MODE_PRIVATE);
    }

    public static CodingChallengePreference getInstance(Application application) {

        if (!(INSTANCE instanceof CodingChallengePreference)) {
            INSTANCE = new CodingChallengePreference(application);
        }
        return INSTANCE;
    }


    public void setDate(String date) {
        savePreference(DATE_NAME, date);
    }

    public void setTime(String time) {
        savePreference(TIME_NAME, time);
    }

    public void setNumber(String number) {
        savePreference(NUMBER_NAME, number);
    }

    public void setName(String name) {
        savePreference(USER_NAME, name);
    }

    public void setFood(String food) {
        savePreference(FOOD_NAME, food);
    }

    public void setIdFood(int idFood) {
        savePreferenceInt(ID_FOOD_NAME, idFood);
    }


    public String getDate() {
        return loadPreference(DATE_NAME);
    }

    public String getTime() {
        return loadPreference(TIME_NAME);
    }

    public String getNumber() {
        return loadPreference(NUMBER_NAME);
    }

    public String getUserName() {
        return loadPreference(USER_NAME);
    }

    public String getFood() {
        return loadPreference(FOOD_NAME);
    }

    public int getIdFood() {
       return loadPreferenceInt(ID_FOOD_NAME);
    }


    private String loadPreference(String preferenceName) {
        return mSharedPreferences.getString(preferenceName, "");
    }

    private void savePreference(String preferenceName, String data) {

        if (!(mEditor instanceof SharedPreferences.Editor)) {
            mEditor = mSharedPreferences.edit();
        }
        mEditor.putString(preferenceName, data)
                .apply();
    }

    private int loadPreferenceInt(String preferenceName) {
        return mSharedPreferences.getInt(preferenceName, 999999);
    }

    private void savePreferenceInt(String preferenceName, int data) {

        if (!(mEditor instanceof SharedPreferences.Editor)) {
            mEditor = mSharedPreferences.edit();
        }
        mEditor.putInt(preferenceName, data)
                .apply();
    }

}
