package com.example.examplewithcompose.serviceExample

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import com.example.examplewithcompose.ui.theme.ExampleWithComposeTheme

class ServiceActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExampleWithComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Button(onClick = {
                        Intent(this, MyService::class.java).also {
                            startService(it)
                        }
                    }) {
                        Text(text = "Click To Start a Service")
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