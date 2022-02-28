package com.example.roomdemo

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SubscriberDao {

    @Insert
    suspend fun insertSubscriber(subscriber: Subscriber):Long

    @Update
    suspend fun updateSubscriber(subscriber: Subscriber):Int

    @Delete
    suspend fun deleteSubscriber(subscriber: Subscriber):Int

    @Query("Delete  from  subscribe_data_table ")
    suspend fun deleteAll()

    @Query("Select *  from  subscribe_data_table ")
    fun getAllSubs():LiveData<List<Subscriber>>


}