package com.namespacermcw.weather_or_not

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.namespacermcw.weather_or_not.adapters.WeatherAdapter
import com.namespacermcw.weather_or_not.databinding.ActivityMainBinding
import com.namespacermcw.weather_or_not.models.Cities.City
import com.namespacermcw.weather_or_not.models.WeatherHead.WeatherHead
import com.namespacermcw.weather_or_not.vm.WeatherViewModel

class MainActivity : AppCompatActivity(), WeatherAdapter.OnItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var weatherHead: WeatherHead
    private lateinit var viewModel: WeatherViewModel
    private lateinit var weatherAdapter: WeatherAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)
        weatherAdapter = WeatherAdapter(this)

        val linearLayoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )

        binding.rvWeather.apply {
            layoutManager = linearLayoutManager
            adapter = weatherAdapter
        }

        binding.btnSearch.apply {
            setOnClickListener {
                hideSoftKeyboard(this@MainActivity)
                Log.d("_WORK", "btnSearch Clicked")
                viewModel.getWeatherByCity(binding.etSearch.text.toString())
            }
        }

        viewModel.getWeather().observe(this, Observer { newWeather ->
            Log.d("_WORK", "Got weather report")
            updateWeather(newWeather)
        })

        viewModel.getCities()
            .observe(this, Observer { newWeather ->
                Log.d("_WORK", "Got cities ${newWeather.cityList.size}")
                (binding.rvWeather.adapter as WeatherAdapter).updateList(newWeather.cityList)
            })

        viewModel.getWeatherByCity("Dover")

    }

    private fun updateWeather(weatherHead: WeatherHead) {
        Log.d("_WORK", "We got weather head  ${weatherHead.name}")
        this.weatherHead = weatherHead

        with(binding) {
            ivWeather.apply {
                Glide.with(this)
                    .load("https://openweathermap.org/img/wn/${weatherHead.weather[0].icon}@4x.png")
                    .into(ivWeather)
            }
            txtTemp.text = weatherHead.main.temp.toInt().toString()
            txtForecast.text = weatherHead.weather[0].main
            txtCity.text = weatherHead.name
        }
    }

    override fun onItemClick(currentCity: String) {
        Log.d("_WORK", "Item clicked")
        viewModel.getWeatherByCity(currentCity)
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