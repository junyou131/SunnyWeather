package com.qiufeng.sunnyweather.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.qiufeng.sunnyweather.logic.Repository
import com.qiufeng.sunnyweather.logic.model.Place

/**
 * Author： QF
 * Time： 2021/6/19
 * Description：
 */
private const val TAG = "PlaceViewModel"

class PlaceViewModel : ViewModel() {
    private val searchLiveData = MutableLiveData<String>()

    val placeList = ArrayList<Place>()

    val placeLiveData = Transformations.switchMap(searchLiveData) { query ->
        Repository.searchPlace(query)
    }

    fun searchPlaces(query: String) {
        searchLiveData.value = query
    }

    fun savePlace(place: Place) = Repository.savePlace(place)
    fun getSavePlace() = Repository.getSavePlace()
    fun isPlaceSaved() = Repository.isPlaceSaved()
}