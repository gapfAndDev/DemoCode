package com.developer.codingchallenge.api;

import okhttp3.OkHttpClient;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;

public class CodingChallengeService {

    private static final String BASE_URL = "http://api.openweathermap.org/";

    private CodingChallengeService() {
    }

    public static CodingChallengeApi getCodingChallengeService() {
        OkHttpClient okHttpClient = new OkHttpClient();

        Retrofit builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return builder.create(CodingChallengeApi.class);
    }
}
