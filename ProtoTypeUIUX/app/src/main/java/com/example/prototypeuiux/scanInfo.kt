package com.example.prototypeuiux

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class scanInfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_info)

        val optionsbutton= findViewById<Button>(R.id.options)
        optionsbutton.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, screen404::class.java)
            startActivity(intent)
        })
    }
}