package com.example.roomdemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.roomdemo.db.Repository
import java.lang.IllegalArgumentException

class SubscriberViewModelFactory (private  val repository: Repository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SubscriberViewModel::class.java)){
            return  SubscriberViewModel(repository ) as T
        }

        throw  IllegalArgumentException("Illegal argument exception")
    }
}