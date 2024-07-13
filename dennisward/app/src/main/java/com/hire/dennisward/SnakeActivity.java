package com.hire.dennisward;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hire.dennisward.games.PongView;
import com.hire.dennisward.games.SnakeView;

public class SnakeActivity extends AppCompatActivity {

    private SnakeView snakeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snake);

        snakeView = findViewById(R.id.snakeView);


        Button upButton = findViewById(R.id.upButton);
        Button leftButton = findViewById(R.id.leftButton);
        Button rightButton = findViewById(R.id.rightButton);
        Button downButton = findViewById(R.id.downButton);

        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snakeView.changeDirection(SnakeView.Direction.UP);
            }
        });

        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snakeView.changeDirection(SnakeView.Direction.LEFT);
            }
        });

        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snakeView.changeDirection(SnakeView.Direction.RIGHT);
            }
        });

        downButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snakeView.changeDirection(SnakeView.Direction.DOWN);
            }
        });

        snakeView.setOnGameEventListener(new SnakeView.OnGameEventListener() {
            @Override
            public void onPlayerLost() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showGameEndDialog();
                    }
                });
            }
        });
    }



    @Override
    protected void onResume() {
        super.onResume();
        snakeView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        snakeView.pause();
    }


    private void showGameEndDialog() {
        AlertDialog.Builder builderEnd = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View endDialogView = inflater.inflate(R.layout.dialog_pong_end, null);
        builderEnd.setView(endDialogView);

        TextView dialogScoreTextView = endDialogView.findViewById(R.id.scoreTextView);
        TextView dialogDifficultyTextView = endDialogView.findViewById(R.id.difficultyTextView);
        Button restartEndButton = endDialogView.findViewById(R.id.restartButton);
        Button exitEndButton = endDialogView.findViewById(R.id.exitButton);

        dialogScoreTextView.setText("Score: " + snakeView.getScore());
        dialogDifficultyTextView.setText("Difficulty: " + snakeView.getSpeed());

        // Update text color to black
        dialogScoreTextView.setTextColor(Color.BLACK);
        dialogDifficultyTextView.setTextColor(Color.BLACK);

        AlertDialog dialogEnd = builderEnd.create();

        restartEndButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogEnd.dismiss();
                snakeView.restartGame();
            }
        });

        exitEndButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogEnd.dismiss();
                finish();
            }
        });

        dialogEnd.show();
    }
}