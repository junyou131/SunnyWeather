package com.qiufeng.sunnyweather.application

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

/**
 * Author： QF
 * Time： 2021/6/19
 * Description：
 */
private const val TAG = "SunnyWeatherApplication"

class SunnyWeatherApplication : Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
        const val TOKEN = ""
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}