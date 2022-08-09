package com.example.examplewithcompose.service_on_bind

import android.content.Intent
import android.os.Binder
import android.os.IBinder
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class OnBindService : LifecycleService() {

    val numberLiveData = MutableLiveData(0)

    inner class MyBinder : Binder() {
        val service = this@OnBindService
    }

    override fun onBind(intent: Intent): IBinder {
        super.onBind(intent)
        // so we can access the property defined inside OnBindService
        lifecycleScope.launch {
            while(true) {
                delay(2500)
                numberLiveData.value = numberLiveData.value?.plus(1)
            }
        }
        return MyBinder()
    }
}