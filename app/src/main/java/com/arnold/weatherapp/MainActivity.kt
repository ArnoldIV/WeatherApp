package com.arnold.weatherapp

import android.annotation.SuppressLint
import android.location.Location
import android.os.Bundle
import android.os.Looper
import androidx.recyclerview.widget.LinearLayoutManager
import com.arnold.weatherapp.bussiness.model.DailyWeatherModel
import com.arnold.weatherapp.bussiness.model.HourlyWeatherModel
import com.arnold.weatherapp.bussiness.model.WeatherDataModel
import com.arnold.weatherapp.presenters.MainPresenter
import com.arnold.weatherapp.view.MainView
import com.arnold.weatherapp.view.adapters.MainDailyListAdapter
import com.arnold.weatherapp.view.adapters.MainHourlyListAdapter
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.activity_main.*
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter


class MainActivity : MvpAppCompatActivity(), MainView {

    private val mainPresenter by moxyPresenter { MainPresenter() }

    private val geoService by lazy { LocationServices.getFusedLocationProviderClient(this) }
    private val locationRequest by lazy { initLocationRequest() }
    private lateinit var mLocation: Location

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        initValues()

        main_hourly_list.apply {
            adapter = MainHourlyListAdapter()
            layoutManager = LinearLayoutManager(
                context, LinearLayoutManager.HORIZONTAL,
                false
            )
            setHasFixedSize(true)
        }
        main_daily_list.adapter = MainDailyListAdapter()
        main_daily_list.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL,
            false
        )
        main_daily_list.setHasFixedSize(true)

        geoService.requestLocationUpdates(locationRequest, geoCallBack, Looper.getMainLooper())

        mainPresenter.enable()
    }


    private fun initValues() {
        main_city_name_tv.text = "Kyiv"
        main_date_tv.text = "14 february"
        main_weather_condition_icon.setImageResource(R.drawable.ic_sun)
        main_weather_condition_description.text = "Clear sky"
        main_temp.text = "25°"
        main_temp_min_tv.text = "19°"
        main_temp_max_tv.text = "28°"
        main_weather_image.setImageResource(R.mipmap.clear_cloud_xhdpi)
        main_pressure_tv.text = "1020 hPa"
        main_humidity_tv.text = "80%"
        main_temp_av_tv.text = "25°"
        main_wind_speed_tv.text = "5 m/s"
        main_sunrise_tv.text = "5:00"
        main_sunset_tv.text = "20:00"
    }

    // -------------- moxy code  --------------
    override fun displayLocation(data: String) {
        main_city_name_tv.text = data
    }

    override fun displayCurrentData(data: WeatherDataModel) {
        main_city_name_tv.text = "Kyiv"
        main_date_tv.text = "14 february"
        main_weather_condition_icon.setImageResource(R.drawable.ic_sun)
        main_weather_condition_description.text = "Clear sky"
        main_temp.text = "25°"
        main_temp_min_tv.text = "19°"
        main_temp_max_tv.text = "28°"
        main_weather_image.setImageResource(R.mipmap.clear_cloud_xhdpi)
        main_pressure_tv.text = "1020 hPa"
        main_humidity_tv.text = "80%"
        main_temp_av_tv.text = "25°"
        main_wind_speed_tv.text = "5 m/s"
        main_sunrise_tv.text = "5:00"
        main_sunset_tv.text = "20:00"
    }

    override fun displayHourlyData(data: List<HourlyWeatherModel>) {
        (main_hourly_list.adapter as MainHourlyListAdapter).updateData(data)
    }

    override fun displayDailyData(data: List<DailyWeatherModel>) {
        (main_daily_list.adapter as MainDailyListAdapter).updateData(data)
    }

    override fun displayError(data: Throwable) {

    }

    override fun setLoading(flag: Boolean) {

    }
    // -------------- moxy code  --------------

    // -------------- location code  --------------


    private fun initLocationRequest(): LocationRequest {
        val request = LocationRequest.create()
        return request.apply {
            interval = 10000
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
    }

    private val geoCallBack = object : LocationCallback() {
        override fun onLocationResult(geo: LocationResult) {
            for (location in geo.locations) {
                mLocation = location
                mainPresenter.refresh(mLocation.latitude.toString(),
                mLocation.longitude.toString())
            }
        }
    }




    // -------------- location code  --------------


}