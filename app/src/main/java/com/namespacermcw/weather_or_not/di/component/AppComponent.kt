package com.namespacermcw.weather_or_not.di.component

import com.namespacermcw.weather_or_not.di.modules.NetworkModule
import com.namespacermcw.weather_or_not.vm.WeatherViewModel
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [NetworkModule::class]
)
interface AppComponent {
    fun inject(viewModel: WeatherViewModel)
}