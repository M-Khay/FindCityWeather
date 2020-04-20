package com.yourself.searchyourcityweather.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.yourself.searchyourcityweather.R
import com.yourself.searchyourcityweather.data.DefaultState
import com.yourself.searchyourcityweather.data.ErrorState
import com.yourself.searchyourcityweather.data.LoadingState
import com.yourself.searchyourcityweather.data.WeatherApiResponseState
import com.yourself.searchyourcityweather.di.ComponentInjector
import com.yourself.searchyourcityweather.ui.rv.CityWeatherListAdapter
import kotlinx.android.synthetic.main.fragment_city_weather_list.*


class CityWeatherListFragment : Fragment() {

    lateinit var adapter: CityWeatherListAdapter

    companion object {
        @JvmStatic
        fun newInstance() = CityWeatherListFragment()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_city_weather_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.show()
        val viewModel =
            ViewModelProvider(requireActivity()).get(CityWeatherViewModel::class.java)


        viewModel.stateLiveData.observe(this.viewLifecycleOwner, weatherListObserver)
        adapter = CityWeatherListAdapter(activity as CityWeatherActivity)
        city_weather_list_rv.adapter = adapter
        city_weather_list_rv.layoutManager = LinearLayoutManager(activity)

        city_weather_list_rv.addItemDecoration(
            DividerItemDecoration(
                city_weather_list_rv.context,
                DividerItemDecoration.VERTICAL
            )
        )
    }


    private val weatherListObserver = Observer<WeatherApiResponseState> { state ->
        state?.let {
            when (state) {
                is DefaultState -> {
                    loading_content.visibility = View.GONE
                    adapter.setWeatherList(state.data)
                    adapter.notifyDataSetChanged()
                }
                is LoadingState -> {
                    loading_content.visibility = View.VISIBLE
                }
                is ErrorState -> {
                    loading_content.visibility = View.GONE
                    Toast.makeText(
                        activity,
                        "Error from API side: ${state.errorMessage}",
                        Toast.LENGTH_LONG
                    ).show()
                    activity?.onBackPressed()

                }
            }
        }
    }

}
