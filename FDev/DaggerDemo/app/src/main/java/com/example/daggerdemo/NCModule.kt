package com.example.daggerdemo

import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class NCModule {

//    @Provides
//    fun getBattery(nickle: NCBattery):Battery{
//        return nickle
//    }
    @Binds
    abstract fun getBattery(nickle: NCBattery):Battery
}