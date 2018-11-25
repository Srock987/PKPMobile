package com.dekodersi.pkpmobile.adapters

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dekodersi.pkpmobile.R
import com.dekodersi.pkpmobile.data.Connection
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.connection_list_item.*
import kotlinx.android.synthetic.main.connection_list_item.view.*
import java.text.SimpleDateFormat

class ConnectionAdater (private val items: Array<Connection>,val listener: ConnectionListener) :
    RecyclerView.Adapter<ConnectionAdater.ConnectionViewHolder>() {

    interface ConnectionListener{
        fun onConnectionClicked(connection: Connection)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConnectionViewHolder {
        return ConnectionViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.connection_list_item, parent, false),listener)

    }

    internal interface Binder<T> {
        fun bind(data: T)
    }


    class ConnectionViewHolder(override val containerView: View?, val listener: ConnectionListener) : RecyclerView.ViewHolder(containerView!!),
        LayoutContainer, Binder<Connection>  {
        override fun bind(data: Connection) {
            numberOfTrain.text = "Train no.\n"+data.numberOfTrain.toString()
            departureTime.text ="Departure:\n"+ SimpleDateFormat("HH:mm").format(data.departureTime)
            arrivalTime.text ="Arrival:\n"+SimpleDateFormat("HH:mm").format(data.arrivalTime)
            containerView?.setOnClickListener{
                listener.onConnectionClicked(data)
            }
        }

    }


    override fun onBindViewHolder(holder: ConnectionViewHolder, position: Int) {
        holder.bind(items.get(position))

    }


    override fun getItemCount() = items.size



}

