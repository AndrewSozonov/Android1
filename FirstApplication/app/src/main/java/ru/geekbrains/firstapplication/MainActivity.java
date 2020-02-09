package ru.geekbrains.firstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView basic = findViewById(R.id.myFirstApplication);
        String hello = getResources().getString(R.string.my_first_application);
        basic.setText(hello);
    }

    public void buttonOnClick(View view) {
        Button button = findViewById(R.id.button);
        if (button.getText().equals(getResources().getString(R.string.my))) {
            button.setText(getResources().getString(R.string.first));
        } else if ((button.getText().equals(getResources().getString(R.string.first)))) {
            button.setText(getResources().getString(R.string.button));
        } else {
            button.setText(getResources().getString(R.string.my));
        }
    }

    public void switchOnClick(View view) {
        TextView basic = findViewById(R.id.myFirstApplication);
        Button button = findViewById(R.id.button);
        if (basic.getCurrentTextColor() == getResources().getColor(R.color.colorAccent)) {
            basic.setTextColor(getResources().getColor(R.color.colorGold));
            button.setTextColor(getResources().getColor(R.color.colorGold));
        } else {
            basic.setTextColor(getResources().getColor(R.color.colorAccent));
            button.setTextColor(getResources().getColor(R.color.colorAccent));
        }
    }
}
