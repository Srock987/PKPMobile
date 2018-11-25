package com.dekodersi.pkpmobile.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dekodersi.pkpmobile.data.repository.SeatRepository

class SeatViewModelFactory(val repository: SeatRepository) : ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SeatsViewModel(repository) as T
    }

}