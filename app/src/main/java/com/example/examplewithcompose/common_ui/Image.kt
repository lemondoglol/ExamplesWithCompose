package com.example.examplewithcompose.common_ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun LoadImageFromInternet(
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.height(150.dp).width(75.dp)) {
        AsyncImage(
            modifier = Modifier.clip(CircleShape),
            model = "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885_1280.jpg",
            contentDescription = "cont",
            contentScale = ContentScale.Crop,
        )
    }
}