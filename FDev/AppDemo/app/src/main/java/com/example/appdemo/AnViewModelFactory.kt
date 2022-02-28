package com.example.appdemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class AnViewModelFactory(val startingInt: Int) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AnViewModel::class.java)){
            return  AnViewModel(startingInt) as T
        }

        throw IllegalArgumentException("Unknown view model")
    }

}