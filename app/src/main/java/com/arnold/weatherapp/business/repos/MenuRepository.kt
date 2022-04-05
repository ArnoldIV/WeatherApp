package com.arnold.weatherapp.business.repos

import com.arnold.weatherapp.business.ApiProvider
import com.arnold.weatherapp.business.mapToEntity
import com.arnold.weatherapp.business.mapToModel
import com.arnold.weatherapp.business.model.GeoCodeModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

const val SAVED = 1
const val CURRENT = 0
class MenuRepository(api: ApiProvider) : BaseRepository<MenuRepository.Content>(api) {

    private val dbAccess = db.getGeoCodeDao()

    fun getCities(name: String) {
        api.provideGeoCodeApi().getCityByName(name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                Content(it, CURRENT)
            }
            .subscribe {
                dataEmitter.onNext(it)
            }
    }

    fun add(data: GeoCodeModel) {
        getFavouriteListWith { dbAccess.add(data.mapToEntity()) }
    }

    fun remove(data: GeoCodeModel) {
        getFavouriteListWith { dbAccess.remove(data.mapToEntity()) }
    }

    fun updateFavourite() {
        getFavouriteListWith()
    }


    private fun getFavouriteListWith(daoQuery: (() -> Unit)? = null) {
        roomTransaction {
            daoQuery?.let { it() }
            Content(dbAccess.getAll().map { it.mapToModel() }, SAVED)
        }
    }

    data class Content(val data: List<GeoCodeModel>, val purpose: Int)
}