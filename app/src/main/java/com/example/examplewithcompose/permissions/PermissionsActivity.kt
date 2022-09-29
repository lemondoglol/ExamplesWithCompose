package com.example.examplewithcompose.permissions

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import com.example.examplewithcompose.R

class PermissionsActivity : AppCompatActivity() {

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        // callback that will be called once the user select the permission dialog
        when (isGranted) {
            true -> {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
            }
            false -> {
                Toast.makeText(this, "Permission Not Granted", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permissions)

        findViewById<Button>(R.id.button).setOnClickListener {
            doSomethingWithPermission()
        }
    }

    private fun doSomethingWithPermission() {
        when (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)) {
            PackageManager.PERMISSION_GRANTED -> {
                Toast.makeText(this, "Start Doing Work", Toast.LENGTH_SHORT).show()
            }
            else -> {
                requestPermissionLauncher.launch(Manifest.permission.CAMERA)
            }
        }
    }
}