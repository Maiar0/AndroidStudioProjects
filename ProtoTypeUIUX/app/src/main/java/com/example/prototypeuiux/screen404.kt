package com.example.prototypeuiux

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class screen404 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen404)

        val loginButton = findViewById<Button>(R.id.returnButton)
        loginButton.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        })
    }
}