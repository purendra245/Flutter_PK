package com.example.roomdemo.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomdemo.Subscriber
import com.example.roomdemo.SubscriberDao

@Database(entities = [Subscriber::class],version = 1)
abstract class SubscribeDatabase : RoomDatabase(){

    abstract val subscribeDao: SubscriberDao


    companion object{
        @Volatile
        private var INSTANCE: SubscribeDatabase? = null
        fun getInstance(context: Context):SubscribeDatabase{
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(context.applicationContext,SubscribeDatabase::class.java,
                        "subscribe_data_table").build()
                }
                return instance
            }



        }
    }

}