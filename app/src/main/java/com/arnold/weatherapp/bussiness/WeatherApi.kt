package com.arnold.weatherapp.bussiness


import com.arnold.weatherapp.bussiness.model.WeatherDataModel
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("data/2.5/onecall?")
    fun getWeatherForecast(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("exclude") exclude: String = "minutely,alerts",
        @Query("appid") appid: String = "e0665080a56e466da7dfdfafada6a882",
        @Query("lang") lang: String = "en"
    ) : Observable<WeatherDataModel>

}