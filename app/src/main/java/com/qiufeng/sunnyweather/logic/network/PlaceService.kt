package com.qiufeng.sunnyweather.logic.network

import com.qiufeng.sunnyweather.application.SunnyWeatherApplication
import com.qiufeng.sunnyweather.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Author： QF
 * Time： 2021/6/19
 * Description：
 */
public interface PlaceService {
    @GET("v2/place?token = ${SunnyWeatherApplication.TOKEN}&lang=zh_CN")
    fun searchPlaces(@Query("query") query: String): Call<PlaceResponse>
}