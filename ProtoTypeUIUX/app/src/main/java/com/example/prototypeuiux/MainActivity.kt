package com.example.prototypeuiux

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import com.example.prototypeuiux.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loginButton = findViewById<Button>(R.id.skipButton)
        loginButton.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        })

        val button3 = findViewById<Button>(R.id.button3)
        button3.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, screen404::class.java)
            startActivity(intent)
        })
        val button4 = findViewById<Button>(R.id.button4)
        button4.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, screen404::class.java)
            startActivity(intent)
        })
    }


}