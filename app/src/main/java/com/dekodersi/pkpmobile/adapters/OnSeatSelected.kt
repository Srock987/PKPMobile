package com.dekodersi.pkpmobile.adapters

import com.dekodersi.pkpmobile.data.entity.Seat

interface OnSeatSelected {
    fun seatSelected(seat: Seat)
    fun seatUnselected(seat: Seat)
    fun seatTaken(seat: Seat)
}