package com.arnold.weatherapp.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arnold.weatherapp.R
import com.arnold.weatherapp.bussiness.model.DailyWeatherModel
import com.arnold.weatherapp.databinding.ItemMainDailyBinding


class MainDailyListAdapter : BaseAdapter<DailyWeatherModel>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_main_daily,parent,
        false)
        return DailyViewHolder(view)
    }

    inner class DailyViewHolder(view:View) : BaseViewHolder(view) {
        private val binding = ItemMainDailyBinding.bind(view)
        override fun bindView(position: Int) {
            with(binding){
                itemDailyDateTv.text = "17 saturday"
                itemDailyWeatherConditionIcon.setImageResource(R.drawable.ic_sun)
                itemDailyPopTv.text = "80%"
                itemDailyMaxTempTv.text = "29°"
                itemDailyMinTempTv.text = "14°"
            }
        }

    }
}
