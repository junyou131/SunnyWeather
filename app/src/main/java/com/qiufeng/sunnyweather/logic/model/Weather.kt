package com.qiufeng.sunnyweather.logic.model

/**
 * Author： QF
 * Time： 2021/6/20
 * Description：
 */
data class Weather(val realtime: RealtimeResponse.Realtime, val daily: DailyResponse.Daily)
