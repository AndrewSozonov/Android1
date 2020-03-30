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

    private Toolbar toolbar;
    private boolean humiditySettings;
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

        intent.putExtra(Constants.HUMIDITY_FIELD_KEY, humiditySettings);
        intent.putExtra(Constants.WIND_FIELD_KEY, windSettings);
        intent.putExtra(Constants.PRESSURE_FIELD_KEY, pressureSettings);
        startActivityForResult(intent, 1);

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(1,1,data);
        if (data == null) {return;}
        humiditySettings = data.getBooleanExtra(Constants.HUMIDITY_FIELD_KEY, false);
        windSettings = data.getBooleanExtra(Constants.WIND_FIELD_KEY, false);
        pressureSettings = data.getBooleanExtra(Constants.PRESSURE_FIELD_KEY, false);
    }

    public boolean isHumiditySettings() { return humiditySettings; }

    public boolean isWindSettings() {
        return windSettings;
    }

    public boolean isPressureSettings() {
        return pressureSettings;
    }
}
