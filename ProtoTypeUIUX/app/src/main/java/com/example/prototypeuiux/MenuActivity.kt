package com.example.prototypeuiux

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager.OnActivityResultListener
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.example.prototypeuiux.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {
    val REQUEST_CODE = 22
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val scannerButton = findViewById<Button>(R.id.scanner)
        scannerButton.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, cameraView::class.java)
            startActivity(intent)
        })

        val button2 = findViewById<Button>(R.id.button2)
        button2.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, screen404::class.java)
            startActivity(intent)
        })

        val button5 = findViewById<Button>(R.id.button5)
        button5.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, screen404::class.java)
            startActivity(intent)
        })
    }
}