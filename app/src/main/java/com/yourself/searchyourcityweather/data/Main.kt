package com.yourself.searchyourcityweather.data

import com.google.gson.annotations.SerializedName

data class Main (var temp : Float, @SerializedName("feels_like") var feelsLike  : Float)