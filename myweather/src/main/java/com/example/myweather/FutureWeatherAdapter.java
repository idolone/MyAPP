package com.example.myweather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FutureWeatherAdapter extends RecyclerView.Adapter<FutureWeatherAdapter.MyViewHolder> {

    private Context mContext;
    LayoutInflater mlayoutInflater;

    private List<DayWeatherBean> mWeathers;

    public FutureWeatherAdapter(Context mContext, List<DayWeatherBean> mWeathers) {
        this.mContext = mContext;
        this.mWeathers = mWeathers;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.weather_item_layout,null);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        DayWeatherBean dayWeather = mWeathers.get(position);

        holder.ivWeather.setImageResource(getImgResOfWeather(dayWeather.getWea_img()));
        holder.tvWeather.setText(dayWeather.getWea());
        holder.tvTem.setText(dayWeather.getTem());
        holder.tvDate.setText(dayWeather.getDate());
        holder.tvAir.setText("空气:"+dayWeather.getAir()+dayWeather.getAir_level());
        holder.tvWin.setText(dayWeather.getWin()[0]+dayWeather.getWin_speed());
        holder.tvTemLowHigh.setText(dayWeather.getTem2()+"~"+dayWeather.getTem1());
    }

    private int getImgResOfWeather(String weaStr){
        // xue、lei、shachen、wu、bingbao、yun、yu、yin、qing
        int result = 0;
        switch(weaStr){
            case "qing":
                result = R.drawable.sunny_day;
                break;
            case "yin":
                result = R.drawable.cloud1;
                break;
            case "yu":
                result = R.drawable.rain;
                break;
            case "yun":
                result = R.drawable.cloud0;
                break;
            case "bingbao":
                result = R.drawable.lei_ice_day;
                break;
            case "wu":
                result = R.drawable.fog0;
                break;
            case "shachen":
                result = R.drawable.sand;
                break;
            case "lei":
                result = R.drawable.lei_rain_day;
                break;
            case "xue":
                result = R.drawable.snow0;
                break;

        }
        return result;

    }

    @Override
    public int getItemCount() {

        return (mWeathers == null ? 0 : mWeathers.size());
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tvWeather,tvTem,tvTemLowHigh,tvWin,tvAir,tvDate;
        ImageView ivWeather;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvWeather = itemView.findViewById(R.id.tv_weather);
            tvTem = itemView.findViewById(R.id.tv_tem);
            tvTemLowHigh = itemView.findViewById(R.id.tv_tem_low_high);
            tvWin = itemView.findViewById(R.id.tv_win);
            tvAir = itemView.findViewById(R.id.tv_air);
            tvDate = itemView.findViewById(R.id.tv_date);
            ivWeather = itemView.findViewById(R.id.iv_weather);

        }
    }
}
