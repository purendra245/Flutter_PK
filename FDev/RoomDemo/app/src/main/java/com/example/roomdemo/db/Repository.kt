package com.example.roomdemo.db

import androidx.lifecycle.LiveData
import com.example.roomdemo.Subscriber
import com.example.roomdemo.SubscriberDao

class Repository(private val subsribeDao: SubscriberDao) {



    suspend fun insetSubscriber(subsribe: Subscriber): Long {
        return subsribeDao.insertSubscriber(subsribe)
    }

    suspend fun updateSubscriber(subsribe: Subscriber){
        subsribeDao.updateSubscriber(subsribe)
    }

    suspend fun deleteSubscriber(subsribe: Subscriber){
        subsribeDao.deleteSubscriber(subsribe)
    }

    suspend fun deleteAll(){
        subsribeDao.deleteAll()
    }

    fun getAllData(): LiveData<List<Subscriber>> {
        return subsribeDao.getAllSubs()
    }



}