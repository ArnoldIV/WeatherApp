package com.arnold.weatherapp.view.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import butterknife.BindView
import butterknife.ButterKnife
import com.arnold.weatherapp.R
import com.arnold.weatherapp.business.model.DailyWeatherModel
import com.arnold.weatherapp.view.*
import com.google.android.material.textview.MaterialTextView


class MainDailyListAdapter : BaseAdapter<DailyWeatherModel>() {

    lateinit var clickListener: DayItemClick

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_main_daily,parent,false)
        return DailyViewHolder(view)
    }

    interface DayItemClick{
        fun showDetails(data: DailyWeatherModel)
    }

    @SuppressLint("NonConstantResourceId")
    inner class DailyViewHolder(view: View) : BaseViewHolder(view){

        @BindView(R.id.day_container)
        lateinit var container: CardView

        @BindView(R.id.item_daily_date_tv)
        lateinit var date: MaterialTextView

        @BindView(R.id.item_daily_pop_tv)
        lateinit var popRate: MaterialTextView

        @BindView(R.id.item_daily_max_temp_tv)
        lateinit var maxTemp: MaterialTextView

        @BindView(R.id.item_daily_min_temp_tv)
        lateinit var minTemp: MaterialTextView

        @BindView(R.id.item_daily_weather_condition_icon)
        lateinit var icon: ImageView

        init {
            ButterKnife.bind(this,itemView)
        }

        override fun bindView(position: Int) {
            val itemData = mData[position]

            container.setOnClickListener{
                clickListener.showDetails(itemData)
            }

            if (mData.isNotEmpty()){
                itemData.apply {
                    val dateOfDay = dt.toDateFormatOf(DAY_WEEK_NAME_LONG)
                    date.text = if (dateOfDay.startsWith("0",true)) dateOfDay
                        .removePrefix("0") else dateOfDay

                    if (pop < 0.01){
                        popRate.visibility = View.INVISIBLE
                    }else{
                        popRate.visibility = View.VISIBLE
                        popRate.text = pop.toPercentString("%")
                    }

                }
            }

            mData[position].apply {
                date.text = dt.toDateFormatOf(DAY_WEEK_NAME_LONG)
                popRate.text = pop.toPercentString(" %")
                minTemp.text = StringBuilder().append(temp.min.toDegree()).append("°").toString()
                maxTemp.text = StringBuilder().append(temp.max.toDegree()).append("°").toString()
                icon.setImageResource(weather[0].icon.provideIcon())
            }
        }
    }

    }








