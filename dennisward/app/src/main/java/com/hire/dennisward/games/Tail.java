package com.hire.dennisward.games;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

public class Tail implements Collidable {
    private float left, top, right, bottom;
    private float size;
    private float speed;
    private float x,y;
    private SnakeView.Direction currentDirection;

    public Tail(float x, float y, float radius, float speed, SnakeView.Direction direction) {
        this.size = (float) (Math.sqrt(Math.PI) * (radius * 0.75f)); // Calculate the side length
        this.speed = speed;
        this.currentDirection = direction;
        this.x = x;
        this.y = y;
        setPosition(x, y);
    }

    public void setPosition(float x, float y) {
        this.left = x - size / 2;
        this.top = y - size / 2;
        this.right = x + size / 2;
        this.bottom = y + size / 2;
    }

    public void setDirection(SnakeView.Direction direction) {
        this.currentDirection = direction;
    }

    public SnakeView.Direction getDirection() {
        return currentDirection;
    }

    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Color.GREEN);
        canvas.drawRect(left, top, right, bottom, paint);
    }

    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }
    @Override
    public float getLeft() {
        return left;
    }

    @Override
    public float getTop() {
        return top;
    }

    @Override
    public float getRight() {
        return right;
    }

    @Override
    public float getBottom() {
        return bottom;
    }

    @Override
    public RectF getBounds() {
        return new RectF(left, top, right, bottom);
    }
}
