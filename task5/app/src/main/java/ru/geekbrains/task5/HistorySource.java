package ru.geekbrains.task5;

import java.util.List;

import ru.geekbrains.task5.dataBase.HistoryDao;
import ru.geekbrains.task5.dataBase.History;

public class HistorySource {

    private final HistoryDao historyDao;

    private List<History> histories;

    public HistorySource(HistoryDao historyDao) {
        this.historyDao = historyDao;
    }

    public List<History> getHistories() {
        if (histories == null) {
            loadHistories();
        }
        return histories;
    }

    public void loadHistories() {
        histories = historyDao.getAllHistory();
    }

    public long getCountHistory() {
        return historyDao.getCountHistory();
    }

    public void addHistory(History history) {
        historyDao.insertHistoryNote(history);
        loadHistories();
    }

    public void updateHistory(History history) {
        historyDao.updateHistoryNote(history);
        loadHistories();
    }

    public void removeHistoryNote(long id) {
        historyDao.deleteHistoryById(id);
        loadHistories();
    }

    public void deleteHistory() {
        historyDao.deleteHistory();
        loadHistories();
    }
}
