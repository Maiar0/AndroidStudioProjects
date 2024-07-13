package com.example.prototypeuiux

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.prototypeuiux.databinding.ActivityCameraViewBinding

class cameraView : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityCameraViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera_view)

        val scannerButton = findViewById<Button>(R.id.excuteScan)
        scannerButton.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, scanInfo::class.java)
            startActivity(intent)
        })
    }

}