package com.dekodersi.pkpmobile.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dekodersi.pkpmobile.data.entity.Seat
import com.dekodersi.pkpmobile.data.repository.SeatRepository

class SeatsViewModel(val repository: SeatRepository) : ViewModel() {

    private lateinit var seatsList: List<Seat>
    private var seatsLivaData = MutableLiveData<List<Seat>>()

    private lateinit var selectedSeats: List<Seat>
    private lateinit var selectedSeatsData: MutableLiveData<List<Seat>>

    private var currentWagon: Int
    private lateinit var wagonData: MutableLiveData<Int>

    init {
        if (!::seatsList.isInitialized){
            seatsList= listOf()
        }
        if (!::selectedSeats.isInitialized){
            selectedSeats= listOf()
        }
        currentWagon = 1
    }

    fun fetchSeats(connectionId: Int){
        repository.fetchSeats(connectionId)
    }

    fun getAllSeats(): MutableLiveData<List<Seat>>{
        val liveData = repository.getSeats()
        liveData.
//        seatsList = seatsLivaData.value!!
       return
    }

    fun getWagon():MutableLiveData<Int>{
        if (!::wagonData.isInitialized){
            wagonData = MutableLiveData()
            wagonData.value=currentWagon
        }
        return wagonData
    }

    fun nextWagon(){
        if (currentWagon<8){
            currentWagon++
        }
        wagonData.value=currentWagon
    }

    fun previousWagon(){
        if (currentWagon>1){
            currentWagon--
        }
        wagonData.value=currentWagon
    }

    fun getSelectedSeats(): MutableLiveData<List<Seat>>{
        if (!::selectedSeatsData.isInitialized){
            selectedSeatsData = MutableLiveData()
        }
        return selectedSeatsData
    }

    fun addSelectedSeat(selectedSeat: Seat){
        val currentSelectedSeats = selectedSeats.toMutableList()
        if (!selectedSeats.contains(selectedSeat)){
            currentSelectedSeats.add(selectedSeat)
        }
        selectedSeats = currentSelectedSeats.toList()
        selectedSeatsData.value = selectedSeats
    }

    fun removeSelectedSeat(selectedSeat: Seat){
        val currentSelectedSeats = selectedSeats.toMutableList()
        var removeIndex = -1
        for (seatIndex in currentSelectedSeats.indices){
            val current = currentSelectedSeats.get(seatIndex)
            if (current.seatNumber == selectedSeat.seatNumber && current.seatWagon==selectedSeat.seatWagon){
                removeIndex = seatIndex
            }
        }
        if (removeIndex != -1) {
            currentSelectedSeats.removeAt(removeIndex)
            selectedSeats = currentSelectedSeats.toList()
            selectedSeatsData.value = selectedSeats
        }
    }


    fun updateSeat(seat: Seat){
        val currentSeats = seatsLivaData.value!!.toMutableList()
        for (i in currentSeats.indices){
            if (currentSeats.get(i).seatNumber==seat.seatNumber){
                currentSeats.set(i,seat)
            }
        }
        seatsLivaData.value=currentSeats
    }


}