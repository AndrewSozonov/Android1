package ru.geekbrains.task5;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import java.util.Arrays;
import java.util.List;


public class CitiesFragment extends Fragment {

    private boolean isTemperatureScreenExist;
    public static String currentCity;
    private CheckBox temperatureCheckBox;
    private CheckBox windCheckBox;
    private CheckBox pressureCheckBox;
    private AutoCompleteTextView cityField;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button buttonMoscow = getView().findViewById(R.id.button_Moscow);
        Button buttonStPetersburg = getView().findViewById(R.id.button_StPetersburg);
        Button buttonNewYork = getView().findViewById(R.id.button_NewYork);
        Button buttonLondon = getView().findViewById(R.id.button_London);
        Button buttonParis = getView().findViewById(R.id.button_Paris);
        Button buttonDubai = getView().findViewById(R.id.button_Dubai);
        Button buttonEnter = getView().findViewById(R.id.enter);
        cityField = getView().findViewById(R.id.CityField);

        View.OnClickListener listener1 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentCity = ((Button) v).getText().toString();
                showTemperatureScreen(currentCity);
            }
        };

        buttonMoscow.setOnClickListener(listener1);
        buttonStPetersburg.setOnClickListener(listener1);
        buttonNewYork.setOnClickListener(listener1);
        buttonLondon.setOnClickListener(listener1);
        buttonParis.setOnClickListener(listener1);
        buttonDubai.setOnClickListener(listener1);

        final String[] cities = getResources().getStringArray(R.array.cities);
        List<String> citiesList = Arrays.asList(cities);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity().getApplicationContext(),android.R.layout.simple_dropdown_item_1line, citiesList);
        cityField.setAdapter(adapter);
        cityField.setThreshold(1);

        buttonEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] cities = getResources().getStringArray(R.array.cities);
                String city = cityField.getText().toString();

                for (int i=0; i< cities.length; i++) {
                    if (city.equals(cities[i])){
                        currentCity = city;
                        showTemperatureScreen(currentCity);
                    }
                }
            }
        });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        isTemperatureScreenExist = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        if (savedInstanceState != null ) {
            currentCity = savedInstanceState.getString("current_city");
        }
        TemperatureFragment tempFrag = (TemperatureFragment) getFragmentManager().findFragmentById(R.id.temperature_screen);
        if (tempFrag != null) {
            showTemperatureScreen(currentCity);
        }

    }

    @Override
    public void onSaveInstanceState(Bundle saveInstanceState){

        super.onSaveInstanceState(saveInstanceState);

        saveInstanceState.putString("current_city", currentCity);
        cityField = getActivity().findViewById(R.id.CityField);
        String cityFieldText = cityField.getText().toString();
        saveInstanceState.putString("cityField", cityFieldText);

        temperatureCheckBox = getActivity().findViewById(R.id.temperature);
        saveInstanceState.putBoolean("tempCheckBox", temperatureCheckBox.isChecked());

        windCheckBox = getActivity().findViewById(R.id.windSpeed);
        saveInstanceState.putBoolean("windCheckBox", windCheckBox.isChecked());

        pressureCheckBox = getActivity().findViewById(R.id.atmospere);
        saveInstanceState.putBoolean("pressureCheckBox", pressureCheckBox.isChecked());

    }

    private void showTemperatureScreen(String city){

        temperatureCheckBox = getActivity().findViewById(R.id.temperature);
        windCheckBox = getActivity().findViewById(R.id.windSpeed);
        pressureCheckBox = getActivity().findViewById(R.id.atmospere);

        if (isTemperatureScreenExist) {
            TemperatureFragment tempFrag = (TemperatureFragment) getFragmentManager().findFragmentById(R.id.temperature_screen);
            if (tempFrag == null || tempFrag.getCity() != currentCity) {

                boolean windCheckBoxIsChecked = windCheckBox.isChecked();
                boolean tempCheckBoxIsChecked = temperatureCheckBox.isChecked();
                boolean pressureCheckBoxIsChecked = pressureCheckBox.isChecked();

                tempFrag = TemperatureFragment.create(city, tempCheckBoxIsChecked, windCheckBoxIsChecked, pressureCheckBoxIsChecked);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.temperature_screen, tempFrag);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.addToBackStack(null);
                ft.commit();
            }
        } else {
            Intent intent = new Intent();
            intent.setClass(getActivity(), TemperatureActivity.class);
            intent.putExtra("temp", temperatureCheckBox.isChecked());
            intent.putExtra("wind", windCheckBox.isChecked());
            intent.putExtra("pressure", pressureCheckBox.isChecked());
            intent.putExtra("city", currentCity);
            startActivity(intent);
        }
    }
}




