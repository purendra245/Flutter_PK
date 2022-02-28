package com.example.workmgrapp

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.workmgrapp.MainActivity.Companion.KEY_COUNT_VALUE
import java.lang.Exception

class UploadWorker( context: Context,param: WorkerParameters)  : Worker(context,param) {

    companion object{
        val KEY_WORKER = "KEY_WORKER"
    }

    override fun doWork(): Result {
        return try {

            val count:Int  = inputData.getInt(KEY_COUNT_VALUE,0)

            for (i:Int in 1..count){
                Log.v("TAG Work ===",i.toString())
            }

            val data = Data.Builder().putString(KEY_WORKER,"hello").build()

            Result.success(data)
        }catch (e:Exception){
            Result.failure()
        }

    }
}