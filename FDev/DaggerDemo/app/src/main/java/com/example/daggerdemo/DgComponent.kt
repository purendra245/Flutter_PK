package com.example.daggerdemo

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MemoryModule::class,NCModule::class])
interface DgComponent{

//    fun getSmartPhoneProvider():SmartPhone

    fun inject(mainActivity: MainActivity)

}