package com.example.main

import android.annotation.TargetApi
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.AudioAttributes
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat

class Notification_receiver: BroadcastReceiver(){
    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onReceive(context: Context?, intent: Intent?) {

        val channel = NotificationChannel("channelID", "myChannel", NotificationManager.IMPORTANCE_DEFAULT)

        channel.description = "new cards"

        var attrs = AudioAttributes.Builder();
        attrs.setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION);
        attrs.setUsage(AudioAttributes.USAGE_NOTIFICATION);
        channel.setSound(null,null)


        var notificationManager:NotificationManager = context!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager?.createNotificationChannel(channel)

        var repeating_intent:Intent = Intent(context,MyMainActivity::class.java)
        repeating_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        var pendingIntent:PendingIntent = PendingIntent.getActivity(context,100,repeating_intent,PendingIntent.FLAG_UPDATE_CURRENT)

        var  builder:NotificationCompat.Builder = NotificationCompat.Builder(context,"channelID")
            .setContentIntent(pendingIntent)
            .setSmallIcon(android.R.drawable.arrow_up_float)
            .setContentTitle("InformativeApp")
            .setContentText("Remember open InformativeApp!")
            .setAutoCancel(true)

        notificationManager.notify(100,builder.build())



    }


}