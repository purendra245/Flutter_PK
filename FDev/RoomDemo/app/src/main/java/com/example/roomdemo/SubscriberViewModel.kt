package com.example.roomdemo

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.Event
import com.example.roomdemo.db.Repository
import kotlinx.coroutines.launch

class SubscriberViewModel(val respositry: Repository) :ViewModel(),Observable{

    @Bindable
    val inputUserName = MutableLiveData<String>()
    @Bindable
     val inputUserEmail = MutableLiveData<String>()
    @Bindable
    val inputUserSaveBtn = MutableLiveData<String>()
    @Bindable
    val inputUserDelBtn = MutableLiveData<String>()



    private var updateOrDelete = false
    private lateinit var subscriberUpdateDetelete: Subscriber

    private val eventStatus = MutableLiveData<Event<String>>()

    val liveDataEvent : LiveData<Event<String>>
    get(){
        return eventStatus
    }


    init {
        inputUserSaveBtn.value = "Save"
        inputUserDelBtn.value = "DeleteAll"
    }



    fun insert() = viewModelScope.launch {
        if(updateOrDelete){
            subscriberUpdateDetelete.email = inputUserEmail.value!!
            subscriberUpdateDetelete.name = inputUserName.value!!
            update()
            eventStatus.value = Event<String>("Subscriber Updated Successfully")
        }else {
            val rowId = respositry.insetSubscriber(Subscriber(0, inputUserName.value!!, inputUserEmail.value!!))
            if(rowId>-1){
                eventStatus.value = Event<String>("Subscriber Inserted $rowId Successfully")
            }

        }
        inputUserName.value = null
        inputUserEmail.value = null

        }


    fun update() = viewModelScope.launch {
        respositry.updateSubscriber(subscriberUpdateDetelete)

    }


    fun delete() = viewModelScope.launch {
        respositry.deleteSubscriber(subscriberUpdateDetelete)

    }



    fun deleteAllData()=
        viewModelScope.launch {
            if(updateOrDelete){
               delete()
                eventStatus.value = Event<String>("Subscriber Deleted Successfully")
            }else{
                respositry.deleteAll()
                eventStatus.value = Event<String>("Subscriber All Deleted Successfully")
            }

        }

    fun getAllData(): LiveData<List<Subscriber>> {
       return respositry.getAllData()
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    fun updateOrDelete(subscriber: Subscriber){
        updateOrDelete = true
        inputUserName.value = subscriber.name
        inputUserEmail.value = subscriber.email
        inputUserDelBtn.value = "Delete"
        inputUserSaveBtn.value = "Update"
        subscriberUpdateDetelete = subscriber



    }


}