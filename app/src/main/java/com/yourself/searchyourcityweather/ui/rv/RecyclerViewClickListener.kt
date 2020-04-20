package com.yourself.searchyourcityweather.ui.rv

import com.yourself.searchyourcityweather.data.WeatherResponseModel

interface RecyclerViewClickListener {
    fun onRecyclerViewItemClick(weatherResponseModel: WeatherResponseModel)
}