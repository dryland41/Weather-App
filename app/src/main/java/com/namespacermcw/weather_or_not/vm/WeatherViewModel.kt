package com.namespacermcw.weather_or_not.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.namespacermcw.weather_or_not.WeatherApplication
import com.namespacermcw.weather_or_not.api.WeatherRepository
import com.namespacermcw.weather_or_not.models.Cities.CitiesWeatherHead
import com.namespacermcw.weather_or_not.models.WeatherHead.WeatherHead
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WeatherViewModel : ViewModel() {

    @Inject
    lateinit var weatherRepo: WeatherRepository

    init {
        WeatherApplication.inject(this)
    }

    private val disposables: CompositeDisposable = CompositeDisposable()
    private val weatherReport: MutableLiveData<WeatherHead> = MutableLiveData()
    private val cities: MutableLiveData<CitiesWeatherHead> = MutableLiveData()

    fun getWeather(): LiveData<WeatherHead> {
        return weatherReport
    }

    fun getCities(): LiveData<CitiesWeatherHead> {
        return cities
    }

    fun getNearbyCities(latitude: Double, longitude: Double) {
        disposables.add(
            weatherRepo.getCitiesObservable(latitude, longitude)
                .subscribeOn(Schedulers.io())
                .subscribe(this::citiesSuccess, this::onError)
        )
    }

    fun getWeatherByCity(city: String) {
        disposables.add(
            weatherRepo.getWeatherObservable(city)
                .subscribeOn(Schedulers.io())
                .subscribe(this::weatherSuccess, this::onError)
        )
    }

    private fun weatherSuccess(weatherHead: WeatherHead) {
        Log.d("_WORK", "Getting weather")
        weatherReport.postValue(weatherHead)
        getNearbyCities(latitude = weatherHead.coord.lat, longitude = weatherHead.coord.lon)
    }

    private fun citiesSuccess(city: CitiesWeatherHead) {
        Log.d("_WORK", "Getting cities")
        cities.postValue(city)
    }

    private fun onError(throwable: Throwable) {
        Log.d("_WORK", "EPIC FAIL! ${throwable.message}")
    }

}