package com.example.quakealertapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quakealertapp.Magnitude
import com.example.quakealertapp.R
import com.example.quakealertapp.constVal.DATE_PATTERN
import com.example.quakealertapp.model.Feature
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Period
import java.time.ZoneId
import java.util.*

class QuakeAlertAdapter():RecyclerView.Adapter<QuakeAlertAdapter.QuakeAlertViewHolder>() {

    var listData :List<Feature> = emptyList()
    fun setQuakeAlert(list:List<Feature>){
        listData = list
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuakeAlertViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list,parent,false)
        return QuakeAlertViewHolder(view)

    }

    override fun getItemCount(): Int {
       return listData.size
    }

    override fun onBindViewHolder(holder: QuakeAlertViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    class QuakeAlertViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        var tvTimeAgo:TextView? = null
        var tvMapSearching:TextView? = null
        var tvQuantity:TextView? = null
        var tvNumberIntensity:TextView? = null
        val sdf = SimpleDateFormat(DATE_PATTERN)


        init{
             tvTimeAgo = itemView.findViewById(R.id.tv_time_ago)
             tvMapSearching = itemView.findViewById(R.id.tv_map_searching)
             tvQuantity = itemView.findViewById(R.id.tv_quantity)
             tvNumberIntensity = itemView.findViewById(R.id.tv_number_intensity)

        }

        fun bind(itemFeature: Feature){
//            val magnitude = itemFeature.properties.magnitude
//            tvMapSearching?.text = itemFeature.properties.locality
//            tvQuantity?.text = String.format("%.1f",magnitude)
//            tvTimeAgo?.text = itemFeature.properties.time
//            val magn = Magnitude.getMagnitude(magnitude)
//            tvNumberIntensity?.text = magn.title.toString()
//            tvNumberIntensity?.background = magn.color.toDrawable()
            initTime(itemFeature)
            initMagnitude(itemFeature)
            initIntensity(itemFeature)
            initLocality(itemFeature)


        }

        private fun initLocality(itemFeature: Feature) {
            val locality = itemFeature.properties.locality
            tvMapSearching?.text = locality
        }

        private fun initIntensity(itemFeature: Feature) {
            val magn = itemFeature.properties.magnitude
            val magnitude = Magnitude.getMagnitude(magn)
            tvNumberIntensity?.apply {
                setBackgroundResource(magnitude.color)
                setText(magnitude.title)
            }
        }

        private fun initMagnitude(itemFeature: Feature) {
            val magnitude = itemFeature.properties.magnitude
            tvQuantity?.text = String.format("%.1f",magnitude)
        }


        private fun initTime(itemFeature: Feature) {

            val timeStr = itemFeature.properties.time
            val timestamp = Instant.parse(timeStr)
            val zone: ZoneId = ZoneId.of("Europe/Paris")
            val date = LocalDateTime.ofInstant(timestamp,zone).toLocalDate()
            val currentDate = LocalDate.now()
            val period = Period.between(date,currentDate).days
            when(period){
                0 -> tvTimeAgo?.text = "Сьогодні"
                1,21,31,41,51,61 -> tvTimeAgo?.text = "$period день тому"
                2,3,4,22,23,24,32,33,34,42,43,44 -> tvTimeAgo?.text = "$period дні тому "
                else -> tvTimeAgo?.text = "$period днів тому"
            }

        }
    }
}
