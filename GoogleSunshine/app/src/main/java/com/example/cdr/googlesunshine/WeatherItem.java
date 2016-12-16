package com.example.cdr.googlesunshine;

/**
 * Created by cdr on 12/12/16.
 */

public class WeatherItem {
    private String mStringTime;
    private Long mLongTime;
    private double mTemperature;
    private String mWeather;
    private int mID;

    public WeatherItem() {

    }

    public WeatherItem(int aID, String stringTime, Long longTime, double temperature, String weather) {
        this.mStringTime = stringTime;
        this.mLongTime = longTime;
        this.mTemperature = temperature;
        this.mWeather = weather;
        this.mID = aID;
    }

    public String getTime() {
        return mStringTime;
    }

    public void setTime(String stringTime) {
        this.mStringTime = stringTime;
    }

    public Long getLongTime() {
        return mLongTime;
    }

    public void setLongTime(Long longTime) {
        this.mLongTime = longTime;
    }

    public double getTemperature() {
        return mTemperature;
    }

    public void setTemperature(double temperature) {
        this.mTemperature = temperature;
    }

    public String getWeather() {
        return mWeather;
    }

    public void setWeather(String weather) {
        this.mWeather = weather;
    }

    public int getID(){
        return mID;
    }

    public void setID(int ID) {
        this.mID = ID;
    }
}
