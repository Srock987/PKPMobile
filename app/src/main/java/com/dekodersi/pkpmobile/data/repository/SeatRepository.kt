package com.dekodersi.pkpmobile.data.repository

import androidx.lifecycle.MutableLiveData
import com.dekodersi.pkpmobile.data.entity.Seat
import com.dekodersi.pkpmobile.data.entity.SeatStatus
import com.dekodersi.pkpmobile.data.ApiService

class SeatRepository(val apiService: ApiService) {

    var incrementor = 1
    val data = MutableLiveData<List<Seat>>()

    fun getSeats(connectionId: Int):MutableLiveData<List<Seat>>{
        data.value=randomSeatList(connectionId)
        return data
    }

    fun randomSeatList(wagonId: Int):List<Seat>{
        val randomSeats = mutableListOf<Seat>()
        for (i in 1..42){
            randomSeats.add(randomSeat(wagonId))
        }
        return randomSeats.toList()
    }

    fun randomSeat(wagonId: Int): Seat {
        val randomStateInt = (0..1).random()
        val status : SeatStatus
        when(randomStateInt){
            0 -> status= SeatStatus.taken
            1 -> status= SeatStatus.free
            else -> status= SeatStatus.selected
        }
        return Seat(incrementor++, status, wagonId)
    }

}