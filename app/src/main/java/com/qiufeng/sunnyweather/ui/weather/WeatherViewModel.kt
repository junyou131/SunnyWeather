package com.qiufeng.sunnyweather.ui.weather

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.qiufeng.sunnyweather.logic.Repository
import com.qiufeng.sunnyweather.logic.model.Location

/**
 * Author： QF
 * Time： 2021/6/21
 * Description：
 */
private const val TAG = "WeatherViewModel"

class WeatherViewModel : ViewModel() {
    private val locationLiveData = MutableLiveData<Location>()

    var locationLng = ""
    var locationLat = ""
    var placeName = ""
    val weatherLiveData = Transformations.switchMap(locationLiveData) { location ->
        Log.e("WeatherViewModel", "locationLng${location.lng}")
        Repository.refreshWeather(
            location.lng,
            location.lat
        )
    }

    fun refreshWeather(lng: String, lat: String) {
        locationLiveData.value = Location(lng, lat)
    }

}