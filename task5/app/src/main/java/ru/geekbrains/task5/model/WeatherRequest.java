package ru.geekbrains.task5.model;
import com.google.gson.annotations.SerializedName;

public class WeatherRequest {


    @SerializedName("list")
    private List[] list;

    @SerializedName("city")
    private City city;

    public List[] getList() {return list;}

    public void setList(List[] list) {
        this.list = list;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
