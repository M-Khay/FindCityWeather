package com.yourself.searchyourcityweather

import android.app.Application
import com.yourself.searchyourcityweather.di.ComponentInjector
import com.yourself.searchyourcityweather.utils.NetworkUtils

// appComponent lives in the Application class to share its lifecycle
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        ComponentInjector.init()

        NetworkUtils.registerNetworkCallback(this)

    }

}