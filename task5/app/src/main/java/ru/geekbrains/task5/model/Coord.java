package ru.geekbrains.task5.model;
import com.google.gson.annotations.SerializedName;

public class Coord {

    @SerializedName("lat")
    private float lat;

    @SerializedName("lon")
    private float lon;

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }
}
