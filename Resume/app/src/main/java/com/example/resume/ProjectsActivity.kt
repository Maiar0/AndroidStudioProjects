package com.example.resume

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.ComponentActivity

class ProjectsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_projects)

        val projectButton1 = findViewById<Button>(R.id.github_button)
        projectButton1.setOnClickListener(View.OnClickListener {
            val url = "https://github.com/Maiar0/Portfolio"
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        })

        val projectButton2 = findViewById<Button>(R.id.video_button)
        projectButton2.setOnClickListener(View.OnClickListener {
            val url = "https://www.youtube.com/playlist?list=PLCWArOvOjisMOQMVsqsQOIelVxEwQGiiv"
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        })

        val menuButton = findViewById<Button>(R.id.projects_mainmenu)
        menuButton.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        })
    }
}