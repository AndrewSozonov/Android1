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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        String [] timeArr = getResources().getStringArray(R.array.time);
        String [] temperatureArr = getResources().getStringArray(R.array.time_temperature);
        initRecyclerView(timeArr, temperatureArr);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState == null) {

            fieldCity = getActivity().findViewById(R.id.fieldCity);
            if (getArguments().containsKey("city")) {
                fieldCity.setText(getArguments().getString("city"));
            }

            fieldTemperature = getActivity().findViewById(R.id.fieldTemperature);
            if (getArguments().containsKey("temp")) {
                boolean temperature = getArguments().getBoolean("temp");
                if (temperature) fieldTemperature.setVisibility(View.VISIBLE);
            }

            fieldWind = getActivity().findViewById(R.id.fieldWind);
            if (getArguments().containsKey("wind")) {
                boolean wind = getArguments().getBoolean("wind");
                if (wind) fieldWind.setVisibility(View.VISIBLE);
            }

            fieldPressure = getActivity().findViewById(R.id.fieldPressure);
            if (getArguments().containsKey("pressure")) {
                boolean pressure = getArguments().getBoolean("pressure");
                if (pressure) fieldPressure.setVisibility(View.VISIBLE);
            }
        }
    }

    private void initRecyclerView(String[] dataTime, String [] dataTemp) {
        RecyclerView recyclerView = getView().findViewById(R.id.recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);

        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(dataTime, dataTemp);
        recyclerView.setAdapter(recyclerAdapter);
    }

}



