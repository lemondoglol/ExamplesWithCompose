package com.example.examplewithcompose.firebase_message

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        // you can do something here such as sending notification
        Log.d("Tuna", "New firebase message received")
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

}