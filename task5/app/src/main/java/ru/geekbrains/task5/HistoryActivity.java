package ru.geekbrains.task5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import java.util.ArrayList;
import ru.geekbrains.task5.dataBase.HistoryDao;
import ru.geekbrains.task5.dataBase.History;
public class HistoryActivity extends AppCompatActivity {

    private ArrayList<String> citiesHistory;
    float[] temperatureHistory;
    private RecyclerView historyRecyclerView;
    private HistoryRecyclerAdapter historyRecyclerAdapter;
    private HistorySource historySource;

    public static final String HISTORY_KEY = "HISTORY";
    public static final String HISTORY_TEMPERATURE_KEY = "HISTORY_TEMPERATURE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_layout);
        citiesHistory = getIntent().getStringArrayListExtra(HISTORY_KEY);
        temperatureHistory = getIntent().getFloatArrayExtra(HISTORY_TEMPERATURE_KEY);

        initRecyclerView();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_context_history, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.remove_context:
                History historyForRemove = historySource.getHistories().get((int) historyRecyclerAdapter.getMenuPosition());
                historySource.removeHistoryNote(historyForRemove.id);
                historyRecyclerAdapter.notifyItemRemoved((int) historyRecyclerAdapter.getMenuPosition());
                return true;
            case R.id.remove_all:
                historySource.deleteHistory();
                historyRecyclerAdapter.notifyDataSetChanged();
                return true;
        }
        return super.onContextItemSelected(item);
    }

    private void initRecyclerView() {
        historyRecyclerView = findViewById(R.id.history_recyclerView);
        historyRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        historyRecyclerView.setLayoutManager(layoutManager);

        HistoryDao historyDao = App
                .getInstance()
                .getHistoryDao();
        historySource = new HistorySource(historyDao);


        historyRecyclerAdapter = new HistoryRecyclerAdapter(historySource, this);
        historyRecyclerView.setAdapter(historyRecyclerAdapter);
    }
}
