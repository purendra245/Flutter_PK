package com.example.workmgrapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.work.*
import com.example.workmgrapp.UploadWorker.Companion.KEY_WORKER
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    companion object{
        const val KEY_COUNT_VALUE = "key_count"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener{
//            setOneTimeWorkRequest()
            periodicWorkRequest()
        }


    }

    fun setOneTimeWorkRequest(){

        val workManager =  WorkManager.getInstance(applicationContext)

        val data: Data = Data.Builder()
            .putInt(KEY_COUNT_VALUE,125)
            .build()

        val constrains = Constraints.Builder()
//            .setRequiresCharging(true)

            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()


//        val uploadRequest: OneTimeWorkRequest = OneTimeWorkRequest.Builder(UploadWorker::class.java)
//            .setConstraints(constrains)
//            .setInputData(data)
//            .build()
//
//        workManager.enqueue(uploadRequest)
//
//
//
//
//
//
//        workManager.getWorkInfoByIdLiveData(uploadRequest.id).observe(
//            this, Observer {
//                text_view.text = it.state.name
//                if(it.state.isFinished) {
//                    val data = it.outputData.getString(KEY_WORKER)
//                    Toast.makeText(this, data, Toast.LENGTH_SHORT).show()
//                }
//
//            }
//        )

        //sequential worker

//        val filteringWorker = OneTimeWorkRequest.Builder(FilteringWorker::class.java).build()
//        val chaingingWorker = OneTimeWorkRequest.Builder(ChaingWorker::class.java).build()
//
//
//        workManager.beginWith(filteringWorker).then(chaingingWorker).enqueue()

        //parllel worker

        val filteringWorker = OneTimeWorkRequest.Builder(FilteringWorker::class.java).build()
        val downloadWorker = OneTimeWorkRequest.Builder(DownloadWorker::class.java).build()
        val chaingingWorker = OneTimeWorkRequest.Builder(ChaingWorker::class.java).build()


        val parellelWorker = mutableListOf<OneTimeWorkRequest>().apply {
            add(filteringWorker)
            add(downloadWorker)
        }


        workManager.beginWith(parellelWorker).then(chaingingWorker).enqueue()

    }



    fun periodicWorkRequest(){
        val perodicWorkReq = PeriodicWorkRequest.
        Builder(DownloadWorker::class.java,16,TimeUnit.MINUTES).
        build()

        WorkManager.getInstance(applicationContext).enqueue(perodicWorkReq)

    }
}