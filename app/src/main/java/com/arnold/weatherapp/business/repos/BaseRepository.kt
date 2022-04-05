package com.arnold.weatherapp.business.repos
import android.util.Log
import com.arnold.weatherapp.App
import com.arnold.weatherapp.business.ApiProvider
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject


abstract class BaseRepository<T>(val api : ApiProvider) {

     val dataEmitter : BehaviorSubject<T> = BehaviorSubject.create()
     protected val db by lazy { App.DbSingleton.db }

     protected fun roomTransaction(
          transaction: () -> T,
     ) = Observable.fromCallable { transaction() }
          .onErrorComplete()
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe( {
               dataEmitter.onNext(it)
          },{
               Log.d("error","error")}
          )


}
