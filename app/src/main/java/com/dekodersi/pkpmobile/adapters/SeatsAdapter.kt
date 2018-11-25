package com.dekodersi.pkpmobile.adapters

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dekodersi.pkpmobile.R
import com.dekodersi.pkpmobile.data.entity.Seat
import com.dekodersi.pkpmobile.data.entity.SeatStatus
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.seat_button.*


class SeatsAdapter(private val seatsList: MutableList<Seat>, val listener: OnSeatSelected) : RecyclerView.Adapter<SeatsAdapter.SeatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeatViewHolder {
        return SeatViewHolder(
            LayoutInflater.from(parent.context).inflate(
                viewType,
                parent,
                false
            ), listener
        )
    }

    override fun getItemCount(): Int {
        return seatsList.size
    }

    fun updateData(seatList: List<Seat> ){
        seatsList.clear()
        seatsList.addAll(seatList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: SeatViewHolder, position: Int) {
        holder.bind(seatsList.get(position))
    }

    internal interface Binder<T> {
        fun bind(data: T)
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.seat_button
    }

    class SeatViewHolder(override val containerView: View?,val providedListener: OnSeatSelected) : RecyclerView.ViewHolder(containerView!!), LayoutContainer,
        Binder<Seat> {

        override fun bind(data: Seat) {
            seatNumberTextView.text=data.seatNumber.toString()
            when(data.seatStatus){
                SeatStatus.free -> seatButton.setBackgroundResource(R.drawable.free_bg_shape)
                SeatStatus.taken -> seatButton.setBackgroundResource(R.drawable.taken_bg_shape)
                SeatStatus.selected -> seatButton.setBackgroundResource(R.drawable.selected_bg_shape)
            }
            containerView!!.setOnClickListener{
                Log.d("Seatsadapter","Item clicked")
                when(data.seatStatus){
                    SeatStatus.free -> providedListener.seatSelected(data)
                    SeatStatus.taken -> providedListener.seatTaken(data)
                    SeatStatus.selected -> providedListener.seatUnselected(data)
                }
            }
//            if (checkLeftAisleSeat(data.seatNumber)){
//                val param = containerView.layoutParams as RecyclerView.LayoutParams
//                param.setMargins(4,4,20,4)
//                containerView.layoutParams = param
//            } else if (checkRightAisleSeat(data.seatNumber)){
//                val param = containerView.layoutParams as RecyclerView.LayoutParams
//                param.setMargins(20,4,4,4)
//                containerView.layoutParams = param
//            }

        }

        private fun checkLeftAisleSeat(seatNumber: Int): Boolean{
            return (seatNumber+1)%3==0
        }

        private fun checkRightAisleSeat(seatNumber: Int): Boolean{
            return (seatNumber)%3==0
        }

    }
}