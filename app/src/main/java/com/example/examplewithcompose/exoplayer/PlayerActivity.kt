package com.example.examplewithcompose.exoplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.media3.common.MediaItem
import androidx.media3.common.MimeTypes
import androidx.media3.common.Player
import androidx.media3.common.util.Util
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.example.examplewithcompose.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * ExoPlayer with Media3 demo
 * */
@AndroidEntryPoint
class PlayerActivity : AppCompatActivity() {

    @Inject
    lateinit var exoPlayer: ExoPlayer

    private val viewModel by viewModels<PlayerActivityViewModel>()

    private val playbackStateListener = playbackStateListener()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        initPlayer()
    }

    private fun initPlayer() {
        findViewById<PlayerView>(R.id.video_view).player = exoPlayer

        // create item
        val mediaItem = MediaItem.fromUri(
            "https://storage.googleapis.com/exoplayer-test-media-0/play.mp3"
            // for MP4, simply replace this URI
        )
        exoPlayer.addMediaItem(mediaItem)

//        val mediaItem1 = MediaItem.fromUri(
//            "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/TearsOfSteel.mp4"
//        )
        // make video DASH, auto adjust quality based on network bandwidth
        val dashMediaItem = MediaItem.Builder()
//            .setUri("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4")
            .setUri(getString(R.string.media_url_dash)) // this link matters if use DASH
            .setMimeType(MimeTypes.APPLICATION_MPD)
            .build()
        exoPlayer.addMediaItem(dashMediaItem)

        // get ready to start player
        exoPlayer.apply {
            this.playWhenReady = viewModel.playWhenReady
            this.seekTo(viewModel.currentItem, viewModel.playbackPosition)
            this.addListener(playbackStateListener)
            this.prepare()
        }
    }

    // allows you to have a full-screen experience, hide the app navigation bar
    private fun hideSystemUi() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(
            window,
            findViewById<PlayerView>(R.id.video_view),
        ).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

    // release resource
    override fun onPause() {
        super.onPause()
        // with API <= 23, there is no guarantee of onStop being called, so we release it as early
        // as onPause is being called
        // with API >=24, onStop is guarantee to be called
        if (Util.SDK_INT <= 23) releasePlayer()
    }

    override fun onStop() {
        super.onStop()
        if (Util.SDK_INT > 23) releasePlayer()
    }

    private fun releasePlayer() {
        viewModel.let {
            it.playbackPosition = exoPlayer.currentPosition
            it.playWhenReady = exoPlayer.playWhenReady
            it.currentItem = exoPlayer.currentMediaItemIndex
        }
        exoPlayer.removeListener(playbackStateListener)
        exoPlayer.release()
    }
}
// outside activity class

// for simplicity
private fun playbackStateListener() = object : Player.Listener {

    override fun onPlaybackStateChanged(playbackState: Int) {
        when (playbackState) {
            ExoPlayer.STATE_IDLE -> {
                // player has been init, but has not yet been prepared
            }
            ExoPlayer.STATE_BUFFERING -> {}
            ExoPlayer.STATE_READY -> {
                Log.d("Tuna", "Read")
            }
            ExoPlayer.STATE_ENDED -> {}
            else -> super.onPlaybackStateChanged(playbackState)
        }
    }
}