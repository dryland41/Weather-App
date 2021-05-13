package com.namespacermcw.weather_or_not

import android.app.Application
import android.content.Context
import com.namespacermcw.weather_or_not.di.component.AppComponent
import com.namespacermcw.weather_or_not.di.component.DaggerAppComponent
import com.namespacermcw.weather_or_not.vm.WeatherViewModel
import javax.inject.Singleton

@Singleton
class WeatherApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        buildAppComponent(applicationContext)
    }

    companion object {
        lateinit var appComponent: AppComponent

        fun buildAppComponent(context: Context) {
            appComponent = DaggerAppComponent
                .builder()
                .build()
        }

        fun inject(viewModel: WeatherViewModel) {
            appComponent.inject(viewModel)
        }


    }
}