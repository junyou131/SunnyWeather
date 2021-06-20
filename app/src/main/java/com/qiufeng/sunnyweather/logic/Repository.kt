package com.qiufeng.sunnyweather.logic

import androidx.lifecycle.liveData
import com.qiufeng.sunnyweather.logic.model.Place
import com.qiufeng.sunnyweather.logic.network.SunnyWeatherNetwork
import kotlinx.coroutines.Dispatchers

/**
 * Author： QF
 * Time： 2021/6/19
 * Description：
 */
object Repository {
    fun searchPlace(query: String) = liveData(Dispatchers.IO) {
        val result = try {
            val placeResponse = SunnyWeatherNetwork.searchPlaces(query)
            if (placeResponse.status == "ok") {
                val places = placeResponse.places
                Result.success(places)
            } else {
                Result.failure(RuntimeException("response status is ${placeResponse.status}"))
            }
        } catch (e: Exception) {
            Result.failure<List<Place>>(e)
        }
        emit(result)

    }
}