package com.dekodersi.pkpmobile.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Connection (val numberOfTrain  : Int = 0,
                       val departureTime: Long = 0,
                       val arrivalTime:Long = 0 ,
                       val numberOfFreeSeats:Int = 0):Parcelable
