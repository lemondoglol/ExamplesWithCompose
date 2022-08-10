package com.example.examplewithcompose.foreground_service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.lifecycleScope
import com.example.examplewithcompose.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ForegroundService : LifecycleService() {
    private var count = 0

    override fun onCreate() {
        super.onCreate()
        lifecycleScope.launch {
            while (true) {
                delay(1000)
                count++
                Log.d("Tuna", "Current count: $count")
            }
        }

        createNotification()
    }

    private fun createNotification() {
        createNotificationChannel()

        // If the notification supports a direct reply action, use PendingIntent.FLAG_MUTABLE instead.
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            Intent(this, ForegroundServiceExampleActivity::class.java),
            PendingIntent.FLAG_IMMUTABLE
        )

        // note: use NotificationCompat to support lower version
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Im Title")
            .setContentText("Content Text")
            .setContentIntent(pendingIntent)
            .build()

        // Notification ID cannot be 0.
        startForeground(ONGOING_NOTIFICATION_ID, notification)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the NotificationChannel
            val name = CHANNEL_NAME
            val descriptionText = CHANNEL_NAME
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val mChannel = NotificationChannel(CHANNEL_ID, name, importance)
            mChannel.description = descriptionText
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }
    }

    companion object {
        private const val ONGOING_NOTIFICATION_ID = 1
        private const val CHANNEL_NAME = "MY_CHANNEL_NAME"
        private const val CHANNEL_ID = "CHANNEL_ID"
    }
}