package ru.geekbrains.task5.dataBase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = {@Index(value = {"city", "temperature"})})
public class History {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "city")
    public String city;

    @ColumnInfo(name = "temperature")
    public float temperature;

    @ColumnInfo(name = "date")
    public String date;
}
