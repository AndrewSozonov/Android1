package ru.geekbrains.task5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    private ArrayList<String> citiesHistory;
    float[] temperatureHistory;
    private RecyclerView historyRecyclerView;
    private HistoryRecyclerAdapter historyRecyclerAdapter;
    public static final String HISTORY_KEY = "HISTORY";
    public static final String HISTORY_TEMPERATURE_KEY = "HISTORY_TEMPERATURE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_layout);
        citiesHistory = getIntent().getStringArrayListExtra(HISTORY_KEY);
        temperatureHistory = getIntent().getFloatArrayExtra(HISTORY_TEMPERATURE_KEY);

        initRecyclerView(citiesHistory, temperatureHistory);
    }

    private void initRecyclerView(ArrayList<String> citiesHistory, float[] temperatureHistory) {
        historyRecyclerView = findViewById(R.id.history_recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        historyRecyclerView.setLayoutManager(layoutManager);
        historyRecyclerAdapter = new HistoryRecyclerAdapter(citiesHistory,temperatureHistory);
        historyRecyclerView.setAdapter(historyRecyclerAdapter);
    }
}
