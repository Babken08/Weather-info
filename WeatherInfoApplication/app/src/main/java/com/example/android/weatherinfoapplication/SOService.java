package com.example.android.weatherinfoapplication;

import com.example.android.weatherinfoapplication.Models.SearchWeather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SOService {

    @GET("data/2.5/weather")
    Call<SearchWeather> getWeatherInfo(@Query("q") String city, @Query("APPID") String key);

//3daed2b6ed998ca0a36a2aba288eac33
}
