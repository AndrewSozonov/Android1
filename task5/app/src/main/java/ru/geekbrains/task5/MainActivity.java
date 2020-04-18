package ru.geekbrains.task5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;



public class MainActivity extends AppCompatActivity {
    public static final String TEMP_FIELD_KEY = "TEMP_FIELD";
    public static final String WIND_FIELD_KEY = "WIND_FIELD";
    public static final String PRESSURE_FIELD_KEY = "PRESSURE_FIELD";
    static final String BROADCAST_ACTION_REQUEST_COMPLETED = "REQUEST_COMPLETED";
    private Toolbar toolbar;
    private boolean temperatureSettings;
    private boolean windSettings;
    private boolean pressureSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTheme(R.style.AppThemeDark);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_about:
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(R.string.developed);
                builder.setCancelable(false);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
                return true;
            case R.id.action_settings:
                startSettingsActivity();

        }

        return super.onOptionsItemSelected(item);
    }

    public void startSettingsActivity() {
        Intent intent = new Intent(this, SettingsActivity.class);

        intent.putExtra(TEMP_FIELD_KEY, temperatureSettings);
        intent.putExtra(WIND_FIELD_KEY, windSettings);
        intent.putExtra(PRESSURE_FIELD_KEY, pressureSettings);
        startActivityForResult(intent, 1);

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(1,1,data);
        if (data == null) {return;}
        temperatureSettings = data.getBooleanExtra(TEMP_FIELD_KEY, false);
        windSettings = data.getBooleanExtra(WIND_FIELD_KEY, false);
        pressureSettings = data.getBooleanExtra(PRESSURE_FIELD_KEY, false);
    }

    public boolean isTemperatureSettings() {
        return temperatureSettings;
    }

    public boolean isWindSettings() {
        return windSettings;
    }

    public boolean isPressureSettings() {
        return pressureSettings;
    }
}
