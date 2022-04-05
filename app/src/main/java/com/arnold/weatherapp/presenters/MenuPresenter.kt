package com.arnold.weatherapp.presenters

import android.util.Log
import com.arnold.weatherapp.business.ApiProvider
import com.arnold.weatherapp.business.model.GeoCodeModel
import com.arnold.weatherapp.business.repos.MenuRepository
import com.arnold.weatherapp.business.repos.SAVED
import com.arnold.weatherapp.view.MenuView


class MenuPresenter : BasePresenter<MenuView>() {
    private val repo = MenuRepository(ApiProvider())

    override fun enable() {
        repo.dataEmitter.subscribe{
            viewState.setLoading(false)
            if(it.purpose == SAVED){
                Log.d("123321", "enable: SAVED ${it.data}")
                viewState.fillFavouriteList(it.data)
            }else{
                Log.d("123321", "enable: CURRENT ${it.data}")

                viewState.fillPredictionList(it.data)
            }
        }
    }

    fun searchFor(str: String){
        repo.getCities(str)
    }

    fun removeLocation(data: GeoCodeModel){
        repo.remove(data)
    }

    fun saveLocation(data: GeoCodeModel){
        repo.add(data)
    }

    fun getFavouriteList() {
        repo.updateFavourite()
    }
}