package com.example.appnetwork

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.example.model.AlbumModel
import com.example.model.AlbumModelItem
import com.example.network.RetrofitInstance
import com.example.services.AlbumService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main2.*
import okhttp3.internal.notify
import retrofit2.Response

class MainActivity2 : AppCompatActivity() {

    var notificationManager : NotificationManager? = null
    private val channecId = "testChannel"
    val KEY_REPLY = "key_reply"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        createNotification(channecId,"notif","test notif")
        button.setOnClickListener {
            displayNotification()
//            createNotification()
        }

    }

    private fun createNotification(id:String,name:String,channelDesc:String) {
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            val important = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(id,name,important)
            channel.description = channelDesc
            notificationManager?.createNotificationChannel(channel)
        }
    }

    private fun displayNotification() {
        val notificationId = 45
        val tapResult = Intent(this,MainActivity3::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        val pendingIntent = PendingIntent.getActivity(this,
            0,
            tapResult,
        PendingIntent.FLAG_UPDATE_CURRENT)

        val action2 = NotificationCompat.Action.Builder(0,"Details",pendingIntent).build()

        val remoteInput :RemoteInput  = RemoteInput.Builder(KEY_REPLY).run {
            setLabel("Reply button ")
                .build()

        }

        val replyAction = NotificationCompat.Action.Builder(0,"reply",pendingIntent).addRemoteInput(remoteInput).build()


        val notificationBuilder = NotificationCompat.Builder(this,channecId).apply {
            setContentTitle("Hello")
            setContentText("hI")
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
//                .addAction(action2)
                .addAction(replyAction)


        } .build()

        notificationManager?.notify(notificationId,notificationBuilder)


    }
}