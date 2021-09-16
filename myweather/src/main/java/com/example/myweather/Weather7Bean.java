package com.example.myweather;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Weather7Bean implements Serializable {

    @SerializedName("cityid")
    private String cityid;

    @SerializedName("city")
    private String city;

    @SerializedName("cityEn")
    private String cityEn;

    @SerializedName("country")
    private String country;

    @SerializedName("countryEn")
    private String countryEn;

    @SerializedName("update_time")
    private String update_time;

    @SerializedName("data")
    private List<DayWeatherBean> dayWeathers;

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityEn() {
        return cityEn;
    }

    public void setCityEn(String cityEn) {
        this.cityEn = cityEn;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryEn() {
        return countryEn;
    }

    public void setCountryEn(String countryEn) {
        this.countryEn = countryEn;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public List<DayWeatherBean> getDayWeathers() {
        return dayWeathers;
    }

    public void setDayWeathers(List<DayWeatherBean> dayWeathers) {
        this.dayWeathers = dayWeathers;
    }

    @Override
    public String toString() {
        return "weather7Bean{" +
                "cityid='" + cityid + '\'' +
                ", city='" + city + '\'' +
                ", cityEn='" + cityEn + '\'' +
                ", country='" + country + '\'' +
                ", countryEn='" + countryEn + '\'' +
                ", update_time='" + update_time + '\'' +
                ", dayWeathers=" + dayWeathers +
                '}';
    }
}
