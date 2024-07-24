package com.hire.dennisward;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the buttons by their IDs
        Button buttonResume = findViewById(R.id.button_resume);
        Button buttonGitHub = findViewById(R.id.button_github);
        Button buttonGames = findViewById(R.id.button_games);

        // Set an OnClickListener for the Resume button
        buttonResume.setOnClickListener(v -> {
            // Start the ResumeActivity
            Intent intent = new Intent(MainActivity.this, ResumeActivity.class);
            startActivity(intent);
        });

        // Set an OnClickListener for the GitHub button
        buttonGitHub.setOnClickListener(v -> {
            // Start the GamesActivity
            Intent intent = new Intent(MainActivity.this, ResourcesActivity.class);
            startActivity(intent);
        });

        // Set an OnClickListener for the Games button
        buttonGames.setOnClickListener(v -> {
            // Start the GamesActivity
            Intent intent = new Intent(MainActivity.this, GamesActivity.class);
            startActivity(intent);
        });
    }
}