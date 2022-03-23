package com.arnold.weatherapp.bussiness.model

data class GeoCodeModel(
    val country: String,
    val lat: Double,
    val local_names: LocalNames,
    val lon: Double,
    val name: String,
    val state: String?,
    var isFavourite: Boolean = false // TODO будет применяться при добавлении городов в любимые в мениактивити
)