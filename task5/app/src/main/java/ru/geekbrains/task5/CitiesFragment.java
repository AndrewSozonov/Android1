package ru.geekbrains.task5;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import com.google.android.material.button.MaterialButton;
import java.util.ArrayList;
import ru.geekbrains.task5.model.Weather;
import ru.geekbrains.task5.model.WeatherRequest;

public class CitiesFragment extends Fragment {

    private static String currentCity;
    private WeatherRequestPresenter weatherRequestPresenter = new WeatherRequestPresenter();
    private ArrayList<String> citiesHistory = new ArrayList<>();
    private ArrayList<Float> temperatureHistory = new ArrayList<>();
    private MyBottomSheetDialogFragment dialogFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        weatherRequestPresenter.initRetrofit();
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        weatherRequestPresenter.attachView(this);

        Button buttonHistory = getView().findViewById(R.id.button_History);
        Button buttonSearch = getView().findViewById(R.id.button_Search);
        showBottomSheetDialogFragment();
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

    public void showBottomSheetDialogFragment() {

        dialogFragment = MyBottomSheetDialogFragment.newInstance();
        dialogFragment.setOnDialogListener(dialogListener);
        dialogFragment.show(getActivity().getSupportFragmentManager(),
                "dialog_fragment");
    }

    @Override
    public void onSaveInstanceState(Bundle saveInstanceState) {

        super.onSaveInstanceState(saveInstanceState);
        saveInstanceState.putString("current_city", currentCity);
    }

    private OnDialogListener dialogListener = new OnDialogListener() {
        @Override
        public void onDialogOk() {
            currentCity = dialogFragment.getCurrentCity();
             weatherRequestPresenter.requestRetrofitCity(currentCity,Constants.WEATHER_API_KEY);
        }
    };

    View.OnClickListener cityButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(final View v) {
            currentCity = ((Button) v).getText().toString();
            weatherRequestPresenter.requestRetrofitTemperature(currentCity, Constants.WEATHER_API_KEY);
        }
    };

    public void showTemperatureScreen(String city, WeatherRequest weatherRequest) {

        Weather[] weather = weatherRequest.getWeather();
        float currentTemperature = weatherRequest.getMain().getTemp();
        int currentHumidity = weatherRequest.getMain().getHumidity();
        float currentWind = weatherRequest.getWind().getSpeed();
        int currentPressure = weatherRequest.getMain().getPressure();
        String currentDescription = weather[0].getMain();
        int currentId = weather[0].getId();
        citiesHistory.add(currentCity);
        temperatureHistory.add(weatherRequest.getMain().getTemp());

            Intent intent = new Intent();
            intent.setClass(getActivity(), TemperatureActivity.class);
            intent.putExtra(Constants.HUMIDITY_FIELD_KEY, ((MainActivity) getActivity()).isHumiditySettings());
            intent.putExtra(Constants.WIND_FIELD_KEY, ((MainActivity) getActivity()).isWindSettings());
            intent.putExtra(Constants.PRESSURE_FIELD_KEY, ((MainActivity) getActivity()).isPressureSettings());
            intent.putExtra(Constants.CITY_KEY, currentCity);
            intent.putExtra(Constants.TEMP_KEY, currentTemperature);
            intent.putExtra(Constants.HUMIDITY_KEY, currentHumidity);
            intent.putExtra(Constants.WIND_KEY, currentWind);
            intent.putExtra(Constants.PRESSURE_KEY, currentPressure);
            intent.putExtra(Constants.DESCRIPTION_KEY, currentDescription);
            intent.putExtra(Constants.ID_KEY, currentId);
            startActivity(intent);

    }

    private void showHistoryScreen() {

        Intent intent = new Intent();
        intent.setClass(getActivity(), HistoryActivity.class);
        intent.putExtra(Constants.HISTORY_KEY, citiesHistory);

        float[] arrayTemperature = new float[temperatureHistory.size()];
        for (int i = 0; i < temperatureHistory.size(); i++) {
            arrayTemperature[i] = temperatureHistory.get(i);
        }
        intent.putExtra(Constants.HISTORY_TEMPERATURE_KEY, arrayTemperature);
        startActivity(intent);
    }

    public void showAlertDialogError() {
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

    public void createCityButton() {
        MaterialButton button = new MaterialButton(getContext());
        LinearLayout layout = getView().findViewById(R.id.buttons_Linear_Layout);
        button.setText(currentCity);
        layout.addView(button);
        button.setOnClickListener(cityButtonListener);
    }
}








