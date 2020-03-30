package ru.geekbrains.task5.model;
import com.google.gson.annotations.SerializedName;

public class Weather {

    @SerializedName("main")
    private String main;

    @SerializedName("description")
    private String description;

    @SerializedName("id")
    private int id;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public int getId() {
        return id;
    }
}
