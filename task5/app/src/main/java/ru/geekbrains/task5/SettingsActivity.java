package ru.geekbrains.task5;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class SettingsActivity extends AppCompatActivity {

    private CheckBox humidityCheckBox;
    private CheckBox windCheckBox;
    private CheckBox pressureCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        humidityCheckBox = findViewById(R.id.settings_humidity);
        windCheckBox = findViewById(R.id.settings_windSpeed);
        pressureCheckBox = findViewById(R.id.settings_atmosphere);

        humidityCheckBox.setChecked(getIntent().getBooleanExtra(Constants.HUMIDITY_FIELD_KEY, false));
        windCheckBox.setChecked(getIntent().getBooleanExtra(Constants.WIND_FIELD_KEY, false));
        pressureCheckBox.setChecked(getIntent().getBooleanExtra(Constants.PRESSURE_FIELD_KEY, false));

        Button buttonOk = findViewById(R.id.button_ok);
        buttonOk.setOnClickListener(buttonOkClickListener);

    }

    View.OnClickListener buttonOkClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            humidityCheckBox = findViewById(R.id.settings_humidity);
            windCheckBox = findViewById(R.id.settings_windSpeed);
            pressureCheckBox = findViewById(R.id.settings_atmosphere);
            Intent intent = new Intent();
            intent.putExtra(Constants.HUMIDITY_FIELD_KEY, humidityCheckBox.isChecked());
            intent.putExtra(Constants.WIND_FIELD_KEY, windCheckBox.isChecked());
            intent.putExtra(Constants.PRESSURE_FIELD_KEY, pressureCheckBox.isChecked());
            setResult(RESULT_OK, intent);
            finish();
        }
    };
}
