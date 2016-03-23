package com.developer.codingchallenge;

import android.app.Application;

import com.developer.codingchallenge.api.CodingChallengeApi;
import com.developer.codingchallenge.api.CodingChallengeService;
import com.developer.codingchallenge.storage.CodingChallengePreference;

public class CodingChallengeApplication extends Application {
    private static CodingChallengeApplication INSTANCE;

    private CodingChallengeApi mCodingChallengeApi;

    private CodingChallengePreference mCodingChallengePreference;

    public static CodingChallengeApplication getInstance() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if (!(INSTANCE instanceof CodingChallengeApplication)) {
            INSTANCE = (CodingChallengeApplication) getApplicationContext();
        }
        ApiInitialization();
        SharedPreferenceInitialization();

    }

    private void SharedPreferenceInitialization() {
        mCodingChallengePreference = CodingChallengePreference.getInstance(this);
    }

    private void ApiInitialization() {
        mCodingChallengeApi = CodingChallengeService.getCodingChallengeService();
    }

    public CodingChallengeApi getCodingChallengeApi() {
        return mCodingChallengeApi;
    }

    public CodingChallengePreference getCodingChallengePreference() {
        return mCodingChallengePreference;
    }



}
