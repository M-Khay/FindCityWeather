package com.yourself.searchyourcityweather.ui.rv

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.yourself.searchyourcityweather.data.WeatherResponseModel
import com.yourself.searchyourcityweather.databinding.CityWeatherListItemBinding

class CityWeatherListViewHolder constructor(weatherItemView: View,
                                         private val weatherListItemBinding: CityWeatherListItemBinding):
    RecyclerView.ViewHolder( weatherItemView){
    fun setData(weatherResponseModel: WeatherResponseModel){
        weatherListItemBinding.weatherResponseModel = weatherResponseModel
    }

    fun setOnRecyclerViewClickListener(recyclerViewClickListener: RecyclerViewClickListener,weatherResponseModel: WeatherResponseModel){
        weatherListItemBinding.root.setOnClickListener{
            recyclerViewClickListener.onRecyclerViewItemClick(weatherResponseModel)
        }
    }
}