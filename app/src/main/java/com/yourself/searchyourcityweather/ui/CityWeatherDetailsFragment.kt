package com.yourself.searchyourcityweather.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.yourself.searchyourcityweather.R
import com.yourself.searchyourcityweather.databinding.FragmentCityWeatherDetalisBinding

class CityWeatherDetailsFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = CityWeatherDetailsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentCityWeatherDetalisBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_city_weather_detalis,
            container,
            false
        )
        val viewModel = ViewModelProvider(requireActivity()).get(CityWeatherViewModel::class.java)
        binding.lifecycleOwner = this
        binding.weatherDetails = viewModel.getSelectedWeatherDetails()

        return binding.root
    }



    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.show()
    }
}
