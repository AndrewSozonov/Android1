package ru.geekbrains.task5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import java.util.ArrayList;
public class HistoryActivity extends AppCompatActivity {

    ArrayList<String> citiesHistory;
    float[] temperatureHistory;
    private RecyclerView historyRecyclerView;
    private HistoryRecyclerAdapter historyRecyclerAdapter;
    public static final String historyKey = "HISTORY";
    public static final String historyTemperatureKey = "HISTORY_TEMPERATURE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_layout);
        citiesHistory = getIntent().getStringArrayListExtra(historyKey);
        temperatureHistory = getIntent().getFloatArrayExtra(historyTemperatureKey);

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
