package com.hire.dennisward.games;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.appcompat.app.AlertDialog;

import com.hire.dennisward.PongActivity;
import com.hire.dennisward.SnakeActivity;

public class SnakeView extends SurfaceView implements Runnable {

    private Thread gameThread;
    private boolean isPlaying;
    private SnakeView.OnGameEventListener gameEventListener;
    private Paint paint;
    private SurfaceHolder surfaceHolder;
    private SnakeHead snakeHead;
    private RectF playableArea;
    private int foodCount;
    private boolean isInit = false;
    private SnakeActivity snakeActivity;
    private float speed = 10.0f;


    private Wall topWall, leftWall, rightWall, bottomWall;
    private Food food;
    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    private Direction currentDirection = Direction.RIGHT;


    public SnakeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        surfaceHolder = getHolder();
        paint = new Paint();

        if (context instanceof SnakeActivity) {
            snakeActivity = (SnakeActivity) context;
        }

        initializeComponents();

    }

    public interface OnGameEventListener {
        void onPlayerLost();
    }

    public void setOnGameEventListener(SnakeView.OnGameEventListener listener) {
        this.gameEventListener = (OnGameEventListener) listener;
    }

    private void initializeComponents(){
        int wallThickness = 20;

        topWall = new Wall(0, 0, getWidth(), wallThickness);
        bottomWall = new Wall(0, getHeight() - wallThickness, getWidth(), getHeight());
        leftWall = new Wall(0, 0, wallThickness, getHeight());
        rightWall = new Wall(getWidth() - wallThickness, 0, getWidth(), getHeight());
        playableArea = getPlayingArea(5.0f);

        if(playableArea != null){
            float foodX = playableArea.left + (float) (Math.random() * (playableArea.width()));
            float foodY = playableArea.top + (float) (Math.random() * (playableArea.height()));
            float foodRadius = 40;
            food = new Food(foodX, foodY, foodRadius);

            foodCount ++;
        }

        float initialX = getWidth() / 2.0f;
        float initialY = getHeight() / 2.0f;
        float radius = 20;
        float speed = this.speed; // Fixed speed for the snake head
        snakeHead = new SnakeHead(initialX, initialY, radius, speed);

        isInit = true;
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
        if (snakeHead == null && food != null && !isInit) {
            return;
        }
        if(food.getCenter().x < 30.0){
            return;
        }
        if(snakeHead.tailSegments == null){
            return;
        }
        if(snakeHead.checkCollision(topWall)){
            PointF newPos = getClosestPointOutsideWall(topWall,snakeHead.getX(),snakeHead.getY());
            snakeHead.setX(newPos.x);
            snakeHead.setY(newPos.y);
            changeDirection(Direction.LEFT);
        }
        if(snakeHead.checkCollision(rightWall)){
            PointF newPos = getClosestPointOutsideWall(rightWall,snakeHead.getX(),snakeHead.getY());
            snakeHead.setX(newPos.x);
            snakeHead.setY(newPos.y);
            changeDirection(Direction.UP);
        }
        if(snakeHead.checkCollision(leftWall)){
            PointF newPos = getClosestPointOutsideWall(leftWall,snakeHead.getX(),snakeHead.getY());
            snakeHead.setX(newPos.x);
            snakeHead.setY(newPos.y);
            changeDirection(Direction.DOWN);
        }
        if(snakeHead.checkCollision(bottomWall)){
            PointF newPos = getClosestPointOutsideWall(bottomWall,snakeHead.getX(),snakeHead.getY());
            snakeHead.setX(newPos.x);
            snakeHead.setY(newPos.y);
            changeDirection(Direction.RIGHT);
        }
        if(snakeHead.checkCollision(food)){
            food = spawnFood();
            snakeHead.addTailSegment();
            foodCount ++;
        }
        if(snakeHead.checkHeadCollidesWithTail()){
            //showGameOverDialog();
            isPlaying = false;
            if (gameEventListener != null) {
                gameEventListener.onPlayerLost();
            }
        }

        snakeHead.update();
    }



    private void draw() {
        if (surfaceHolder.getSurface().isValid()) {
            Canvas canvas = surfaceHolder.lockCanvas();
            canvas.drawColor(Color.BLACK); // Clear the screen with black color

            // Draw the walls
            paint.setColor(Color.RED);
            topWall.draw(canvas, paint);
            bottomWall.draw(canvas, paint);
            leftWall.draw(canvas, paint);
            rightWall.draw(canvas, paint);

            // TODO: Draw other game elements, such as the snake
            snakeHead.draw(canvas,paint);
            if(food != null) {
                food.draw(canvas,paint);
            }

            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    private Food spawnFood(){
        if (playableArea != null) {
            float foodX = playableArea.left + (float) (Math.random() * (playableArea.width()));
            float foodY = playableArea.top + (float) (Math.random() * (playableArea.height()));
            float foodRadius = snakeHead.getRadius(); // Use the radius of the snake head

            return new Food(foodX, foodY, foodRadius);
        }
        return null;
    }
    private void control() {
        try {
            Thread.sleep(17); // Control the game loop to run at ~60 FPS
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        initializeComponents();
    }

    public void changeDirection(Direction newDirection) {
        // Avoid reversing direction directly
        if ((currentDirection == Direction.UP && newDirection != Direction.DOWN) ||
                (currentDirection == Direction.DOWN && newDirection != Direction.UP) ||
                (currentDirection == Direction.LEFT && newDirection != Direction.RIGHT) ||
                (currentDirection == Direction.RIGHT && newDirection != Direction.LEFT)) {
            currentDirection = newDirection;
        }
        snakeHead.setDirection(currentDirection);
    }

    public PointF getClosestPointOutsideWall(Wall wall, float x, float y) {
        float newX = x;
        float newY = y;
        float margin = 5.0f; // Margin to ensure the point is outside the wall
        RectF wallBounds = wall.getBounds();

        if (x < wallBounds.left) {
            newX = wallBounds.left - snakeHead.getRadius() - margin;
        } else if (x > wallBounds.right) {
            newX = wallBounds.right + snakeHead.getRadius() + margin;
        }

        if (y < wallBounds.top) {
            newY = wallBounds.top - snakeHead.getRadius() - margin;
        } else if (y > wallBounds.bottom) {
            newY = wallBounds.bottom + snakeHead.getRadius() + margin;
        }

        return new PointF(newX, newY);
    }

    private RectF getPlayingArea(float padding) {
        float left = leftWall.getRight() + padding;
        float top = topWall.getBottom() + padding;
        float right = rightWall.getLeft() - padding;
        float bottom = bottomWall.getTop() - padding;

        // Ensure right is greater than left and bottom is greater than top
        if (right < left) right = left + 1;
        if (bottom < top) bottom = top + 1;

        return new RectF(left, top, right, bottom);
    }

    public void restartGame() {
        initializeComponents();

        resume();  // Resume the game
    }
    public int getScore(){
        return foodCount;
    }
    public String getSpeed() {
        return String.valueOf(speed);
    }

}