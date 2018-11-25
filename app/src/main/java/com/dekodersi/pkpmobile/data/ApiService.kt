package com.dekodersi.pkpmobile.data

import androidx.lifecycle.LiveData
import com.dekodersi.pkpmobile.data.entity.Seat
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/connections/{connectionId}")
    fun fetchSeats(@Path("connectionId") connectionId: Int): LiveData<List<Seat>>

}