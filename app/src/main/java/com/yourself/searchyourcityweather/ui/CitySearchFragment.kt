package com.yourself.searchyourcityweather.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.yourself.searchyourcityweather.R
import com.yourself.searchyourcityweather.di.ComponentInjector
import kotlinx.android.synthetic.main.search_city_weather_fragment.*

class CitySearchFragment : Fragment() {

    companion object {
        val TAG = CitySearchFragment::class.java.name
        fun newInstance() = CitySearchFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.search_city_weather_fragment, container, false)
    }

    override fun onStart() {

        super.onStart()
        (activity as AppCompatActivity).supportActionBar?.hide()
        val viewModel = ViewModelProvider(requireActivity()).get(CityWeatherViewModel::class.java).also{
            ComponentInjector.component.inject(it)
        }

        lookup.setOnClickListener {
            val enteredCityName = search_city.text.toString()
            if (TextUtils.isEmpty(enteredCityName)) {
                Toast.makeText(activity, "City Name cannot be empty", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.setCityName(enteredCityName)
                viewModel.getWeatherForCity()
            }

        }
    }
}
