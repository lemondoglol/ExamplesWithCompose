package com.example.examplewithcompose.serviceExample

import android.content.Intent
import android.util.Log
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MyService : LifecycleService() {
    private var number = 0

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // running a counter in background
        lifecycleScope.launch {
            while (true) {
                delay(500)
                Log.d("Tuna", "current number: $number")
                number++
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }
}