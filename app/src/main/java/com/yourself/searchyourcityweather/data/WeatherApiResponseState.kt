package com.yourself.searchyourcityweather.data

sealed class WeatherApiResponseState {
    abstract val loadedAllItems: Boolean
}


data class DefaultState(val data: List<WeatherResponseModel>, override val loadedAllItems: Boolean):
    WeatherApiResponseState()

data class LoadingState(override val loadedAllItems: Boolean) :
    WeatherApiResponseState()

data class ErrorState(val errorMessage: String, override val loadedAllItems: Boolean) :
    WeatherApiResponseState()

