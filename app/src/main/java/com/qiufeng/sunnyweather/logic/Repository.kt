package com.qiufeng.sunnyweather.logic

import androidx.lifecycle.liveData
import com.qiufeng.sunnyweather.logic.dao.PlaceDao
import com.qiufeng.sunnyweather.logic.model.Place
import com.qiufeng.sunnyweather.logic.model.Weather
import com.qiufeng.sunnyweather.logic.network.SunnyWeatherNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlin.coroutines.CoroutineContext

/**
 * Author： QF
 * Time： 2021/6/19
 * Description：
 */
object Repository {
    // 封装 try-catch异常捕捉
    private fun <T> fire(block: suspend () -> Result<T>) =
        liveData(Dispatchers.IO) {
            val result = try {
                block()
            } catch (e: Exception) {
                Result.failure<T>(e)
            }
            emit(result)
        }

    fun savePlace(place: Place) = PlaceDao.savePlace(place)
    fun getSavePlace() = PlaceDao.getSavePlace()
    fun isPlaceSaved() = PlaceDao.isPlaceSaved()

    fun searchPlace(query: String) = fire {
        val placeResponse = SunnyWeatherNetwork.searchPlaces(query)
        if (placeResponse.status == "ok") {
            val places = placeResponse.places
            Result.success(places)
        } else {
            Result.failure(RuntimeException("response status is ${placeResponse.status}"))
        }
    }

    fun refreshWeather(lng: String, lat: String) = fire {
        coroutineScope {
            val deferredRealtime = async { SunnyWeatherNetwork.getRealtimeWeather(lng, lat) }
            val deferredDaily = async { SunnyWeatherNetwork.getDailyWeather(lng, lat) }
            val realtimeResponse = deferredRealtime.await()
            val dailyResponse = deferredDaily.await()
            if (realtimeResponse.status == "ok" && dailyResponse.status == "ok") {
                val weather =
                    Weather(realtimeResponse.result.realtime, dailyResponse.result.daily)
                Result.success(weather)
            } else {
                Result.failure(RuntimeException("realtime response status is ${realtimeResponse.status}" + "daily response status is ${dailyResponse.status}"))
            }
        }
    }

}