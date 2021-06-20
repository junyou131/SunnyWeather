package com.qiufeng.sunnyweather.logic.model

import com.google.gson.annotations.SerializedName


/**
 * Author： QF
 * Time： 2021/6/19
 * Description：
 */
data class PlaceResponse(val status:String,val places:List<Place>)
data class Place(val name:String,val location:Location,
@SerializedName("fromatted_address")val address:String)
data class Location(val lng:String,val lat:String)

