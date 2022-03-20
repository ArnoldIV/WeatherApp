package com.arnold.weatherapp.presenters

import com.arnold.weatherapp.view.MainView

class MainPresenter : BasePresenter<MainView>() {
    override fun enable() {

    }

    fun refresh(lat: String, lon: String) {
        viewState.setLoading(true)
        //TODO тут будеет обращение к репозиторию
    }

}