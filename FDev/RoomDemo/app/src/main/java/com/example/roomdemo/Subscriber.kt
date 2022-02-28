package com.example.roomdemo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "subscribe_data_table")
data class Subscriber(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "subscribe_id")
    val id:Int,

    @ColumnInfo(name = "subscribe_name")
    var name:String,

    @ColumnInfo(name = "subscribe_email")
    var email: String
)