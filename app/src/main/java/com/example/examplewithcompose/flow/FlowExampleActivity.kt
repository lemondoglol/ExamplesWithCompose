package com.example.examplewithcompose.flow

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.fragment.app.FragmentActivity
import com.example.examplewithcompose.ui.theme.ExampleWithComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FlowExampleActivity : FragmentActivity() {

    private val viewModel by viewModels<FlowExampleViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExampleWithComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Button(
                            onClick = viewModel::updateIsLoading
                        ) {
                            Text("Update IsLoading")
                        }
                        Button(
                            onClick = viewModel::updateIsEligible
                        ) {
                            Text("Update IsEligible")
                        }

                        val state by viewModel.canAllowProcess.collectAsState()
                        Text(
                            "Current Value: $state"
                        )
                    }
                }
            }
        }
    }
}