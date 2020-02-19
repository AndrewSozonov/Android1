package ru.geekbrains.task3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String TAG = "Жизненный цикл";
    private AutoCompleteTextView cityField;
    private CheckBox tempCheckbox;
    private CheckBox windCheckbox;
    private CheckBox pressureCheckbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG,"onCreate");

        cityField = findViewById(R.id.CityField);
        String text = "";
        if (savedInstanceState != null && savedInstanceState.containsKey("cityField"))
            text = savedInstanceState.getString("cityField");
        cityField.setText(text);

        tempCheckbox = findViewById(R.id.temperature);
        if (savedInstanceState != null && savedInstanceState.containsKey("tempCheckBox"))
            tempCheckbox.setChecked(savedInstanceState.getBoolean("tempCheckBox"));

        windCheckbox = findViewById(R.id.windSpeed);
        if (savedInstanceState != null && savedInstanceState.containsKey("windCheckBox"))
            tempCheckbox.setChecked(savedInstanceState.getBoolean("windCheckBox"));

        pressureCheckbox = findViewById(R.id.atmospere);
        if (savedInstanceState != null && savedInstanceState.containsKey("pressureCheckBox"))
            pressureCheckbox.setChecked(savedInstanceState.getBoolean("pressureCheckBox"));


        final String[] cities = getResources().getStringArray(R.array.cities);
        List<String> citiesList = Arrays.asList(cities);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, citiesList);
        cityField.setAdapter(adapter);
        cityField.setThreshold(1);

        Toast.makeText(getApplicationContext(),"onCreate()",Toast.LENGTH_SHORT).show();

        Button buttonEnter = findViewById(R.id.enter);
        buttonEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = cityField.getText().toString();
                boolean cityNotAvailable = true;

                for (int i=0; i< cities.length; i++) {
                    if (city.equals(cities[i])){
                        cityNotAvailable = false;
                        Toast.makeText(getApplicationContext(), "Выбран " + city, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                        intent.putExtra("temperatureCheckBox", tempCheckbox.isChecked());
                        intent.putExtra("windCheckBox", windCheckbox.isChecked());
                        intent.putExtra("pressureCheckBox", pressureCheckbox.isChecked());
                        intent.putExtra("city", city);
                        startActivity(intent);
                    }
                }
                if (cityNotAvailable)
                    Toast.makeText(getApplicationContext(), "Неправильно выбран город", Toast.LENGTH_SHORT).show();
            }
        });


        Button buttonMoscow = findViewById(R.id.button_Moscow);
        Button buttonStPetersburg = findViewById(R.id.button_StPetersburg);
        Button buttonNewYork = findViewById(R.id.button_NewYork);
        Button buttonLondon = findViewById(R.id.button_London);
        Button buttonParis = findViewById(R.id.button_Paris);
        Button buttonDubai = findViewById(R.id.button_Dubai);

        buttonMoscow.setOnClickListener(this);
        buttonStPetersburg.setOnClickListener(this);
        buttonNewYork.setOnClickListener(this);
        buttonLondon.setOnClickListener(this);
        buttonParis.setOnClickListener(this);
        buttonDubai.setOnClickListener(this);
    }

    @Override
    public void onSaveInstanceState(Bundle saveInstanceState){
        cityField = findViewById(R.id.CityField);
        String cityFieldText = cityField.getText().toString();
        saveInstanceState.putString("cityField", cityFieldText);

        tempCheckbox = findViewById(R.id.temperature);
        saveInstanceState.putBoolean("tempCheckBox", tempCheckbox.isChecked());

        windCheckbox = findViewById(R.id.windSpeed);
        saveInstanceState.putBoolean("windCheckBox", windCheckbox.isChecked());

        pressureCheckbox = findViewById(R.id.atmospere);
        saveInstanceState.putBoolean("pressureCheckBox", pressureCheckbox.isChecked());

        super.onSaveInstanceState(saveInstanceState);
        Toast.makeText(getApplicationContext(),"onSaveInstanceState()",Toast.LENGTH_SHORT).show();
        Log.d(TAG,"onSaveInstanceState");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(),"onStart()",Toast.LENGTH_SHORT).show();
        Log.d(TAG," onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(),"onResume()", Toast.LENGTH_SHORT).show();
        Log.d(TAG," onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(getApplicationContext(), "onPause", Toast.LENGTH_SHORT).show();
        Log.d(TAG," onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(getApplicationContext(), "onStop", Toast.LENGTH_SHORT).show();
        Log.d(TAG," onStop()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(getApplicationContext(), "onRestart", Toast.LENGTH_SHORT).show();
        Log.d(TAG," onRestart()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "onDestroy", Toast.LENGTH_SHORT).show();
        Log.d(TAG," onDestroy");
    }

    @Override
    public void onClick(View v) {

        tempCheckbox = findViewById(R.id.temperature);
        windCheckbox = findViewById(R.id.windSpeed);
        pressureCheckbox = findViewById(R.id.atmospere);
        Button button = (Button)v;
        String city = button.getText().toString();

        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        intent.putExtra("temperatureCheckBox", tempCheckbox.isChecked());
        intent.putExtra("windCheckBox", windCheckbox.isChecked());
        intent.putExtra("pressureCheckBox", pressureCheckbox.isChecked());
        intent.putExtra("city", city);
        startActivity(intent);
    }
}







