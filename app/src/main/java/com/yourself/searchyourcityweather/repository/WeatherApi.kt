package com.yourself.searchyourcityweather.repository

import com.yourself.searchyourcityweather.data.WeatherApiResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherApi {
    @GET("data/2.5/forecast")
    suspend  fun getWeatherOf(@Query("q") city: String,@Query("appid") api_key : String = "65d00499677e59496ca2f318eb68c049"): WeatherApiResponse

}