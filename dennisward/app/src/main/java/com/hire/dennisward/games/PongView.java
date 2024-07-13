package com.hire.dennisward.games;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.hire.dennisward.PongActivity;

public class PongView extends SurfaceView implements Runnable {
    private Thread gameThread;
    private boolean isPlaying;
    private OnGameEventListener gameEventListener;
    private int score = 0;
    private int difficulty = 1;
    private boolean hasLost = false;
    private Paint paint;

    private Paddle paddle;
    private Ball ball;
    private Wall topWall, leftWall, rightWall, bottomWall;
    private SurfaceHolder surfaceHolder;
    private PongActivity pongActivity;

    public PongView(Context context, AttributeSet attrs) {
        super(context, attrs);
        surfaceHolder = getHolder();
        paint = new Paint();

        if (context instanceof PongActivity) {
            pongActivity = (PongActivity) context;
        }
    }

    public interface OnGameEventListener {
        void onPlayerLost();
    }

    public void setOnGameEventListener(OnGameEventListener listener) {
        this.gameEventListener = listener;
    }

    @Override
    public void run() {
        while (isPlaying) {
            update();
            draw();
            control();
        }
    }

    private void update() {
        if (paddle == null || ball == null) {
            return;
        }
        paddle.update();
        ball.update();
        handleCollisions();
        updateScore();
        if (difficulty <= score / 3) {
            increaseDifficulty();
            updateDifficulty();
        }
    }

    private void draw() {
        if (surfaceHolder.getSurface().isValid()) {
            Canvas canvas = surfaceHolder.lockCanvas();
            canvas.drawColor(Color.BLACK);

            paddle.draw(canvas, paint);
            ball.draw(canvas, paint);
            topWall.draw(canvas, paint);
            leftWall.draw(canvas, paint);
            rightWall.draw(canvas, paint);

            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    private void control() {
        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void handleCollisions() {
        Log.d("Bjkdshakjlf", "DY = " + ball.getDy());
        // Check collision with the top wall
        if (ball.getY() - ball.getRadius() <= topWall.getBottom()) {
            ball.reverseY();
        }

        if (ball.getY() + ball.getRadius() >= bottomWall.getTop()) {
            isPlaying = false;
            hasLost = true;
            if (gameEventListener != null) {
                gameEventListener.onPlayerLost();
            }
        }

        // Check collision with the left and right walls
        if (ball.getX() - ball.getRadius() <= leftWall.getRight() || ball.getX() + ball.getRadius() >= rightWall.getLeft()) {
            ball.reverseX();
        }

        // Check collision with the paddle
        if (paddle.checkCollision(ball)) {
            score++;
            ball.reverseY();
        }

    }

    public void increaseDifficulty() {
        ball.setDx(ball.getDx() * 1.3f);
        ball.setDy(ball.getDy() * 1.3f);
        difficulty++;
    }


    public void resume() {
        isPlaying = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void pause() {
        try {
            isPlaying = false;
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void restartGame() {
        paddle = new Paddle(getWidth() / 2, getHeight() - 50);
        ball = new Ball(getWidth() / 2, getHeight() / 2);

        topWall = new Wall(0, 0, getWidth(), 20);
        leftWall = new Wall(0, 0, 20, getHeight());
        rightWall = new Wall(getWidth() - 20, 0, getWidth(), getHeight());
        score = 0;
        difficulty = 1;
        pongActivity.updateDifficulty(difficulty);
        ball.setBallSpeed(pongActivity.getBallSpeed());
        hasLost = false;

        resume();  // Resume the game
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                paddle.setX(event.getX());
                break;
        }
        return true;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        // Initialize game objects with view dimensions
        paddle = new Paddle(w / 2, h - 50);
        ball = new Ball(w / 2, h / 2);

        // Initialize the walls
        topWall = new Wall(0, 0, w, 20);
        leftWall = new Wall(0, 0, 20, h);
        rightWall = new Wall(w - 20, 0, w, h);

        //Bottom wall for losing
        bottomWall = new Wall(0, h - 30, w, h);
    }

    private void updateScore() {
        if (pongActivity != null) {
            pongActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    pongActivity.updateScore(score);
                }
            });
        }
    }

    private void updateDifficulty() {
        if (pongActivity != null) {
            pongActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    pongActivity.updateDifficulty(difficulty);
                }
            });
        }
    }

    public int getScore() {
        return score;
    }

    public int getDifficulty() {
        return difficulty;
    }


}

