package com.dekodersi.pkpmobile.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dekodersi.pkpmobile.R
import com.dekodersi.pkpmobile.data.entity.Seat
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.selcted_seat_view.*
import java.lang.StringBuilder

class SelectedAdapter(private val seatsList: MutableList<Seat>) : RecyclerView.Adapter<SelectedAdapter.SelectedViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectedViewHolder {
        return SelectedViewHolder(
            LayoutInflater.from(parent.context).inflate(
                viewType,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return seatsList.size
    }

    fun updateData(newList: List<Seat>){
        seatsList.clear()
        seatsList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: SelectedViewHolder, position: Int) {
        holder.bind(seatsList.get(position))
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.selcted_seat_view
    }

    internal interface Binder<T> {
        fun bind(data: T)
    }

    class SelectedViewHolder(override val containerView: View?): RecyclerView.ViewHolder(containerView!!), LayoutContainer, SelectedAdapter.Binder<Seat>{
        override fun bind(data: Seat) {
            val numberBuilder = StringBuilder()
            numberBuilder.append("Seat: ")
            numberBuilder.append(data.seatNumber)
            selectedSeatTextView.text=numberBuilder.toString()

            val wagonBuilder = StringBuilder()
            wagonBuilder.append("Wagon: ")
            wagonBuilder.append(data.seatWagon)
            selectedSeatWagon.text=wagonBuilder.toString()
        }

    }
}