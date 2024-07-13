package com.example.resume

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.ComponentActivity
import com.github.chrisbanes.photoview.PhotoView


class ResumeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.resume.R.layout.activity_resume)

        val menuButton = findViewById<Button>(com.example.resume.R.id.resume_mainMenu)
        menuButton.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        })

    }

}