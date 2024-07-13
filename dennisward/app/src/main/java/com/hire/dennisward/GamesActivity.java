package com.hire.dennisward;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class GamesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);

        // Find the buttons by their IDs
        Button buttonPong = findViewById(R.id.button_pong);
        Button buttonSnake = findViewById(R.id.button_snake);

        // Set an OnClickListener for the Pong button
        buttonPong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the PongActivity
                Intent intent = new Intent(GamesActivity.this, PongActivity.class);
                startActivity(intent);
            }
        });

        // Set an OnClickListener for the Snake button
        buttonSnake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the SnakeActivity
                Intent intent = new Intent(GamesActivity.this, SnakeActivity.class);
                startActivity(intent);
            }
        });
    }
}