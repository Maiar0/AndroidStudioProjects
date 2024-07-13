package com.example.resume

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity

class LockScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lock_screen)

        var textInput = findViewById<EditText>(R.id.passwordInput)

        val sumbitButton = findViewById<Button>(R.id.sumbit_LockScreen)
        sumbitButton.setOnClickListener(View.OnClickListener {
            val entered_pass = textInput.text.toString()
            if(entered_pass == "ABTech"){

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                println("CHECK: Pass Locked Screen!")

                val prefs = getSharedPreferences("prefs", MODE_PRIVATE)//access preferences
                var editor = prefs.edit()
                editor.putBoolean("firstStart", false).apply()
                println("CHECK: set first start false")

            }
        })

        val appInfo = findViewById<Button>(R.id.lock_AppInfo)
        appInfo.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, lockAppInfo::class.java)
            startActivity(intent)
        })
    }
    override fun onBackPressed() {
        println("CHECK: Back press not allowed.")
    }
}