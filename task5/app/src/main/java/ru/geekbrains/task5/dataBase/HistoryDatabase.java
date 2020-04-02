package ru.geekbrains.task5.dataBase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import ru.geekbrains.task5.dataBase.History;
import ru.geekbrains.task5.dataBase.HistoryDao;

@Database(entities = {History.class}, version = 1, exportSchema = false)
public abstract class HistoryDatabase extends RoomDatabase{

    public abstract HistoryDao getHistoryDao();
}
