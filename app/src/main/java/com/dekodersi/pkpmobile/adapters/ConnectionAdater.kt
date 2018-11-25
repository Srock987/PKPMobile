package com.dekodersi.pkpmobile.adapters

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dekodersi.pkpmobile.R
import com.dekodersi.pkpmobile.data.Connection
import kotlinx.android.synthetic.main.connection_list_item.view.*
import java.text.SimpleDateFormat

class ConnectionAdater (private val items: Array<Connection>,val context:Context) :
    RecyclerView.Adapter<ConnectionAdater.ConnectionViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConnectionViewHolder {
        return ConnectionViewHolder(LayoutInflater.from(context).inflate(R.layout.connection_list_item, parent, false))

    }



    class ConnectionViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        val trainNumber = view.numberOfTrain
        val departureTime = view.departureTime
        val arrivalTime = view.arrivalTime

    }




        override fun onBindViewHolder(holder: ConnectionViewHolder, position: Int) {

            holder.trainNumber.text = "Train no.\n"+items[position].numberOfTrain.toString()
            holder.departureTime.text ="Departure:\n"+ SimpleDateFormat("HH:mm").format(items[position].departureTime)
            holder.arrivalTime.text ="Arrival:\n"+SimpleDateFormat("HH:mm").format(items[position].arrivalTime)



    }


    override fun getItemCount() = items.size
}

