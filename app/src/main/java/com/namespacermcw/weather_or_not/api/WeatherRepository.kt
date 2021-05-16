package com.namespacermcw.weather_or_not.api

import com.namespacermcw.weather_or_not.models.Cities.CitiesWeatherHead
import com.namespacermcw.weather_or_not.models.WeatherHead.WeatherHead
import io.reactivex.Single

class WeatherRepository(private val service: WeatherAPIService) {

    fun getWeatherObservable(myCity: String) : Single<WeatherHead> {
        return service.getWeather(city=myCity)
    }

    fun getCitiesObservable(latitude: Double, longitude: Double): Single<CitiesWeatherHead> {
        return service.getCities(lat=latitude, lon=longitude)
    }
}