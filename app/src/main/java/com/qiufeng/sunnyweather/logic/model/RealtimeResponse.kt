package com.qiufeng.sunnyweather.logic.model

import com.google.gson.annotations.SerializedName

/**
 * Author： QF
 * Time： 2021/6/20
 * Description：
 */
data class RealtimeResponse(val status: String, val result: Result) {
    data class Result(val realtime: Realtime)
    data class Realtime(
        val skycon: String, val temperature: Float,
        @SerializedName("air_quality")
        val airQuality: AirQuality
    )

    data class AirQuality(val aqi: AQI)
    data class AQI(val chn: Float)
}
