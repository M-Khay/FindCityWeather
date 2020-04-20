package com.yourself.searchyourcityweather.ui.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yourself.searchyourcityweather.data.WeatherResponseModel
import com.yourself.searchyourcityweather.databinding.CityWeatherListItemBinding

class CityWeatherListAdapter(private val recyclerViewClickListener: RecyclerViewClickListener) : RecyclerView.Adapter<CityWeatherListViewHolder>() {

    private var weatherList = emptyList<WeatherResponseModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityWeatherListViewHolder {

        val cityWeatherListItemBinding =
            CityWeatherListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return CityWeatherListViewHolder(cityWeatherListItemBinding.root, cityWeatherListItemBinding)
    }

    override fun getItemCount(): Int {
       return weatherList.size
    }

    override fun onBindViewHolder(holder: CityWeatherListViewHolder, position: Int) {
        val weatherListItem = weatherList.get(position)
        holder.setData(weatherListItem)
        holder.setOnRecyclerViewClickListener(recyclerViewClickListener,weatherListItem)
    }

    fun setWeatherList(cityWeatherList: List<WeatherResponseModel>) {
        weatherList = cityWeatherList
    }
}
