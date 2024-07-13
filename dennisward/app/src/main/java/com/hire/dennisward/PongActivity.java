package com.hire.dennisward;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hire.dennisward.games.PongView;

public class PongActivity extends AppCompatActivity {
    private PongView pongView;
    private TextView scoreTextView;
    private TextView difficultyTextView;
    private Button menuButton;
    public int ballSpeed = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pongView = new PongView(this, null);
        setContentView(R.layout.activity_pong);

        pongView = findViewById(R.id.pongView);
        scoreTextView = findViewById(R.id.scoreTextView);
        difficultyTextView = findViewById(R.id.difficultyTextView);
        menuButton = findViewById(R.id.menuButton);

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pongView.pause();
                showMenuDialog();
            }
        });

        pongView.setOnGameEventListener(new PongView.OnGameEventListener() {
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
        pongView.resume();  // Resume the game when the activity is resumed.
    }

    @Override
    protected void onPause() {
        super.onPause();
        pongView.pause();  // Pause the game when the activity is paused.
    }

    public void updateScore(int score) {
        scoreTextView.setText("Score: " + score);
    }

    public void updateDifficulty(int difficulty){
        difficultyTextView.setText("Difficulty: " + difficulty);
    }

    private void showMenuDialog() {
        AlertDialog.Builder builderMenu = new AlertDialog.Builder(this);
        View menuDialogView = getLayoutInflater().inflate(R.layout.dialog_pong, null);
        builderMenu.setView(menuDialogView);

        AlertDialog dialog = builderMenu.create();

        TextView dialogScoreTextView = menuDialogView.findViewById(R.id.scoreTextView);
        TextView dialogDifficultyTextView = menuDialogView.findViewById(R.id.difficultyTextView);
        EditText ballSpeedEditText = menuDialogView.findViewById(R.id.ballSpeedEditText);
        Button resumeButton = menuDialogView.findViewById(R.id.resumeButton);
        Button restartButton = menuDialogView.findViewById(R.id.restartButton);
        Button exitButton = menuDialogView.findViewById(R.id.exitButton);

        dialogScoreTextView.setText("Score: " + pongView.getScore());
        dialogDifficultyTextView.setText("Difficulty: " + pongView.getDifficulty());
        ballSpeedEditText.setText(String.valueOf(ballSpeed));

        // Update text color to black
        dialogScoreTextView.setTextColor(Color.BLACK);
        dialogDifficultyTextView.setTextColor(Color.BLACK);

        ballSpeedEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Not needed for this implementation
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Not needed for this implementation
            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    int newSpeed = Integer.parseInt(s.toString());
                    ballSpeed = newSpeed;
                } catch (NumberFormatException e) {
                    ballSpeed = 5;
                }
            }
        });

        resumeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float newSpeed = Float.parseFloat(ballSpeedEditText.getText().toString());
                dialog.dismiss();
                pongView.resume();
            }
        });

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float newSpeed = Float.parseFloat(ballSpeedEditText.getText().toString());
                dialog.dismiss();
                pongView.restartGame();
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                finish();
            }
        });

        dialog.show();
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

        dialogScoreTextView.setText("Score: " + pongView.getScore());
        dialogDifficultyTextView.setText("Difficulty: " + pongView.getDifficulty());

        // Update text color to black
        dialogScoreTextView.setTextColor(Color.BLACK);
        dialogDifficultyTextView.setTextColor(Color.BLACK);

        AlertDialog dialogEnd = builderEnd.create();

        restartEndButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogEnd.dismiss();
                pongView.restartGame();
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

    public int getBallSpeed(){
        return ballSpeed;
    }
}
