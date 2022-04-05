package com.arnold.weatherapp.business

import com.arnold.weatherapp.business.model.GeoCodeModel
import com.arnold.weatherapp.business.room.GeoCodeEntity

fun GeoCodeModel.mapToEntity() = GeoCodeEntity(
    country = this.country,
    local_names = this.local_names,
    lat = this.lat,
    lon = this.lon,
    name = this.name,
    state = this.state ?: "",
    isFavourite = this.isFavourite
)

fun GeoCodeEntity.mapToModel() = GeoCodeModel(
    country = this.country,
    local_names = this.local_names,
    lat = this.lat,
    lon = this.lon,
    name = this.name,
    state = this.state,
    isFavourite = this.isFavourite

)