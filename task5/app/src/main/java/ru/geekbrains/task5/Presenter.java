package ru.geekbrains.task5;

import android.util.Log;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Collectors;
import javax.net.ssl.HttpsURLConnection;
import ru.geekbrains.task5.model.WeatherRequest;

public class Presenter {

    private static final String WEATHER_API_KEY = "43dad43a0a9a1275403cb230692b0369";

    public WeatherRequest connectToServer(String currentCity) {
        WeatherRequest currentWeatherRequest = new WeatherRequest();

        try {
            final URL uri = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + currentCity + "&units=metric&appid=" + WEATHER_API_KEY);
            HttpsURLConnection urlConnection = null;
            try {
                urlConnection = (HttpsURLConnection) uri.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setReadTimeout(10000);
                BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String result = getLines(in);
                Gson gson = new Gson();
                currentWeatherRequest = gson.fromJson(result, WeatherRequest.class);
            } catch (Exception e) {
                Log.d("TAG", "Connection error", e);
                return null;
            } finally {
                if (null != urlConnection) {
                    urlConnection.disconnect();
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return currentWeatherRequest;
    }
    private String getLines(BufferedReader in) {
        return in.lines().collect(Collectors.joining("\n"));
    }
}


