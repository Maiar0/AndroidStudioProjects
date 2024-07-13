package com.hire.dennisward.games;

import android.graphics.RectF;

public interface Collidable {
    float getLeft();
    float getTop();
    float getRight();
    float getBottom();
    public RectF getBounds();
}