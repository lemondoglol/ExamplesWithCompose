package com.example.examplewithcompose

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import dagger.hilt.android.AndroidEntryPoint

//startActivity(Intent(this, SecondActivity::class.java))
@AndroidEntryPoint
class SecondActivity : AppCompatActivity() {

    /**
     *  Photo Selector Handler, Done
     */
    private val getUriContentLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        // handle uri
        uri?.let {
            Toast.makeText(this, uri.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Ringtone, use custom contract Done
     * */
    private val customRingtoneLauncher = registerForActivityResult(
        PickRingtone()
    ) { uri: Uri? ->
        uri?.let {
            Toast.makeText(this, "Ringtone $uri", Toast.LENGTH_SHORT).show()
        }
    }

    private val defaultRingtoneLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        when (it.resultCode) {
            Activity.RESULT_OK -> {
                Toast.makeText(this, "Ring tone success", Toast.LENGTH_SHORT).show()
            }
            else -> {
                Toast.makeText(this, "Ring tone fail", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * Permissions, ToDo
     * */
    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            when {
                isGranted -> {
                    // Permission is granted. Continue the action or workflow in your
                    // app.
                }
                else -> {
                    // Explain to the user that the feature is unavailable because the
                    // features requires a permission that the user has denied. At the
                    // same time, respect the user's decision. Don't link to system
                    // settings in an effort to convince the user to change their
                    // decision.
                }
            }
        }

    /**
     * Camera, TODO
     * */
    private val camera = registerForActivityResult(
        ActivityResultContracts.TakePicturePreview()
    ) { bitmap ->
        // use bitmap
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        /**
         * trigger image selector from image folder, once selected, it will return and handled
         * in getUriContent()
         * */
        findViewById<Button>(R.id.select_button).setOnClickListener {
            getUriContentLauncher.launch("image/*")
        }

        findViewById<Button>(R.id.ring_tone_button).setOnClickListener {
//            customRingtoneLauncher.launch(RingtoneManager.TYPE_ALL)
            defaultRingtoneLauncher.launch(
                Intent(RingtoneManager.ACTION_RINGTONE_PICKER).apply {
                    putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_ALL)
                }
            )
        }

        // TODO
        findViewById<Button>(R.id.camera_button).setOnClickListener {
            if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                // explain to the user why the permission is needed
            } else {
                requestPermissionLauncher.launch(Manifest.permission.CAMERA)
            }
        }
    }
}

/**
 * Custom Contract, if this is not needed, we can just simply pass
 * StartActivityForResult contract
 * */
class PickRingtone : ActivityResultContract<Int, Uri?>() {
    override fun createIntent(context: Context, input: Int): Intent =
        Intent(RingtoneManager.ACTION_RINGTONE_PICKER).apply {
            putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, input)
        }

    override fun parseResult(resultCode: Int, intent: Intent?): Uri? {
        return when (resultCode) {
            Activity.RESULT_OK -> intent?.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI)
            else -> null
        }
    }
}