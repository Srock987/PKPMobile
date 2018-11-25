package com.dekodersi.pkpmobile

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dekodersi.pkpmobile.adapters.SelectedAdapter
import com.dekodersi.pkpmobile.fragments.WagonFragment
import com.dekodersi.pkpmobile.viewmodels.SeatViewModelFactory
import com.dekodersi.pkpmobile.viewmodels.SeatsViewModel
import kotlinx.android.synthetic.main.activity_place_picker.*
import javax.inject.Inject

class PlacePickerActivity : AppCompatActivity() {

    private lateinit var selectedSeatsAdapter: SelectedAdapter

    @Inject
    lateinit var viewModelFactory: SeatViewModelFactory

    lateinit var viewModel: SeatsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_picker)
        PkpApplication.apiComponent.inject(this)
        viewModel = ViewModelProviders.of(this,viewModelFactory).get(SeatsViewModel::class.java)
        startFragment()
        nextWagonButton.setOnClickListener{
            viewModel.nextWagon()
        }
        previousWagonButton.setOnClickListener{
            viewModel.previousWagon()
        }
    }

    private fun startFragment(){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.wagonPlaceHolder,provideFragment())
            .addToBackStack("FUCK THIS SHIT")
            .commit()
        selectedSeatsAdapter = SelectedAdapter(mutableListOf())
        selectedPlacesRecycler.adapter = selectedSeatsAdapter
        selectedPlacesRecycler.setLayoutManager(LinearLayoutManager(this))

    }

    override fun onStart() {
        super.onStart()
        viewModel.getSelectedSeats().observe(this, Observer {
            selectedSeatsAdapter.updateData(it)
        })
        viewModel.getWagon().observe(this, Observer {
            onWagonChange(it)
        })
    }

    private fun onWagonChange(wagon: Int){
        wagonNumber.text=wagon.toString()
        if (wagon==1){
            previousWagonButton.isEnabled=false
        } else if (wagon==8){
            nextWagonButton.isEnabled=false
        } else{
            previousWagonButton.isEnabled=true
            nextWagonButton.isEnabled=true
        }
    }

    private fun provideFragment(): Fragment {
        val oldFragment = supportFragmentManager
            .findFragmentByTag("FUCK THIS SHIT")
        if(oldFragment!=null){
            return oldFragment
        } else return WagonFragment()
    }
}