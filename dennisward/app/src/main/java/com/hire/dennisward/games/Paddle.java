package com.hire.dennisward.games;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Paddle {
    private float x, y;
    private float width, height;


    public Paddle(float x, float y) {
        this.x = x;
        this.y = y;
        this.width = 200;
        this.height = 20;
    }
    public float getX(){
        return this.x;
    }

    public float getY(){
        return this.y;
    }

    public void setX(float x){
        this.x = x;
    }

    public void setY(float y){
        this.y = y;
    }

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }

    public void update() {
        // Update paddle position based on user input.
    }

    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Color.WHITE);
        canvas.drawRect(x - width / 2, y - height / 2, x + width / 2, y + height / 2, paint);
    }

    public boolean checkCollision(Ball ball) {
        return ball.getY() + ball.getRadius() >= y - height / 2
                && ball.getX() >= x - width / 2
                && ball.getX() <= x + width / 2;
    }

}
