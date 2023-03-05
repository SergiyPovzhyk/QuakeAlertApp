package com.example.quakealertapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quakealertapp.R
import com.example.quakealertapp.model.Feature
import com.example.quakealertapp.model.Properties
import com.example.quakealertapp.model.QuakeDto
import org.w3c.dom.Text
import kotlin.math.roundToInt

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
        holder.tvMapSearching?.text = listData[position].properties.locality
        holder.tvQuantity?.text = listData[position].properties.magnitude.roundToInt().toString()
        holder.tvTimeAgo?.text = listData[position].properties.time
    }

    class QuakeAlertViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        var tvTimeAgo:TextView? = null
        var tvMapSearching:TextView? = null
        var tvQuantity:TextView? = null

        init{
             tvTimeAgo = itemView.findViewById(R.id.tv_time_ago)
             tvMapSearching = itemView.findViewById(R.id.tv_map_searching)
             tvQuantity = itemView.findViewById(R.id.tv_quantity)
        }
    }
}