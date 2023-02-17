package com.example.examplewithcompose

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.fragment.app.FragmentActivity
import com.example.examplewithcompose.exoplayer.PlayerActivity
import com.example.examplewithcompose.flow.FlowExampleActivity
import com.example.examplewithcompose.foreground_service.ForegroundServiceExampleActivity
import com.example.examplewithcompose.serviceExample.ServiceActivity
import com.example.examplewithcompose.service_on_bind.FirstServiceActivityOnBind
import com.example.examplewithcompose.ui.theme.ExampleWithComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    private val viewModel by viewModels<MainActivityViewModel>()

    @SuppressLint("CoroutineCreationDuringComposition", "FlowOperatorInvokedInComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExampleWithComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
//                    MainActivityScreen()

                    // testing code starts here
//                    startActivity(Intent(this, PermissionsActivity::class.java))
//                    startActivity(Intent(this, PlayerActivity::class.java))
                    startActivity(Intent(this, FlowExampleActivity::class.java))
                }
            }
        }
    }
}

@Composable
fun ExampleUI(
    modifier: Modifier = Modifier,
) {
}

@Composable
fun MainActivityScreen(
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            Intent(context, ServiceActivity::class.java).also {
                context.startActivity(it)
            }
        }) {
            Text("To Service Example")
        }

        Button(onClick = {
            Intent(context, FirstServiceActivityOnBind::class.java).also {
                context.startActivity(it)
            }
        }) {
            Text("To Bind Service Example")
        }

        Button(onClick = {
            Intent(context, ForegroundServiceExampleActivity::class.java).also {
                context.startActivity(it)
            }
        }) {
            Text("To Foreground Service Example")
        }
    }
}
