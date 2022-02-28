package com.example.daggerdemo

import android.util.Log
import javax.inject.Inject

interface Battery  {

    fun getPower(){
        Log.i("MYTAG","Battery power is available")
    }
}