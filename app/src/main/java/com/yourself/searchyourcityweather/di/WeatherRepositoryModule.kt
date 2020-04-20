package com.yourself.searchyourcityweather.di

import android.content.Context
import com.yourself.searchyourcityweather.repository.WeatherApi
import com.yourself.searchyourcityweather.repository.WeatherRepository
import com.yourself.searchyourcityweather.repository.WeatherRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class WeatherRepositoryModule {
    @Provides @Singleton
    fun provideDictionaryRepository(dictionaryApi: WeatherApi): WeatherRepository = WeatherRepositoryImpl(dictionaryApi)
}