package com.example.daggerdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.daggerdemo.R
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
     lateinit var smartPhone:SmartPhone

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val smartPhone = SmartPhone(Battery(),SIMCard (ServiceProvider()),MemoryCard()).makeACallWithRecording()

//        smartPhone = DaggerDgComponent.create().getSmartPhoneProvider()
//         DaggerDgComponent.create().inject(this)

        ( application as SmartPhoneApplication).dgComponent.inject(this)

        smartPhone?.makeACallWithRecording()

    }
}
