package com.dekodersi.pkpmobile.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragmen_wagon.*
import androidx.recyclerview.widget.GridLayoutManager
import com.dekodersi.pkpmobile.*
import com.dekodersi.pkpmobile.adapters.OnSeatSelected
import com.dekodersi.pkpmobile.adapters.SeatsAdapter
import com.dekodersi.pkpmobile.data.entity.Seat
import com.dekodersi.pkpmobile.data.entity.SeatStatus
import com.dekodersi.pkpmobile.viewmodels.SeatViewModelFactory
import com.dekodersi.pkpmobile.viewmodels.SeatsViewModel
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject


class WagonFragment : Fragment(), OnSeatSelected {

    override fun seatSelected(seat: Seat) {
        val newSeat = Seat(
            seatNumber = seat.seatNumber,
            seatStatus = SeatStatus.selected,
            seatWagon = seat.seatWagon
        )
        viewModel.updateSeat(newSeat)
        viewModel.addSelectedSeat(newSeat)
    }

    override fun seatUnselected(seat: Seat) {
        val newSeat = Seat(
            seatNumber = seat.seatNumber,
            seatStatus = SeatStatus.free,
            seatWagon = seat.seatWagon)
        viewModel.updateSeat(newSeat)
        viewModel.removeSelectedSeat(newSeat)
    }

    override fun seatTaken(seat: Seat) {
        Snackbar.make(seatsGrid,"Seat already taken",Snackbar.LENGTH_SHORT).show()
    }

    private lateinit var adapter: SeatsAdapter

    @Inject
    lateinit var viewModelFactory: SeatViewModelFactory

    lateinit var viewModel: SeatsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PkpApplication.apiComponent.inject(this)
        adapter= SeatsAdapter(mutableListOf(), this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = activity?.run {
            ViewModelProviders.of(this,viewModelFactory).get(SeatsViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
        viewModel.getAllSeats().observe(this, Observer {
            adapter.updateData(it)
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragmen_wagon,container,false)
    }

    override fun onStart() {
        super.onStart()
        seatsGrid.adapter=adapter
        seatsGrid.setLayoutManager(GridLayoutManager(context, 3))
        viewModel.getWagon().observe(this, Observer {
            showSeats(it)
        })

    }

    private fun showSeats(wagonId: Int){
        viewModel.fetchSeats(wagonId)
    }
}