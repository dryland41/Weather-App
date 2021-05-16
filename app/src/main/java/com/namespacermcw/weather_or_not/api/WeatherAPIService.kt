package com.namespacermcw.weather_or_not.api

import com.namespacermcw.weather_or_not.models.Cities.CitiesWeatherHead
import com.namespacermcw.weather_or_not.models.WeatherHead.WeatherHead
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPIService {

    @GET("weather")
    fun getWeather(
        @Query("q") city: String = "Atlanta",
        @Query("appid") appid: String = "ecb95411883c1a2934892d3ee3540552",
        @Query("units") units: String = "imperial"
    ): Single<WeatherHead>

    @GET("find")
    fun getCities(
        @Query("lat") lat: Double = 33.749,
        @Query("lon") lon: Double = -84.388,
        @Query("units") units: String = "imperial",
        @Query("appid") appid: String = "ecb95411883c1a2934892d3ee3540552"
    ) : Single<CitiesWeatherHead>
}