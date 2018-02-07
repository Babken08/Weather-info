package com.example.android.weatherinfoapplication;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<WeatherInfoModel> list;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Window window = getWindow();
        window.setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.colorGreen));

        list = new ArrayList<>();
        initList(list);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_country);
        CountryRecyclerAdapter adapter = new CountryRecyclerAdapter(list, MainActivity.this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);


    }



    private void initList(List<WeatherInfoModel> countryList) {

        WeatherInfoModel weatherInfoModelArm = new WeatherInfoModel();
        WeatherInfoModel weatherInfoModelRus = new WeatherInfoModel();
        WeatherInfoModel weatherInfoModelFr = new WeatherInfoModel();
        WeatherInfoModel weatherInfoModelUsa = new WeatherInfoModel();
        WeatherInfoModel weatherInfoModelEng = new WeatherInfoModel();


        weatherInfoModelArm.setCountry(getString(R.string.Yerevan));
        weatherInfoModelArm.setImageCountry(R.mipmap.arm);

        weatherInfoModelRus.setCountry(getString(R.string.moscow));
        weatherInfoModelRus.setImageCountry(R.mipmap.rus);

        weatherInfoModelFr.setCountry(getString(R.string.pariz));
        weatherInfoModelFr.setImageCountry(R.mipmap.fr);

        weatherInfoModelUsa.setCountry(getString(R.string.newyork));
        weatherInfoModelUsa.setImageCountry(R.mipmap.usa);

        weatherInfoModelEng.setCountry(getString(R.string.london));
        weatherInfoModelEng.setImageCountry(R.mipmap.eng);


        countryList.add(weatherInfoModelArm);
        countryList.add(weatherInfoModelRus);
        countryList.add(weatherInfoModelFr);
        countryList.add(weatherInfoModelUsa);
        countryList.add(weatherInfoModelEng);

    }
}
