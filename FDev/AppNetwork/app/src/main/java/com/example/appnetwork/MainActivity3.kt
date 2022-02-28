package com.example.appnetwork

import android.app.RemoteInput
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main3.*

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        receiveInput()
    }

    fun receiveInput(){
        val KEY_REPLY = "key_reply"
        val intent = intent
        val remoteInput = RemoteInput.getResultsFromIntent(intent)
        if(remoteInput!=null){
            val inputString  = remoteInput.getString(KEY_REPLY)
            textView.text = inputString
        }

    }
}