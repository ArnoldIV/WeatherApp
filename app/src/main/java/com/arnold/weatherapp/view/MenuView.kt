package com.arnold.weatherapp.view

import com.arnold.weatherapp.business.model.GeoCodeModel
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface MenuView : MvpView {

    @AddToEndSingle
    fun setLoading(flag: Boolean)

    @AddToEndSingle
    fun fillPredictionList(data: List<GeoCodeModel>)

    @AddToEndSingle
    fun fillFavouriteList(data: List<GeoCodeModel>)
}