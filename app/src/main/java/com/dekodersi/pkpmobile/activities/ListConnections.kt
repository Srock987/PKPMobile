package com.dekodersi.pkpmobile.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.dekodersi.pkpmobile.R
import com.dekodersi.pkpmobile.adapters.ConnectionAdater
import com.dekodersi.pkpmobile.data.Connection
import kotlinx.android.synthetic.main.activity_list_connections.*

class ListConnections : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_connections)

        departureCity.text = getIntent().getExtras().getString("departureCity")
        arrivalCity.text = getIntent().getExtras().getString("arrivalCity")


        val arrayConnection: Array<Connection> = filledList()


        connectionRecyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        connectionRecyclerView.adapter = ConnectionAdater(arrayConnection,context = this@ListConnections)

    }


    fun filledList() : Array<Connection>{
        var list = mutableListOf<Connection>()
        for (i in 1..23){
            list.add(Connection(i,1543173501L,1543180821L,3))
        }
        return list.toTypedArray()
    }
}
