package com.sanjesh.motomart.Notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi

class Channels(val context: Context) {
    val channel1:String = "Channel1"
    val channel2:String="Channel2"
    @RequiresApi(Build.VERSION_CODES.O)
    fun createNotificationChannels()
    {
        val channel_1 = NotificationChannel(channel1,"highChannel", NotificationManager.IMPORTANCE_HIGH)
        channel_1.description = "Notification from Channel 1"
        val channel_2 = NotificationChannel(channel2,"lowChannel", NotificationManager.IMPORTANCE_LOW)
        channel_2.description = "Notification from Channel 2"
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel_1)
        notificationManager.createNotificationChannel(channel_2)
    }
}