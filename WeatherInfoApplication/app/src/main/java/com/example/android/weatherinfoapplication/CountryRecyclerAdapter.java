package com.example.android.weatherinfoapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.weatherinfoapplication.Models.SearchWeather;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CountryRecyclerAdapter extends RecyclerView.Adapter<CountryRecyclerAdapter.MyViewHolder> {

    private List<WeatherInfoModel> list;
    private static int item_position = -1;
    private static String humidity;
    private static String temp;
    private static String pressure;
    private static String speed;
    private static String description;
    private boolean flag = true;

    private Context context;
    private SOService mService = ApiUtils.getSOService();
    private final static String key = "3daed2b6ed998ca0a36a2aba288eac33";


    public CountryRecyclerAdapter(List<WeatherInfoModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclercountry, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.tvCountry.setText(list.get(position).getCountry());
        holder.imgCountry.setImageResource(list.get(position).getImageCountry());

        for (int i = 0; i < list.size(); i++) {
            if (item_position != position) {
                holder.informLayout.setVisibility(View.GONE);
            } else {
                holder.informLayout.setVisibility(View.VISIBLE);
            }
        }

        if (humidity != null) {
            holder.tvHumidity.setText(humidity);
            Log.i("ssssssssssssssssss", "humditi" + humidity);
        }
        if (temp != null) {
            holder.tvTemp.setText(temp);
        }
        if (pressure != null) {
            holder.tvPressure.setText(pressure);
        }
        if (speed != null) {
            holder.tvSpeed.setText(speed);
        }
        if (description != null) {
            holder.tvDescription.setText(description);
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (holder.informLayout.getVisibility() < 4) {
                    holder.informLayout.setVisibility(View.GONE);
                    item_position = -1;
                    notifyDataSetChanged();
                } else {
                    holder.informLayout.setVisibility(View.VISIBLE);
                    getWeatherInfo(list.get(position).getCountry(), key, holder);
                    item_position = position;
//                    holder.tvHumidity.setText(humidity);
//                    holder.tvTemp.setText(temp);
//                    holder.tvPressure.setText(pressure);
//                    holder.tvSpeed.setText(speed);
//                    holder.tvDescription.setText(description);
                }
                notifyDataSetChanged();
            }
        });

    }

    private void getWeatherInfo(String city, String key, final MyViewHolder holder) {
        Call<SearchWeather> call = mService.getWeatherInfo(city, key);
        call.enqueue(new Callback<SearchWeather>() {
            @Override
            public void onResponse(Call<SearchWeather> call, Response<SearchWeather> response) {

                if (response.isSuccessful()) {
                    SearchWeather searchWeather = response.body();
                    int x = (int) Math.round(searchWeather.getMain().getTemp() - 273.15);
                    humidity = "Humidity is " + searchWeather.getMain().getHumidity();
                    temp = "Temp is " + x + "C" + "\u00b0";
                    pressure = "Pressure is" + searchWeather.getMain().getPressure().toString() + " P";
                    speed = "Speed of wind " + searchWeather.getWind().getSpeed() + "m/s";
                    description = searchWeather.getWeather().get(0).getDescription();

                    if (flag) {
                        notifyDataSetChanged();
                        flag = false;
                    }
                } else {
                    Toast.makeText(context, "Sorry,but i can't find data :)", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SearchWeather> call, Throwable t) {
                Toast.makeText(context, "Sorry,but i can't find data :)", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgCountry;
        private TextView tvCountry;
        private TextView tvHumidity;
        private TextView tvTemp;
        private TextView tvPressure;
        private TextView tvSpeed;
        private TextView tvDescription;
        private LinearLayout informLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            imgCountry = itemView.findViewById(R.id.img_country);
            tvCountry = itemView.findViewById(R.id.tv_country);
            tvHumidity = itemView.findViewById(R.id.tv_Humidity);
            tvTemp = itemView.findViewById(R.id.tv_Temp);
            tvPressure = itemView.findViewById(R.id.tv_pressure);
            tvSpeed = itemView.findViewById(R.id.tv_speed);
            tvDescription = itemView.findViewById(R.id.tv_description);
            informLayout = itemView.findViewById(R.id.inform_layout);

        }
    }
}
