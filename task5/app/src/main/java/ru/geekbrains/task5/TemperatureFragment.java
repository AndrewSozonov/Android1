package ru.geekbrains.task5;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import static android.content.Context.MODE_PRIVATE;

public class TemperatureFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_temperature, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        initRecyclerView();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initFields();
        setWeatherIcon();
    }

    private void initFields() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(Constants.PREFERENCE_FILE_NAME, MODE_PRIVATE);

        TextView fieldCity = getActivity().findViewById(R.id.fieldCity);
        if (getArguments().containsKey(Constants.CITY_KEY)) {
            fieldCity.setText(getArguments().getString(Constants.CITY_KEY));
        }

        TextView fieldTemperature = getActivity().findViewById(R.id.fieldTemperature);
        if (getArguments().containsKey(Constants.TEMP_KEY)) {
            fieldTemperature.setText(String.format("%.1f С°", getArguments().getFloat(Constants.TEMP_KEY)));
        }

        TextView fieldHumidity = getActivity().findViewById(R.id.fieldHumidity);
        boolean humidity = sharedPreferences.getBoolean(Constants.HUMIDITY_FIELD_KEY, false);
        if (humidity) fieldHumidity.setVisibility(View.VISIBLE);

        if (getArguments().containsKey(Constants.HUMIDITY_KEY)) {
            fieldHumidity.setText(String.format("Humidity  " + "%d mm", getArguments().getInt(Constants.HUMIDITY_KEY)));
        }

        TextView fieldWind = getActivity().findViewById(R.id.fieldWind);
        boolean wind = sharedPreferences.getBoolean(Constants.WIND_FIELD_KEY, false);
        if (wind) fieldWind.setVisibility(View.VISIBLE);

        if (getArguments().containsKey(Constants.WIND_KEY)) {
            fieldWind.setText(String.format("Wind speed  " + "%.1f m/s", getArguments().getFloat(Constants.WIND_KEY)));
        }

        TextView fieldPressure = getActivity().findViewById(R.id.fieldPressure);
        boolean pressure = sharedPreferences.getBoolean(Constants.PRESSURE_FIELD_KEY, false);
        if (pressure) fieldPressure.setVisibility(View.VISIBLE);

        if (getArguments().containsKey(Constants.PRESSURE_KEY)) {
            fieldPressure.setText(String.format("Atmospheric pressure  " + "%d mm", getArguments().getInt(Constants.PRESSURE_KEY)));
        }
    }

    private void setWeatherIcon() {
        ImageView weatherIcon = getActivity().findViewById(R.id.weatherIcon);
        ImageView background = getActivity().findViewById(R.id.backgroundTemperatureFragment);
        String currentWeatherDescription = getArguments().getString(Constants.DESCRIPTION_KEY);
        int currentID = getArguments().getInt(Constants.ID_KEY);
        switch (currentWeatherDescription) {
            case ("Thunderstorm"):
                weatherIcon.setImageResource(R.drawable.icon200);
                Picasso.get()
                        .load("https://images.unsplash.com/photo-1527572232473-494f1e9c7917?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=668&q=80")
                        .into(background);
                break;
            case ("Drizzle"):
                weatherIcon.setImageResource(R.drawable.icon300);
                Picasso.get()
                        .load("https://images.unsplash.com/photo-1524693788736-5e6f1716beb3?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=634&q=80")
                        .into(background);
                break;
            case ("Rain"):
                weatherIcon.setImageResource(R.drawable.icon500_504);
                Picasso.get()
                        .load("https://images.unsplash.com/photo-1534274988757-a28bf1a57c17?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=675&q=80")
                        .into(background);
                break;
            case ("Snow"):
                weatherIcon.setImageResource(R.drawable.icon600);
                Picasso.get()
                        .load("https://images.unsplash.com/photo-1547576962-9f4ee7e7a7c1?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60")
                        .into(background);
                break;
            case ("Clear"):
                weatherIcon.setImageResource(R.drawable.icon800);
                Picasso.get()
                        .load("https://images.unsplash.com/photo-1548346941-0f485f3ec808?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60")
                        .into(background);
                break;
            case ("Clouds"):
                Picasso.get()
                        .load("https://images.unsplash.com/photo-1556005781-709b99c7dc18?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=564&q=80")
                        .into(background);
                switch (currentID) {
                    case (801):
                        weatherIcon.setImageResource(R.drawable.icon801);
                        break;
                    case (802):
                        weatherIcon.setImageResource(R.drawable.icon802);
                        break;
                    case (803):
                        weatherIcon.setImageResource(R.drawable.icon803);
                        break;
                    case (804):
                        weatherIcon.setImageResource(R.drawable.icon804);
                        break;
                }
        }
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = getView().findViewById(R.id.recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);

        String[] dateForecast = getArguments().getStringArray(Constants.DATE_FORECAST_KEY);
        String[] temperatureForecast = getArguments().getStringArray(Constants.TEMPERATURE_FORECAST_KEY);

        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(dateForecast, temperatureForecast);
        recyclerView.setAdapter(recyclerAdapter);
    }
}



