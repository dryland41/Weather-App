package com.namespacermcw.weather_or_not.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.namespacermcw.weather_or_not.databinding.WeatherItemBinding
import com.namespacermcw.weather_or_not.models.Cities.City

class WeatherAdapter(private val listener: OnItemClickListener) : RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    lateinit var binding: WeatherItemBinding

    private var cityList: List<City> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherAdapter.ViewHolder {
        binding = WeatherItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherAdapter.ViewHolder, position: Int) {
        holder.bind(cityList[position])
    }

    override fun getItemCount(): Int = cityList.size

    fun updateList(newList: List<City>) {
        cityList = newList
        notifyDataSetChanged()
    }

    inner class ViewHolder(binding: WeatherItemBinding): RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        private lateinit var weatherItem: City

        fun bind(city: City) {
            weatherItem = city
            with(binding) {
                Glide.with(itemView)
                    .load("https://openweathermap.org/img/wn/${weatherItem.weather[0].icon}@4x.png")
                    .into(ivWeatherItem)
                txtForecastItem.text = weatherItem.weather[0].description
                txtCityItem.text = weatherItem.name
                txtTempItem.text = weatherItem.main.temp.toInt().toString()
            }
        }

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position: Int = adapterPosition
            if(position != RecyclerView.NO_POSITION) {
                listener.onItemClick(weatherItem)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(currentCity: City)
    }
}