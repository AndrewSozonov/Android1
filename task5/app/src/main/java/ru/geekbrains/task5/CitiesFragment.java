package ru.geekbrains.task5;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import ru.geekbrains.task5.model.WeatherRequest;

public class CitiesFragment extends Fragment {

    private static final String TAG = "Weather";
    private boolean isTemperatureScreenExist;
    private static String currentCity;
    private TextInputEditText cityField;
    private WeatherRequest currentWeatherRequest;
    private Presenter presenter;
    public static final String TEMP_FIELD_KEY = "TEMP_FIELD";
    public static final String WIND_FIELD_KEY = "WIND_FIELD";
    public static final String PRESSURE_FIELD_KEY = "PRESSURE_FIELD";
    public static final String TEMP_KEY = "TEMP";
    public static final String WIND_KEY = "WIND";
    public static final String PRESSURE_KEY = "PRESSURE";
    public static final String CITY_KEY = "CITY";
    public static final String HISTORY_KEY = "HISTORY";
    public static final String HISTORY_TEMPERATURE_KEY = "HISTORY_TEMPERATURE";
    private ArrayList<String> citiesHistory = new ArrayList<>();
    private ArrayList<Float> temperatureHistory = new ArrayList<>();
    private MyBottomSheetDialogFragment dialogFragment;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button buttonMoscow = getView().findViewById(R.id.button_Moscow);
        Button buttonRostov = getView().findViewById(R.id.button_Rostov);
        Button buttonSochi = getView().findViewById(R.id.button_Sochi);
        Button buttonVladivostok = getView().findViewById(R.id.button_Vladivostok);
        Button buttonParis = getView().findViewById(R.id.button_Paris);
        Button buttonVoronezh = getView().findViewById(R.id.button_Voronezh);
        Button buttonHistory = getView().findViewById(R.id.button_History);
        Button buttonSearch = getView().findViewById(R.id.button_Search);
        final Button buttonEnter = getView().findViewById(R.id.enter);
        cityField = getView().findViewById(R.id.inputCityField);
        dialogFragment = MyBottomSheetDialogFragment.newInstance();
        dialogFragment.setOnDialogListener(dialogListener);
        dialogFragment.show(getActivity().getSupportFragmentManager(),
                "dialog_fragment");


        View.OnClickListener listener1 = new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (v == buttonEnter) {
                    currentCity = cityField.getText().toString();
                } else {
                    currentCity = ((Button) v).getText().toString();
                }
                presenter = new Presenter();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        currentWeatherRequest = presenter.connectToServer(currentCity);
                        if (currentWeatherRequest != null) {
                        showTemperatureScreen(currentCity, currentWeatherRequest);
                        }
                    }
                }).start();
            }
        };

        buttonMoscow.setOnClickListener(listener1);
        buttonRostov.setOnClickListener(listener1);
        buttonSochi.setOnClickListener(listener1);
        buttonVladivostok.setOnClickListener(listener1);
        buttonParis.setOnClickListener(listener1);
        buttonVoronezh.setOnClickListener(listener1);
        buttonEnter.setOnClickListener(listener1);
        buttonHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHistoryScreen();
            }
        });
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogFragment.show(getActivity().getSupportFragmentManager(),
                        "dialog_fragment");
            }
        });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        isTemperatureScreenExist = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        if (savedInstanceState != null) {
            currentCity = savedInstanceState.getString("current_city");
        }
        TemperatureFragment tempFrag = (TemperatureFragment) getFragmentManager().findFragmentById(R.id.temperature_screen);
        if (tempFrag != null) {
            showTemperatureScreen(currentCity, currentWeatherRequest);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle saveInstanceState) {

        super.onSaveInstanceState(saveInstanceState);

        saveInstanceState.putString("current_city", currentCity);
        cityField = getActivity().findViewById(R.id.inputCityField);
        String cityFieldText = cityField.getText().toString();
        saveInstanceState.putString("cityField", cityFieldText);
    }

    private void showTemperatureScreen(String city, WeatherRequest weatherRequest) {

        float currentTemperature = weatherRequest.getMain().getTemp();
        float currentWind = weatherRequest.getWind().getSpeed();
        int currentPressure = weatherRequest.getMain().getPressure();
        citiesHistory.add(currentCity);
        temperatureHistory.add(currentWeatherRequest.getMain().getTemp());

        if (isTemperatureScreenExist) {
            TemperatureFragment tempFrag = (TemperatureFragment) getFragmentManager().findFragmentById(R.id.temperature_screen);
            if (tempFrag == null || tempFrag.getCity() != currentCity) {

                boolean tempCheckBoxIsChecked = ((MainActivity) getActivity()).isTemperatureSettings();
                boolean windCheckBoxIsChecked = ((MainActivity) getActivity()).isWindSettings();
                boolean pressureCheckBoxIsChecked = ((MainActivity) getActivity()).isPressureSettings();

                tempFrag = TemperatureFragment.create(city, tempCheckBoxIsChecked, windCheckBoxIsChecked, pressureCheckBoxIsChecked, currentTemperature, currentWind, currentPressure);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.temperature_screen, tempFrag);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.addToBackStack(null);
                ft.commit();
            }
        } else {
            Intent intent = new Intent();
            intent.setClass(getActivity(), TemperatureActivity.class);
            intent.putExtra(TEMP_FIELD_KEY, ((MainActivity) getActivity()).isTemperatureSettings());
            intent.putExtra(WIND_FIELD_KEY, ((MainActivity) getActivity()).isWindSettings());
            intent.putExtra(PRESSURE_FIELD_KEY, ((MainActivity) getActivity()).isPressureSettings());
            intent.putExtra(CITY_KEY, currentCity);
            intent.putExtra(TEMP_KEY, currentTemperature);
            intent.putExtra(WIND_KEY, currentWind);
            intent.putExtra(PRESSURE_KEY, currentPressure);
            startActivity(intent);
        }
    }

    private void showHistoryScreen() {

        Intent intent = new Intent();
        intent.setClass(getActivity(), HistoryActivity.class);
        intent.putExtra(HISTORY_KEY, citiesHistory);

        float[] arrayTemperature = new float[temperatureHistory.size()];
        for (int i = 0; i < temperatureHistory.size(); i++) {
            arrayTemperature[i] = temperatureHistory.get(i);
        }
        intent.putExtra(HISTORY_TEMPERATURE_KEY, arrayTemperature);
        startActivity(intent);
    }

    private OnDialogListener dialogListener = new OnDialogListener() {
        @Override
        public void onDialogOk() {
            currentCity = dialogFragment.getCurrentCity();
            presenter = new Presenter();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    currentWeatherRequest = presenter.connectToServer(currentCity);
                    if (currentWeatherRequest != null) {
                        showTemperatureScreen(currentCity, currentWeatherRequest);
                    }
                }
            }).start();
        }
    };

    private void showAlertDialogError() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(R.string.city_error);
        builder.setCancelable(false);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}








