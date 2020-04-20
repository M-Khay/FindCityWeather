package com.yourself.searchyourcityweather.di

import com.yourself.searchyourcityweather.ui.CityWeatherViewModel
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = arrayOf(
        AndroidSupportInjectionModule::class, AppModule::class,
        NetworkModule::class, WeatherRepositoryModule::class
    )
)

interface  AppComponent {
    fun inject(cityWeatherViewModel: CityWeatherViewModel)
}


