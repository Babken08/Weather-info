package com.example.android.weatherinfoapplication;

public class ApiUtils {

    public static final String BASE_URL = "http://api.openweathermap.org/";

    public static SOService getSOService() {
        return RetrofitClient.getClient(BASE_URL).create(SOService.class);
    }
}
