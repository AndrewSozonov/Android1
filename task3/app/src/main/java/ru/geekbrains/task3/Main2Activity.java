package ru.geekbrains.task3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city);

        TextView temperatureView = findViewById(R.id.fieldTemperature);
        TextView windView = findViewById(R.id.fieldWind);
        TextView pressureView = findViewById(R.id.fieldPressure);
        final TextView fieldCity = findViewById(R.id.fieldCity);


        boolean tempCheckBox = getIntent().getExtras().getBoolean("temperatureCheckBox");
        boolean windCheckBox = getIntent().getExtras().getBoolean("windCheckBox");
        boolean pressureCheckBox = getIntent().getExtras().getBoolean("pressureCheckBox");
        fieldCity.setText(getIntent().getExtras().getString("city"));

        if (tempCheckBox) {
            temperatureView.setVisibility(View.VISIBLE);
        }
        if (windCheckBox) {
            windView.setVisibility(View.VISIBLE);
        }
        if (pressureCheckBox) {
            pressureView.setVisibility(View.VISIBLE);
        }

        Button browser = findViewById(R.id.browser);
        browser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cityBrowser = fieldCity.getText().toString();
                String url = getString(R.string.default_Url);
                switch (cityBrowser) {
                    case ("Moscow"):
                        url = getString(R.string.moscowUrl);
                        break;
                    case ("St.Petersburg"):
                        url = getString(R.string.stPetersburgUrl);
                        break;
                    case ("New York"):
                        url = getString(R.string.newYorkUrl);
                        break;
                    case ("London"):
                        url = getString(R.string.londonUrl);
                        break;
                    case ("Paris"):
                        url = getString(R.string.parisUrl);
                        break;
                    case ("Dubai"):
                        url = getString(R.string.dubaiUrl);
                        break;
                }
                Uri uri = Uri.parse(url);
                Intent browser = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(browser);
            }
        });

    }
}
