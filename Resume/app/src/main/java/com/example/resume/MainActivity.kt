package com.example.resume

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.compose.ui.res.booleanResource


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //for debug
        //clear_sharedprefs(); println("CHECK: Cleared SharedPrefs")

        val prefs = getSharedPreferences("prefs", MODE_PRIVATE)//creates preferences
        var firstStart =  prefs.getBoolean("firstStart",true)
        println("CHECK: firstStart " + firstStart )

        if(firstStart){

            lockScreen_Open()
        }

        val resumeButton = findViewById<Button>(R.id.Resume)
        resumeButton.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, ResumeActivity::class.java)
            startActivity(intent)
        })

        val projectsButton = findViewById<Button>(R.id.projects)
        projectsButton.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, ProjectsActivity::class.java)
            startActivity(intent)
        })

        val contactButton = findViewById<Button>(R.id.menu_contact)
        contactButton.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, ContactInfo::class.java)
            startActivity(intent)
        })

        /*val otherButton = findViewById<Button>(R.id.Other)
        otherButton.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, LockScreen::class.java)
            startActivity(intent)
        })*/
    }

    override fun onBackPressed() {
        println("CHECK: Back press not allowed.")
    }

    fun lockScreen_Open(){

        val intent = Intent(this, LockScreen::class.java)
        startActivity(intent)
    }
    fun clear_sharedprefs(){
        val prefs = getSharedPreferences("prefs", MODE_PRIVATE)//access preferences
        var editor = prefs.edit()
        editor.clear().apply()
    }

    fun appUpdate(){/*todo*/}

}
