package ru.geekbrains.task5.model;
import com.google.gson.annotations.SerializedName;

public class Wind {

    @SerializedName("speed")
    private float speed;

    @SerializedName("deg")
    private int deg;

    public int getDeg() {
        return deg;
    }

    public void setDeg(int deg) {
        this.deg = deg;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

}
