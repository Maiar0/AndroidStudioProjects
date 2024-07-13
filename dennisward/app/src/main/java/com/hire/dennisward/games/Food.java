package com.hire.dennisward.games;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;

public class Food implements Collidable{
    private float left, top, right, bottom;
    private float size;

    public Food(float x, float y, float radius) {
        this.size = (float) (Math.sqrt(Math.PI) * (radius * 1.2f )); // Calculate the side length
        setPosition(x, y);
    }

    public void setPosition(float x, float y) {
        this.left = x - size / 2;
        this.top = y - size / 2;
        this.right = x + size / 2;
        this.bottom = y + size / 2;
    }

    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Color.RED);
        canvas.drawRect(left, top, right, bottom, paint);
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
    public float getSize() {return size;}
    public PointF getCenter() {
        float centerX = (left + right) / 2;
        float centerY = (top + bottom) / 2;
        return new PointF(centerX, centerY);
    }
}
