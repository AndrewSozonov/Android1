package ru.geekbrains.task1_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city);

        TextView temperatureView = findViewById(R.id.fieldTemperature);
        TextView windView = findViewById(R.id.fieldWind);
        TextView pressureView = findViewById(R.id.fieldPressure);

        boolean tempCheckBox = getIntent().getExtras().getBoolean("temperatureCheckBox");
        boolean windCheckBox = getIntent().getExtras().getBoolean("windCheckBox");
        boolean pressureCheckBox = getIntent().getExtras().getBoolean("pressureCheckBox");

        if (tempCheckBox) {
            temperatureView.setVisibility(View.VISIBLE);
        }
        if (windCheckBox) {
            windView.setVisibility(View.VISIBLE);
        }
        if (pressureCheckBox) {
            pressureView.setVisibility(View.VISIBLE);
        }
    }
}
