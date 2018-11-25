package com.dekodersi.pkpmobile.activities

import android.app.Application
import com.dekodersi.pkpmobile.data.ApiComponent
import com.dekodersi.pkpmobile.data.ApiModule
import com.dekodersi.pkpmobile.data.DaggerApiComponent

class PkpApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        apiComponent = DaggerApiComponent.builder()
            .apiModule(ApiModule())
            .build()
    }

    companion object {
        lateinit var apiComponent: ApiComponent
    }

}