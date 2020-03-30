package ru.geekbrains.task5;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.geekbrains.task5.model.WeatherRequest;

public class WeatherRequestPresenter {

    private OpenWeather openWeather;
    private String units = "metric";
    private CitiesFragment citiesFragment;

    public void attachView(CitiesFragment citiesFragment) {
        this.citiesFragment = citiesFragment;
    }

    public void initRetrofit() {
        Retrofit retrofit;
        retrofit = new Retrofit.Builder().baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        openWeather = retrofit.create(OpenWeather.class);
    }

    public void requestRetrofitCity(String city, String keyApi) {

        openWeather.loadWeather(city, units, keyApi)
                .enqueue(new Callback<WeatherRequest>() {
                    @Override
                    public void onResponse(Call<WeatherRequest> call, Response<WeatherRequest> response) {
                        if (response.body() != null) {
                            citiesFragment.createCityButton();
                        }
                    }

                    @Override
                    public void onFailure(Call<WeatherRequest> call, Throwable t) {
                        citiesFragment.showAlertDialogError();
                    }
                });
    }

    public void requestRetrofitTemperature(final String city, String keyApi) {

        openWeather.loadWeather(city, units, keyApi)
                .enqueue(new Callback<WeatherRequest>() {
                    @Override
                    public void onResponse(Call<WeatherRequest> call, Response<WeatherRequest> response) {
                        if (response.body() != null) {
                            WeatherRequest request = response.body();
                            citiesFragment.showTemperatureScreen(city, request);
                        }
                    }

                    @Override
                    public void onFailure(Call<WeatherRequest> call, Throwable t) {
                        citiesFragment.showAlertDialogError();
                    }
                });
    }
}


