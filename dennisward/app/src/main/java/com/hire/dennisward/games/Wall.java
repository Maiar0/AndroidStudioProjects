package com.hire.dennisward.games;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;


public class Wall implements Collidable{
    private float left, top, right, bottom;
    public Wall(float left, float top, float right, float bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Color.WHITE);
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

}
