package ru.geekbrains.task5;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.geekbrains.task5.model.WeatherRequest;


    public interface OpenWeather {
        @GET("data/2.5/weather")
        Call<WeatherRequest> loadWeather(@Query("q") String cityCountry, @Query("units") String units, @Query("appid") String keyApi);
}
