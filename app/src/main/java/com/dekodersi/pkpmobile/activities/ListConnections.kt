package com.dekodersi.pkpmobile.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dekodersi.pkpmobile.R
import com.dekodersi.pkpmobile.adapters.ConnectionAdater
import com.dekodersi.pkpmobile.data.Connection
import kotlinx.android.synthetic.main.activity_list_connections.*

class ListConnections : AppCompatActivity(), ConnectionAdater.ConnectionListener {

    companion object {
        const val DESIRED_CONNECTION = "DESIRED_CONNECTION"
        const val CONNECTION_BUNDLE = "CONNECTION_BUNDLE"
    }

    override fun onConnectionClicked(connection: Connection) {
       val intent = Intent(this,PlacePickerActivity::class.java)
        val bundle = Bundle()
        bundle.putParcelable(DESIRED_CONNECTION,connection)
        intent.putExtra(CONNECTION_BUNDLE,bundle)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_connections)

        departureCity.text = getIntent().getExtras()?.getString("departureCity")
        arrivalCity.text = getIntent().getExtras()?.getString("arrivalCity")


        val arrayConnection: Array<Connection> = filledList()


        connectionRecyclerView.layoutManager = LinearLayoutManager(
            this,
            RecyclerView.VERTICAL,
            false
        )
        connectionRecyclerView.adapter = ConnectionAdater(arrayConnection,this)

    }


    fun filledList() : Array<Connection>{
        var list = mutableListOf<Connection>()
        for (i in 1..23){
            list.add(Connection(i,1543173501L,1543180821L,3))
        }
        return list.toTypedArray()
    }
}
