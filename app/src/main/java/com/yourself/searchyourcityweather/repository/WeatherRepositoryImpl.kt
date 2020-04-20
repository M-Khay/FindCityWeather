package com.yourself.searchyourcityweather.repository

import com.yourself.searchyourcityweather.data.WeatherApiResponse

class WeatherRepositoryImpl(private val weatherApi: WeatherApi) : WeatherRepository {
    override suspend fun getWeatherOf(cityName: String): WeatherApiResponse =
        weatherApi.getWeatherOf(cityName)
}