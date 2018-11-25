package com.dekodersi.pkpmobile.data

import com.dekodersi.pkpmobile.PlacePickerActivity
import com.dekodersi.pkpmobile.fragments.WagonFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(fragment: WagonFragment)

    fun inject(activity: PlacePickerActivity)

}