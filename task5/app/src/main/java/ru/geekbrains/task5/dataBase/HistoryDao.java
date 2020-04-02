package ru.geekbrains.task5.dataBase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;
import ru.geekbrains.task5.dataBase.History;


@Dao
public interface HistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertHistoryNote(History history);

    @Update
    void updateHistoryNote(History history);

    @Delete
    void deleteHistoryNote(History history);

    @Query("DELETE FROM history WHERE id = :id")
    void deleteHistoryById(long id);

    @Query("DELETE FROM history")
    void deleteHistory();

    @Query("SELECT * FROM history")
    List<History> getAllHistory();

    @Query("SELECT * FROM history WHERE id = :id")
    History getHistoryById(long id);

    @Query("SELECT COUNT() FROM history")
    long getCountHistory();
}
