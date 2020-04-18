package ru.geekbrains.task5;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class TemperatureFragment extends Fragment {

    private TextView fieldCity;
    private TextView fieldTemperature;
    private TextView fieldWind;
    private TextView fieldPressure;
    public static final String tempFieldKey = "TEMP_FIELD";
    public static final String windFieldKey = "WIND_FIELD";
    public static final String pressureFieldKey = "PRESSURE_FIELD";
    public static final String tempKey = "TEMP";
    public static final String windKey = "WIND";
    public static final String pressureKey = "PRESSURE";
    public static final String cityKey = "CITY";
    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static TemperatureFragment create(String city, boolean tempField, boolean windField, boolean pressureField, float temperature, float wind, int pressure) {
        TemperatureFragment t = new TemperatureFragment();

        Bundle bundle = new Bundle();
        bundle.putString(cityKey, city);
        bundle.putBoolean(tempFieldKey, tempField);
        bundle.putBoolean(windFieldKey, windField);
        bundle.putBoolean(pressureFieldKey, pressureField);
        bundle.putFloat(tempKey, temperature);
        bundle.putFloat(windKey, wind);
        bundle.putInt(pressureKey, pressure);
        t.setArguments(bundle);
        return t;
    }

    public String getCity() {
        return getArguments().getString(cityKey);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_temperature, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String [] timeArr = getResources().getStringArray(R.array.time);
        String [] temperatureArr = getResources().getStringArray(R.array.time_temperature);
        initRecyclerView(timeArr, temperatureArr);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState == null) {

            fieldCity = getActivity().findViewById(R.id.fieldCity);
            if (getArguments().containsKey(cityKey)) {
                fieldCity.setText(getArguments().getString(cityKey));
            }

            fieldTemperature = getActivity().findViewById(R.id.fieldTemperature);
            if (getArguments().containsKey(tempFieldKey)) {
                boolean temperature = getArguments().getBoolean(tempFieldKey);
                if (temperature) fieldTemperature.setVisibility(View.VISIBLE);
            }
            if (getArguments().containsKey(tempKey)) {
                fieldTemperature.setText(String.format("%.1f С°",getArguments().getFloat(tempKey)));
            }

            fieldWind = getActivity().findViewById(R.id.fieldWind);
            if (getArguments().containsKey(windFieldKey)) {
                boolean wind = getArguments().getBoolean(windFieldKey);
                if (wind) fieldWind.setVisibility(View.VISIBLE);
            }
            if (getArguments().containsKey(windKey)) {
                fieldWind.setText(String.format("%.1f m/s",getArguments().getFloat(windKey)));
            }

            fieldPressure = getActivity().findViewById(R.id.fieldPressure);
            if (getArguments().containsKey(pressureFieldKey)) {
                boolean pressure = getArguments().getBoolean(pressureFieldKey);
                if (pressure) fieldPressure.setVisibility(View.VISIBLE);
            }
            if (getArguments().containsKey(pressureKey)) {
                fieldPressure.setText(String.format("%d mm",getArguments().getInt(pressureKey)));
            }
        }
    }

    private void initRecyclerView(String[] dataTime, String [] dataTemp) {
        recyclerView = getView().findViewById(R.id.recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);

        recyclerAdapter = new RecyclerAdapter(dataTime, dataTemp);
        recyclerView.setAdapter(recyclerAdapter);
    }
}



