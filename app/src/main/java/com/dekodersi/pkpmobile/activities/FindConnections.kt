package com.dekodersi.pkpmobile.activities

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.Toast
import com.dekodersi.pkpmobile.R
import kotlinx.android.synthetic.main.activity_find_connections.*
import java.text.SimpleDateFormat
import java.util.*

class FindConnections : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_connections)

        timeTextView.text = SimpleDateFormat("HH:mm").format( Calendar.getInstance().time)
        dateTextView.text = SimpleDateFormat("dd:MM:yyyy").format(Calendar.getInstance().time)


        val stations = arrayOf(
            "Krakow","Gdynia","Kielce","Radom","Nowy Sacz",
            "Rybitwy","Plaszow"
        )

        val departureAdapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1,
            stations
        )

        val arrivalAdapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1,
            stations
        )


        arrivalInput.threshold = 0
        arrivalInput.setAdapter(arrivalAdapter)
        departureInput.threshold = 0
        departureInput.setAdapter(departureAdapter)

        timeTextView.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                timeTextView.text = SimpleDateFormat("HH:mm").format(cal.time)
            }
            TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
        }
        
        dateTextView.setOnClickListener {
            val cal = Calendar.getInstance()
            val dateSetListener = DatePickerDialog.OnDateSetListener { datePicker, year, month, date ->
                cal.set(Calendar.YEAR,year)
                cal.set(Calendar.MONTH,month)
                cal.set(Calendar.DAY_OF_MONTH,date)
                dateTextView.text = SimpleDateFormat("dd:MM:yyyy").format(cal.time)
            }
            DatePickerDialog(this,dateSetListener,cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH)).show()


        }


        searchButton.setOnClickListener{
            println(arrivalInput.text.toString())
            if(arrivalInput == departureInput){
                Toast.makeText(this,"Arrival and Departure are the same!",Toast.LENGTH_SHORT).show()
            }
            if(arrivalInput == null || departureInput == null){
                Toast.makeText(this,"Arrival or Departure is null",Toast.LENGTH_SHORT).show()
            }
            if(!stations.contains(arrivalInput.text.toString()) || !stations.contains(departureInput.text.toString())) {
                Toast.makeText(this,"Arrival or Departure not in stations list",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this,"Dziala Elo",Toast.LENGTH_SHORT).show()
                onSuccessfulPick()
            }



        }
    }
    private fun onSuccessfulPick(): Unit
    {

        val intent = Intent(this, ListConnections::class.java)
        intent.putExtra("departureCity",departureInput.text.toString())
        intent.putExtra("arrivalCity",arrivalInput.text.toString())
        startActivity(intent)
    }
}
