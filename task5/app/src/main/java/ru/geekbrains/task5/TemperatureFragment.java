package ru.geekbrains.task5;


import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.sax.StartElementListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;


public class TemperatureFragment extends Fragment {

    private TextView fieldCity;
    private TextView fieldTemperature;
    private TextView fieldWind;
    private TextView fieldPressure;

    public TemperatureFragment() {
    }

    public static TemperatureFragment create(String city, boolean temp, boolean wind, boolean pressure) {
        TemperatureFragment t = new TemperatureFragment();
        Bundle bundle = new Bundle();
        bundle.putString("city", city);
        bundle.putBoolean("temp", temp);
        bundle.putBoolean("wind", wind);
        bundle.putBoolean("pressure", pressure);
        t.setArguments(bundle);
        return t;
    }
    public String getCity() {
        return getArguments().getString("city");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_temperature, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {

            fieldCity = getActivity().findViewById(R.id.fieldCity);
            fieldCity.setText(getArguments().getString("city"));

            fieldTemperature = getActivity().findViewById(R.id.fieldTemperature);
            boolean temperature = getArguments().getBoolean("temp");
            if (temperature) fieldTemperature.setVisibility(View.VISIBLE);

            fieldWind = getActivity().findViewById(R.id.fieldWind);
            boolean wind = getArguments().getBoolean("wind");
            if (wind) fieldWind.setVisibility(View.VISIBLE);

            fieldPressure = getActivity().findViewById(R.id.fieldPressure);
            boolean pressure = getArguments().getBoolean("pressure");
            if (pressure) fieldPressure.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState == null) {
            if (getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE) {

                fieldCity = getActivity().findViewById(R.id.fieldCity);
                String currentCity = getActivity().getIntent().getExtras().getString("currentCity");
                fieldCity.setText(currentCity);

                fieldTemperature = getActivity().findViewById(R.id.fieldTemperature);
                boolean isTemperatureCheckBox = getActivity().getIntent().getExtras().getBoolean("temperatureCheckBox");
                if (isTemperatureCheckBox) fieldTemperature.setVisibility(View.VISIBLE);

                fieldWind = getActivity().findViewById(R.id.fieldWind);
                boolean isWindCheckBox = getActivity().getIntent().getExtras().getBoolean("windCheckBox");
                if (isWindCheckBox) fieldWind.setVisibility(View.VISIBLE);

                fieldPressure = getActivity().findViewById(R.id.fieldPressure);
                boolean isPressureCheckBox = getActivity().getIntent().getExtras().getBoolean("pressureCheckBox");
                if (isPressureCheckBox) fieldPressure.setVisibility(View.VISIBLE);
            }
        }
    }
}

