package com.example.daggerdemo

import android.util.Log
import javax.inject.Inject

class NCBattery @Inject constructor():Battery {

    override fun getPower() {
        Log.i("MYTAG","NC Battery power is available")
    }
}