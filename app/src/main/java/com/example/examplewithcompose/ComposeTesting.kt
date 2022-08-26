package com.example.examplewithcompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun GradientColorBackground(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.background(
            brush = Brush.verticalGradient(
                colors = listOf(Color.Blue, Color.Cyan)
            )
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

    }
}

@Composable
fun ScrollToBottomDetect(
    modifier: Modifier = Modifier,
) {
   Scaffold {
       val scrollState = rememberScrollState()
       if (scrollState.isScrollInProgress) {
           DisposableEffect(key1 = Unit) {
               onDispose {
                   if (scrollState.value == scrollState.maxValue) {
                       // do something when it hits the buttom
                   }
               }
           }
       }
       // Other Content
   }
}
