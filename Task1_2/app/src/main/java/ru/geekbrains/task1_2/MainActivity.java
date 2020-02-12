package ru.geekbrains.task1_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonMoscow = findViewById(R.id.button_Moscow);
        Button buttonStPetersburg = findViewById(R.id.button_StPetersburg);
        Button buttonNewYork = findViewById(R.id.button_NewYork);
        Button buttonLondon = findViewById(R.id.button_London);
        Button buttonParis = findViewById(R.id.button_Paris);

        buttonMoscow.setOnClickListener(this);
        buttonStPetersburg.setOnClickListener(this);
        buttonNewYork.setOnClickListener(this);
        buttonLondon.setOnClickListener(this);
        buttonParis.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        CheckBox tempCheckbox = findViewById(R.id.temperature);
        CheckBox windCheckbox = findViewById(R.id.windSpeed);
        CheckBox pressureCheckbox = findViewById(R.id.atmospere);

        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        intent.putExtra("temperatureCheckBox", tempCheckbox.isChecked());
        intent.putExtra("windCheckBox", windCheckbox.isChecked());
        intent.putExtra("pressureCheckBox", pressureCheckbox.isChecked());
        startActivity(intent);
    }
}







