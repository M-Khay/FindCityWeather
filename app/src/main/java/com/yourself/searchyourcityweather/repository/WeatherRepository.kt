package com.yourself.searchyourcityweather.repository

import com.yourself.searchyourcityweather.data.WeatherApiResponse

interface WeatherRepository {
    suspend fun getWeatherOf(cityName:String): WeatherApiResponse

}