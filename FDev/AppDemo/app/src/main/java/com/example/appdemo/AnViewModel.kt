package com.example.appdemo

import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AnViewModel(startTotal: Int) : ViewModel(){

    private  var count = MutableLiveData<Int>()


    val total_live_data : LiveData<Int>
        get()= count;

    @Bindable
      var inputText = MutableLiveData<String>()



    init {
        count.value = startTotal
    }

    fun updateCount(){
        count.value = count.value?.plus(inputText.value!!.toInt())
    }



}