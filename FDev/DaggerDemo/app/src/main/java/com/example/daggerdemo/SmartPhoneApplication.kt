package com.example.daggerdemo

import android.app.Application

class SmartPhoneApplication :Application(){

    lateinit var dgComponent: DgComponent
    override fun onCreate() {
        super.onCreate()
        dgComponent= initDagger()
    }


    fun initDagger(): DgComponent =
         DaggerDgComponent.builder()
            .memoryModule(MemoryModule(1000))
            .build()




}