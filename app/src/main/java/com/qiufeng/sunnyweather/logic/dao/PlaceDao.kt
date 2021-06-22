package com.qiufeng.sunnyweather.logic.dao

import android.content.Context
import androidx.core.content.edit
import com.google.gson.Gson
import com.qiufeng.sunnyweather.application.SunnyWeatherApplication
import com.qiufeng.sunnyweather.logic.model.Place

/**
 * Author： QF
 * Time： 2021/6/22
 * Description：
 */
object PlaceDao {
    fun savePlace(place: Place) {
        sharePreferences().edit {
            putString("place", Gson().toJson(place))
        }
    }

    fun getSavePlace(): Place {
        val placeJson = sharePreferences().getString("place", "")
        return Gson().fromJson(placeJson, Place::class.java)
    }

    private fun sharePreferences() =
        SunnyWeatherApplication.context.getSharedPreferences("sunny_weather", Context.MODE_PRIVATE)

    fun isPlaceSaved() = sharePreferences().contains("place")
}