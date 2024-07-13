package com.hire.dennisward.games;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.hire.dennisward.PongActivity;

public class Ball {
    private float x, y;
    private float radius;
    private float dx, dy;  // Velocity in x and y directions.

    PongActivity pongActivity;
    public Ball(float x, float y) {
        this.x = x;
        this.y = y;
        this.radius = 20;
        this.dx = 5;
        this.dy = 5;
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

    public void setDx(float dx){
        this.dx = dx;
    }

    public void setDy(float dy){
        this.dy = dy;
    }

    public float getDx(){
        return this.dx;
    }

    public float getDy(){
        return this.dy;
    }

    public float getRadius(){
        return this.radius;
    }
    public void setRadius(float radius){
        this.radius = radius;
    }

    public void reverseX(){
        this.dx = -dx;
    }
    public void reverseY(){
        this.dy = -dy;
    }

    public void update() {
        x += dx;
        y += dy;
        // Add collision detection with walls and paddle.
    }

    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Color.WHITE);
        canvas.drawCircle(x, y, radius, paint);
    }

    public void setBallSpeed(float speed) {
        this.dx = speed;
        this.dy = speed;
    }
}
