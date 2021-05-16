package com.namespacermcw.weather_or_not.di.modules

import com.namespacermcw.weather_or_not.api.WeatherAPIService
import com.namespacermcw.weather_or_not.api.WeatherRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideWeatherService(): WeatherAPIService {
        return provideRetrofit("https://api.openweathermap.org/data/2.5/")
            .create(WeatherAPIService::class.java)
    }

    @Singleton
    @Provides
    fun provideWeatherRepository(): WeatherRepository {
        return WeatherRepository(provideWeatherService())
    }

}