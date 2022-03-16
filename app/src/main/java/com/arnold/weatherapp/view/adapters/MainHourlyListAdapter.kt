package com.arnold.weatherapp.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arnold.weatherapp.R

class MainHourlyListAdapter : RecyclerView.Adapter<MainHourlyListAdapter.HourlyViewHolder>() {

    inner class HourlyViewHolder(view: View) : RecyclerView.ViewHolder(view){

    }

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyViewHolder {
         val view = LayoutInflater.from(parent.context).inflate(R.layout.item_main_hourly,parent,
         false)
         return HourlyViewHolder(view)
     }

     override fun onBindViewHolder(holder: HourlyViewHolder, position: Int) {

     }

     override fun getItemCount() = 10
 }