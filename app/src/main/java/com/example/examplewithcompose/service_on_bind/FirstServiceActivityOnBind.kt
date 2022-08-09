package com.example.examplewithcompose.service_on_bind

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.examplewithcompose.serviceExample.MyService
import com.example.examplewithcompose.ui.theme.ExampleWithComposeTheme

class FirstServiceActivityOnBind : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExampleWithComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val context = LocalContext.current

                    Button(onClick = {
                        val bindIntent = Intent(context, OnBindService::class.java)
                        val serviceConnection = object : ServiceConnection {
                            override fun onServiceConnected(p0: ComponentName?, service: IBinder?) {
                                (service as OnBindService.MyBinder).service.numberLiveData.observe(
                                    this@FirstServiceActivityOnBind
                                ) {
                                    Toast.makeText(this@FirstServiceActivityOnBind, "current value $it", Toast.LENGTH_SHORT).show()
                                }
                            }

                            override fun onServiceDisconnected(p0: ComponentName?) {}
                        }
                        startService(bindIntent)
                        bindService(bindIntent, serviceConnection, Context.BIND_AUTO_CREATE)
                    }) {
                        Text(text = "Click To Start a Service and then bind service")
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // stop the service when this activity is being destroyed
        Intent(this, MyService::class.java).also {
            stopService(it)
        }
    }

}