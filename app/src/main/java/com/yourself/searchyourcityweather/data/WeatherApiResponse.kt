package com.yourself.searchyourcityweather.data

import com.google.gson.annotations.SerializedName

data class WeatherApiResponse(@SerializedName("list")
                              var weatherResponseModelList: List<WeatherResponseModel>)