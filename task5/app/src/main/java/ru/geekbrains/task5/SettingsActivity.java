package ru.geekbrains.task5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class SettingsActivity extends AppCompatActivity {

    private CheckBox temperatureCheckBox;
    private CheckBox windCheckBox;
    private CheckBox pressureCheckBox;
    public static final String TEMP_FIELD_KEY = "TEMP_FIELD";
    public static final String WIND_FIELD_KEY = "WIND_FIELD";
    public static final String PRESSURE_FIELD_KEY = "PRESSURE_FIELD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        temperatureCheckBox = findViewById(R.id.settings_temperature);
        windCheckBox = findViewById(R.id.settings_windSpeed);
        pressureCheckBox = findViewById(R.id.settings_atmosphere);

        temperatureCheckBox.setChecked(getIntent().getBooleanExtra(TEMP_FIELD_KEY, false));
        windCheckBox.setChecked(getIntent().getBooleanExtra(WIND_FIELD_KEY, false));
        pressureCheckBox.setChecked(getIntent().getBooleanExtra(PRESSURE_FIELD_KEY, false));

        Button buttonOk = findViewById(R.id.button_ok);
        buttonOk.setOnClickListener(buttonOkClickListener);

    }

    View.OnClickListener buttonOkClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            temperatureCheckBox = findViewById(R.id.settings_temperature);
            windCheckBox = findViewById(R.id.settings_windSpeed);
            pressureCheckBox = findViewById(R.id.settings_atmosphere);
            Intent intent = new Intent();
            intent.putExtra(TEMP_FIELD_KEY, temperatureCheckBox.isChecked());
            intent.putExtra(WIND_FIELD_KEY, windCheckBox.isChecked());
            intent.putExtra(PRESSURE_FIELD_KEY, pressureCheckBox.isChecked());
            setResult(RESULT_OK, intent);
            finish();
        }
    };
}
