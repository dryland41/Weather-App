package com.namespacermcw.weather_or_not

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.namespacermcw.weather_or_not.adapters.WeatherAdapter
import com.namespacermcw.weather_or_not.databinding.ActivityMainBinding
import com.namespacermcw.weather_or_not.models.Cities.CitiesWeatherHead
import com.namespacermcw.weather_or_not.models.Cities.City
import com.namespacermcw.weather_or_not.models.WeatherHead.WeatherHead
import com.namespacermcw.weather_or_not.vm.WeatherViewModel

class MainActivity : AppCompatActivity(), WeatherAdapter.OnItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var weatherHead: WeatherHead
    private lateinit var cityHead: CitiesWeatherHead
    private lateinit var viewModel: WeatherViewModel
    private lateinit var weatherAdapter: WeatherAdapter
    private val lifecycleOwner = this as LifecycleOwner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        weatherAdapter = WeatherAdapter(this)

        viewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)

        val linearLayoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )

        binding.rvWeather.apply {
            layoutManager = linearLayoutManager
            adapter = weatherAdapter
        }

        viewModel.getWeather().observe(lifecycleOwner, Observer { newWeather ->
            Log.d("_WORK", "Got weather report")
            this.weatherHead = newWeather
            updateWeather()
        })

        viewModel.getCities()
            .observe(lifecycleOwner, Observer { newCities ->
                Log.d("_WORK", "Got cities ${newCities.cityList.size}")
                this.cityHead = newCities
                (binding.rvWeather.adapter as WeatherAdapter).updateList(newCities.cityList)
            })

        viewModel.getWeatherByCity("Dover")

        binding.btnSearch.apply {
            setOnClickListener {
                hideSoftKeyboard(this@MainActivity)
                Log.d("_WORK", "btnSearch Clicked")
                viewModel.getWeatherByCity(binding.etSearch.text.toString())
                viewModel.getNearbyCities(weatherHead.coord.lat, weatherHead.coord.lon)
                (binding.rvWeather.adapter as WeatherAdapter).updateList(cityHead.cityList)
            }
        }
    }

    private fun updateWeather() {
        Log.d("_WORK", "We got weather head  ${weatherHead.name}")

        with(binding) {
            ivWeather.apply {
                Glide.with(this)
                    .load("https://openweathermap.org/img/wn/${weatherHead.weather[0].icon}@4x.png")
                    .into(ivWeather)
            }
            txtTemp.text = weatherHead.main.temp.toInt().toString()
            txtForecast.text = weatherHead.weather[0].main
            txtCity.text = weatherHead.name

            viewModel.getNearbyCities(weatherHead.coord.lat, weatherHead.coord.lon)
        }
    }

    override fun onItemClick(currentCity: City) {
        Log.d("_WORK", "Item clicked")
        viewModel.getWeatherByCity(currentCity.name)
        viewModel.getNearbyCities(currentCity.coord.lat, currentCity.coord.lon)
    }

    private fun hideSoftKeyboard(activity: Activity) {
        val inputMethodManager: InputMethodManager = activity.getSystemService(
            INPUT_METHOD_SERVICE
        ) as InputMethodManager
        if (inputMethodManager.isAcceptingText) {
            inputMethodManager.hideSoftInputFromWindow(
                activity.currentFocus!!.windowToken, 0
            )
        }
    }
}