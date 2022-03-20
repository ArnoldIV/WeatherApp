package com.arnold.weatherapp.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arnold.weatherapp.R
import com.arnold.weatherapp.bussiness.model.HourlyWeatherModel
import com.arnold.weatherapp.databinding.ItemMainHourlyBinding


class MainHourlyListAdapter : BaseAdapter<HourlyWeatherModel>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_main_hourly, parent,
            false
        )
        return HourlyViewHolder(view)
    }

    inner class HourlyViewHolder(view: View) : BaseViewHolder(view) {
        private val binding = ItemMainHourlyBinding.bind(view)

        override fun bindView(position: Int) {
            with(binding){
                itemMainHourlyTimeTv.text = "14:00"
                itemMainHourlyPopTv.text = "24%"
                itemMainHourlyTempIcon.setImageResource(R.drawable.ic_sun)
                itemMainHourlyTempTv.text = "14°"
            }
        }

    }

}