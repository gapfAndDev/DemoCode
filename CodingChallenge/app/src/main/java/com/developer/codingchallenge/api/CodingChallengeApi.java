package com.developer.codingchallenge.api;

import com.developer.codingchallenge.model.weather.CityWeather;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface CodingChallengeApi {

    @GET("data/2.5//weather")
    Observable<CityWeather> getWeatherByCity(@Query("q") String cityName, @Query("appid") String appId);

}
