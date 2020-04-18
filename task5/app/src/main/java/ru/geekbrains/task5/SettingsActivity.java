package ru.geekbrains.task5;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class SettingsActivity extends AppCompatActivity {

    private CheckBox humidityCheckBox;
    private CheckBox windCheckBox;
    private CheckBox pressureCheckBox;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        humidityCheckBox = findViewById(R.id.settings_humidity);
        windCheckBox = findViewById(R.id.settings_windSpeed);
        pressureCheckBox = findViewById(R.id.settings_atmosphere);

        sharedPreferences = getSharedPreferences(Constants.PREFERENCE_FILE_NAME, MODE_PRIVATE);
        humidityCheckBox.setChecked(sharedPreferences.getBoolean(Constants.HUMIDITY_FIELD_KEY, false));
        windCheckBox.setChecked(sharedPreferences.getBoolean(Constants.WIND_FIELD_KEY, false));
        pressureCheckBox.setChecked(sharedPreferences.getBoolean(Constants.PRESSURE_FIELD_KEY, false));

        Button buttonOk = findViewById(R.id.button_ok);
        buttonOk.setOnClickListener(buttonOkClickListener);

    }

    View.OnClickListener buttonOkClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            humidityCheckBox = findViewById(R.id.settings_humidity);
            windCheckBox = findViewById(R.id.settings_windSpeed);
            pressureCheckBox = findViewById(R.id.settings_atmosphere);

            sharedPreferences = getSharedPreferences(Constants.PREFERENCE_FILE_NAME, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(Constants.HUMIDITY_FIELD_KEY, humidityCheckBox.isChecked());
            editor.putBoolean(Constants.WIND_FIELD_KEY, windCheckBox.isChecked());
            editor.putBoolean(Constants.PRESSURE_FIELD_KEY, pressureCheckBox.isChecked());
            editor.commit();

            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            finish();
        }
    };
}
