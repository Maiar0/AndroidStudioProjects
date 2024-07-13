package com.example.resume

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.ComponentActivity
import com.github.chrisbanes.photoview.PhotoView

class ContactInfo : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_info)

        val menuButton = findViewById<Button>(R.id.contact_mainmenu)
        menuButton.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        })

        val photoView = findViewById<View>(R.id.contact) as PhotoView
        photoView.setImageResource(R.drawable.contact)
    }
}