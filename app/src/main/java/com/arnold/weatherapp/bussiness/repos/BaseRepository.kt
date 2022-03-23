package com.arnold.weatherapp.bussiness.repos

import com.arnold.weatherapp.bussiness.ApiProvider
import io.reactivex.rxjava3.subjects.BehaviorSubject


abstract class BaseRepository<T>(val api : ApiProvider) {

     val dataEmitter : BehaviorSubject<T> = BehaviorSubject.create()
}