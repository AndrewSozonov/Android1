/*
package ru.geekbrains.task5;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;

import ru.geekbrains.task5.model.WeatherRequest;

public class WeatherRequestService extends IntentService {

    public static final String CITY_KEY = "CITY";
    public static final String TEMP_KEY = "TEMP";
    public static final String WIND_KEY = "WIND";
    public static final String PRESSURE_KEY = "PRESSURE";
    private static final String WEATHER_API_KEY = "43dad43a0a9a1275403cb230692b0369";
    private WeatherRequest weatherRequest;


    public WeatherRequestService(String name) {
        super("WeatherRequestService");
    }

    public static void startRequestService(Context context, String currentCity) {
        Intent intent = new Intent(context, WeatherRequestService.class);
        intent.putExtra(CITY_KEY, currentCity );
        context.startService(intent);
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String city = intent.getStringExtra(CITY_KEY);
        weatherRequest = makeRequest(city);
        float currentTemperature = weatherRequest.getMain().getTemp();
        float currentWind = weatherRequest.getWind().getSpeed();
        int currentPressure = weatherRequest.getMain().getPressure();
        sendBrodcast(currentTemperature, currentWind, currentPressure);
    }

    public static WeatherRequest makeRequest(String currentCity){
        WeatherRequest currentWeatherRequest = new WeatherRequest();
        try {
            final URL uri = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + currentCity + "&units=metric&appid=" + WEATHER_API_KEY);
            //final Handler handler = new Handler();
          */
/* new Thread(new Runnable() {
                @Override
                public void run() {*//*


            HttpsURLConnection urlConnection = null;
            try {
                urlConnection = (HttpsURLConnection) uri.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setReadTimeout(10000);
                BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String result = in.lines().collect(Collectors.joining("\n"));
                //String result = getLines(in);
                Gson gson = new Gson();
                final WeatherRequest weatherRequest = gson.fromJson(result, WeatherRequest.class);
                currentWeatherRequest = weatherRequest;
                //return weatherRequest;

                        */
/*handler.post(new Runnable() {
                            @Override
                            public void run() {
                            }
                        });*//*

            } catch (Exception e) {
                Log.d("TAG", "Connection error", e);
                //showDialogScreen("message",context);

            } finally {
                if (null != urlConnection) {
                    urlConnection.disconnect();
                }
            }
           */
/*     }
            }).start();*//*

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return currentWeatherRequest;
    }

    private void sendBrodcast(float temperature, float wind, int pressure) {
        Intent broadcastIntent = new Intent(CitiesFragment.BROADCAST_ACTION_REQUEST_COMPLETED);
        broadcastIntent.putExtra(TEMP_KEY, temperature);
        broadcastIntent.putExtra(WIND_KEY, wind);
        broadcastIntent.putExtra(PRESSURE_KEY, pressure);
        sendBroadcast(broadcastIntent);
    }

    private String getLines(BufferedReader in) {
        return in.lines().collect(Collectors.joining("\n"));
    }

}
*/
