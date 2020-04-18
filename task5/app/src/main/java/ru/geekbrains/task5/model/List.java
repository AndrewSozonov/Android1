package ru.geekbrains.task5.model;

import com.google.gson.annotations.SerializedName;

public class List {

    @SerializedName("main")
    private Main main;


    @SerializedName("wind")
    private Wind wind;


    @SerializedName("dt_txt")
    private String date;

    @SerializedName("weather")
    private Weather[] weather;


    public Main getMain() {
        return main;
    }

    public Wind getWind() {
        return wind;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Weather[] getWeather() {
        return weather;
    }

    public void setWeather(Weather[] weather) {
        this.weather = weather;
    }
}
