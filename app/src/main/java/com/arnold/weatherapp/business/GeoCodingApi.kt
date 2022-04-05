package com.arnold.weatherapp.business

import com.arnold.weatherapp.business.model.GeoCodeModel
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface GeoCodingApi {

    @GET("geo/1.0/reverse?")
    fun getCityByCoord(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("limit") limit: String = "10",
        @Query("appid") id: String = "e0665080a56e466da7dfdfafada6a882"

    ): Observable<List<GeoCodeModel>>

    @GET("geo/1.0/direct")
    fun getCityByName(
        @Query("q") name: String,
        @Query("limit") limit: String = "10",
        @Query("appid") appid: String = "e0665080a56e466da7dfdfafada6a882",
    ) : Observable<List<GeoCodeModel>>

}