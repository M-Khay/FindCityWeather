package com.yourself.searchyourcityweather.ui

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.yourself.searchyourcityweather.R
import com.yourself.searchyourcityweather.data.WeatherResponseModel
import com.yourself.searchyourcityweather.ui.rv.RecyclerViewClickListener
import com.yourself.searchyourcityweather.utils.NetworkConnectivity
import com.yourself.searchyourcityweather.utils.NetworkUtils


class CityWeatherActivity : AppCompatActivity(), RecyclerViewClickListener {

    private lateinit var cityWeatherViewModel: CityWeatherViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (savedInstanceState == null)
            supportFragmentManager.beginTransaction().add(
                R.id.root_layout,
                CitySearchFragment.newInstance(),
                CitySearchFragment.TAG
            ).commitNow()

        cityWeatherViewModel = ViewModelProvider(this).get(CityWeatherViewModel::class.java)
        cityWeatherViewModel.cityName.observe(this, cityNameObserver)

    }

    private val cityNameObserver = Observer<String> {
        if (NetworkConnectivity.isNetworkConnected) {
            supportActionBar?.title = it
            supportFragmentManager.beginTransaction()
                .replace(R.id.root_layout, CityWeatherListFragment.newInstance())
                .addToBackStack(null)
                .commit()
        } else {
            showAlertDialog(resources.getString(R.string.network_error_title), resources.getString(R.string.network_error_message), resources.getString(R.string.alert_dialog_ok))
        }
    }

    override fun onRecyclerViewItemClick(weatherResponseModel: WeatherResponseModel) {
        cityWeatherViewModel.selectedWeatherDetails(weatherResponseModel)
        supportFragmentManager.beginTransaction()
            .replace(R.id.root_layout, CityWeatherDetailsFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showAlertDialog(title: String, message: String, positiveButtonText: String?) {
        val builder = AlertDialog.Builder(this)
        builder.apply {
            builder.setTitle(title)
            builder.setMessage(message)
            positiveButtonText?.let {
                builder.setPositiveButton(positiveButtonText) { dialog, _ ->
                    dialog.dismiss()
                }
            }
        }
        return builder.create().show();
    }
}
