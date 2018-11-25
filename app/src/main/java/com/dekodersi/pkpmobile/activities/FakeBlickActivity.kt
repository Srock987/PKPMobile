package com.dekodersi.pkpmobile.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dekodersi.pkpmobile.R
import kotlinx.android.synthetic.main.activity_fake_blik.*

class FakeBlickActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fake_blik)
        blikConfirm.setOnClickListener{
            if (blikCodeEditText.text.isNotBlank()){
                val intent = Intent(this,SuccessActivity::class.java)
                startActivity(intent)
            } else{
                Toast.makeText(this,"Blick code cant be processed",Toast.LENGTH_SHORT).show()
            }
        }
    }
}