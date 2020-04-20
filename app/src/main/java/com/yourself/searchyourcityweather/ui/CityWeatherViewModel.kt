package com.yourself.searchyourcityweather.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.yourself.searchyourcityweather.data.*
import com.yourself.searchyourcityweather.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CityWeatherViewModel : ViewModel() {

    private val TAG = CityWeatherViewModel::class.java.name

    @Inject
    lateinit var repository: WeatherRepository

    val cityName = MutableLiveData<String>()

    private lateinit var selectedWeatherDetails: WeatherResponseModel

    var stateLiveData = MutableLiveData<WeatherApiResponseState>()

    init {
        stateLiveData.value = LoadingState(false)
    }

    fun setCityName(city: String) {
        cityName.value = city
    }

    fun getWeatherForCity() {
        stateLiveData.value = LoadingState(false)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                cityName.value?.let {
                    val result = repository.getWeatherOf(it)
                    withContext(Dispatchers.Main) {
                        result.weatherResponseModelList.forEach {
                            apply {
                                   it.main.temp = String.format("%.2f", it.main.temp - 273).toFloat()
                                   it.main.feelsLike =String.format("%.2f", it.main.feelsLike - 273).toFloat()
                            }
                        }
                        stateLiveData.value = DefaultState(result.weatherResponseModelList, true)
                    }
                }
            } catch (exception: Exception) {
                Log.d(TAG, "Error from API ${exception.localizedMessage}")
                withContext(Dispatchers.Main) {
                    stateLiveData.value = ErrorState(exception.localizedMessage, true)
                }
            }
        }
    }

    fun selectedWeatherDetails(weatherResponseModel: WeatherResponseModel) {
        selectedWeatherDetails = weatherResponseModel
    }

    fun getSelectedWeatherDetails(): WeatherResponseModel {
        return selectedWeatherDetails
    }
}
