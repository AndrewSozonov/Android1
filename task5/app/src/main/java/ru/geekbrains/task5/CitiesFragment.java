package ru.geekbrains.task5;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import com.google.android.material.button.MaterialButton;
import java.text.SimpleDateFormat;
import java.util.Date;
import ru.geekbrains.task5.dataBase.History;
import ru.geekbrains.task5.dataBase.HistoryDao;
import ru.geekbrains.task5.model.List;
import ru.geekbrains.task5.model.Weather;
import ru.geekbrains.task5.model.WeatherRequest;

import static android.content.Context.LOCATION_SERVICE;

public class CitiesFragment extends Fragment {

    private static String currentCity;
    private WeatherRequestPresenter weatherRequestPresenter = new WeatherRequestPresenter();
    private MyBottomSheetDialogFragment dialogFragment;
    private static final int PERMISSION_REQUEST_CODE = 10;

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
        Button buttonNearbyWeather = getView().findViewById(R.id.button_nearbyWeather);
        showBottomSheetDialogFragment();
        buttonHistory.setOnClickListener(v -> showHistoryScreen());
        buttonSearch.setOnClickListener(v -> dialogFragment.show(getActivity().getSupportFragmentManager(),
                "dialog_fragment"));
        buttonNearbyWeather.setOnClickListener(v -> requestPemissions());
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
            weatherRequestPresenter.requestRetrofitCity(currentCity, Constants.WEATHER_API_KEY);
        }
    };

    View.OnClickListener cityButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(final View v) {
            currentCity = ((Button) v).getText().toString();
            weatherRequestPresenter.requestRetrofitTemperatureHourly(currentCity, Constants.WEATHER_API_KEY);
        }
    };

    public void showTemperatureScreen(String city, WeatherRequest weatherRequest) {

        HistoryDao historyDao = App
                .getInstance()
                .getHistoryDao();
        HistorySource historySource = new HistorySource(historyDao);


        List[] list = weatherRequest.getList();
        String[] dateForecast = new String[12];
        String[] temperatureForecast = new String[12];

        for (int i = 0; i<12; i++ ) {
            String oldDate = list[i].getDate();
           dateForecast[i] = oldDate.substring(11,16);
           temperatureForecast[i] = String.format("%.1f С°",list[i].getMain().getTemp());
        }

        Weather[] weather = list[0].getWeather();
        float currentTemperature = list[0].getMain().getTemp();
        int currentHumidity = list[0].getMain().getHumidity();
        float currentWind = list[0].getWind().getSpeed();
        int currentPressure = list[0].getMain().getPressure();
        String currentDescription = weather[0].getMain();
        int currentId = weather[0].getId();

        History history = new History();
        history.city = city;
        history.temperature = currentTemperature;

        Date dateNow = new Date();
        SimpleDateFormat formatForDayNow = new SimpleDateFormat("dd.MM E");
        history.date = formatForDayNow.format(dateNow);

        historySource.addHistory(history);

        Intent intent = new Intent();
        intent.setClass(getActivity(), TemperatureActivity.class);
        //intent.putExtra(Constants.CITY_KEY, currentCity);
        intent.putExtra(Constants.CITY_KEY, city);
        intent.putExtra(Constants.TEMP_KEY, currentTemperature);
        intent.putExtra(Constants.HUMIDITY_KEY, currentHumidity);
        intent.putExtra(Constants.WIND_KEY, currentWind);
        intent.putExtra(Constants.PRESSURE_KEY, currentPressure);
        intent.putExtra(Constants.DESCRIPTION_KEY, currentDescription);
        intent.putExtra(Constants.ID_KEY, currentId);
        intent.putExtra(Constants.DATE_FORECAST_KEY,dateForecast);
        intent.putExtra(Constants.TEMPERATURE_FORECAST_KEY, temperatureForecast);
        startActivity(intent);
    }

    private void showHistoryScreen() {

        Intent intent = new Intent();
        intent.setClass(getActivity(), HistoryActivity.class);
        startActivity(intent);
    }

    public void showAlertDialogError() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(R.string.city_error);
        builder.setCancelable(false);
        builder.setPositiveButton(R.string.ok, (dialog, which) -> dialog.dismiss());
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

    private void requestPemissions() {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            requestLocation();
        } else {
            requestLocationPermissions();
        }
    }

    private void requestLocationPermissions() {
        if (!ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CALL_PHONE)) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {   // Запрошенный нами
            if (grantResults.length == 2 && (grantResults[0] == PackageManager.PERMISSION_GRANTED || grantResults[1] == PackageManager.PERMISSION_GRANTED)) {
                requestLocation();
            }
        }
    }

    private void requestLocation() {

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            return;

        LocationManager locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_COARSE);

        String provider = locationManager.getBestProvider(criteria, true);

        if (provider != null) {
            locationManager.requestLocationUpdates(provider, 100000, 1000, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    double lat = location.getLatitude();
                    String latitude = Double.toString(lat);

                    double lng = location.getLongitude();
                    String longitude = Double.toString(lng);

                    weatherRequestPresenter.requestRetrofitTemperatureByCoord(latitude, longitude, Constants.WEATHER_API_KEY );
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {
                }

                @Override
                public void onProviderEnabled(String provider) {
                }

                @Override
                public void onProviderDisabled(String provider) {
                }
            });
        }
    }
}








