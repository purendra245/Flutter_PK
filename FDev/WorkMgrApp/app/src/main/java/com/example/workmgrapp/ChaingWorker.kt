package com.example.workmgrapp

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.workmgrapp.MainActivity.Companion.KEY_COUNT_VALUE
import java.lang.Exception

class ChaingWorker(context: Context, param: WorkerParameters)  : Worker(context,param) {

    companion object{
        val KEY_WORKER = "KEY_WORKER"
    }

    override fun doWork(): Result {
        return try {


            for (i:Int in 1..700){
                Log.v("TAG Chaining Work ===",i.toString())
            }

            Result.success()
        }catch (e:Exception){
            Result.failure()
        }

    }
}