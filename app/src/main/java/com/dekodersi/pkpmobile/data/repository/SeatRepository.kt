package com.dekodersi.pkpmobile.data.repository

import androidx.lifecycle.MutableLiveData
import com.dekodersi.pkpmobile.data.entity.Seat
import com.dekodersi.pkpmobile.data.entity.SeatStatus
import com.dekodersi.pkpmobile.data.ApiService

class SeatRepository(val apiService: ApiService) {

    var incrementor = 1
    private var data = MutableLiveData<List<Seat>>()

    fun getSeats(): MutableLiveData<List<Seat>>{
        return data
    }

    fun fetchSeats(connectionId: Int){
        data.value=randomSeatList(connectionId)
    }

    fun randomSeatList(wagonId: Int):List<Seat>{
        incrementor = 1
        val randomSeats = mutableListOf<Seat>()
        for (i in 1..42){
            randomSeats.add(randomSeat(wagonId))
        }
        return randomSeats.toList()
    }

    fun randomSeat(wagonId: Int): Seat {
        val wagonStart = (wagonId-1)*42
        val randomStateInt = (0..1).random()
        val status : SeatStatus
        when(randomStateInt){
            0 -> status= SeatStatus.taken
            1 -> status= SeatStatus.free
            else -> status= SeatStatus.selected
        }
        return Seat(wagonStart+incrementor++, status, wagonId)
    }

}