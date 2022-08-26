package com.example.examplewithcompose.common_ui

import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder

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

@Composable
fun LoadGif(
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    // make this singleTon, and share through out the app
    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()

    Image(
        painter = rememberAsyncImagePainter(
            model = "https://media.giphy.com/media/139eZBmH1HTyRa/giphy.gif",
            imageLoader = imageLoader,
        ),
        contentDescription = "contentDescription"
    )
}