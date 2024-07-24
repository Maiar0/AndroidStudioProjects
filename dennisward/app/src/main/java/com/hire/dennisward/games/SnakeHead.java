package com.hire.dennisward.games;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Log;

import java.util.ArrayList;

public class SnakeHead {

    private float x, y; // Position of the snake head
    private float radius; // Radius of the snake head
    private float speed; // Speed of the snake head
    private SnakeView.Direction currentDirection;
    public ArrayList<Tail> tailSegments;
    private ArrayList<PositionAndDirection> previousPositions; // Track previous positions and directions

    private static final float SEGMENT_GAP = 50.0f;
    private boolean hasTail = false;

    public SnakeHead(float x, float y, float radius, float speed) {
        this.x = x;
        this.y = y;
        this.radius = radius * 2.0f;
        this.speed = speed;
        this.currentDirection = SnakeView.Direction.RIGHT; // Initial direction
        tailSegments = new ArrayList<>();
        previousPositions = new ArrayList<>();
    }

    public void update() {

        populatePosList();

        // Move the snake head
        switch (currentDirection) {
            case UP:
                y -= speed;
                break;
            case DOWN:
                y += speed;
                break;
            case LEFT:
                x -= speed;
                break;
            case RIGHT:
                x += speed;
                break;
        }

        // Move the tail segments
        if (!tailSegments.isEmpty() && !previousPositions.isEmpty() && hasTail) {
            updateTailPositions();
        }
    }

    public void printTailSegments() {
        if (tailSegments.isEmpty()) {
            return;
        }
        for (int i = 0; i < tailSegments.size(); i++) {
            Tail segment = tailSegments.get(i);
        }
    }
    private void populatePosList(){
        // Record the current position and direction before moving
        if(previousPositions!= null && previousPositions.size() > 1){
            PositionAndDirection lastPosition = previousPositions.get(previousPositions.size() - 1);
            float distance = calculateDistance(new PointF(x,y),lastPosition.position);
            if(distance > SEGMENT_GAP){
                previousPositions.add(new PositionAndDirection(new PointF(x, y), currentDirection));
            }
        }
        else{
            previousPositions.add(new PositionAndDirection(new PointF(x, y), currentDirection));
        }
        int requiredPositions = (tailSegments.size() + 1) ;
        if (previousPositions.size() > requiredPositions) {
            previousPositions.remove(0); // Keep the list size manageable
        }
    }

    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Color.GREEN);
        // Draw the tail segments
        for (Tail tailSegment : tailSegments) {
            tailSegment.draw(canvas, paint);
        }
        // Draw the snake head
        canvas.drawCircle(x, y, radius, paint);
    }

    public float calculateDistance(PointF point1, PointF point2) {
        float dx = point2.x - point1.x;
        float dy = point2.y - point1.y;
        return (float) Math.sqrt(dx * dx + dy * dy);
    }

    public boolean checkHeadCollidesWithTail() {
        RectF headRect = new RectF(x - radius, y - radius, x + radius, y + radius);

        // Start the loop from index 1 to skip the first tail segment
        for (int i = 0; i < tailSegments.size(); i++) {
            Tail tailSegment = tailSegments.get(i);
            RectF tailRect = new RectF(tailSegment.getLeft(), tailSegment.getTop(), tailSegment.getRight(), tailSegment.getBottom());
            if (RectF.intersects(headRect, tailRect)) {
                if(i == tailSegments.size()-1 || i == tailSegments.size()-2){
                    return false;
                }
                return true;
            }
        }
        return false;
    }


    public void setDirection(SnakeView.Direction direction) {
        currentDirection = direction;
    }

    public boolean checkCollision(Collidable collidable) {
        if (collidable == null) {
            return false;
        }

        RectF collRect = collidable.getBounds();
        if (collRect == null) {
            return false;
        }

        RectF headRect = new RectF(x - radius, y - radius, x + radius, y + radius);

        return RectF.intersects(headRect, collRect);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getRadius() {
        return radius;
    }

    // Add a new tail segment
    public void addTailSegment() {

        printTailSegments();
        hasTail = true;
        Tail lastSegment;
        if (tailSegments.isEmpty()) {
            lastSegment = new Tail(this.x, this.y, this.radius, this.speed, this.currentDirection);
        } else {
            lastSegment = tailSegments.get(tailSegments.size() - 1);
        }

        // Use the position and direction of the last segment to create a new segment
        Tail newSegment = new Tail(lastSegment.getLeft(), lastSegment.getTop(), this.radius, this.speed, lastSegment.getDirection());
        tailSegments.add(newSegment);
    }



    // Update the positions and directions of the tail segments
    public void updateTailPositions() {
        if(tailSegments.isEmpty()){
            return;
        }
        for (int i = tailSegments.size() - 1; i >= 0; i--) {
            PositionAndDirection newPositionAndDirection = previousPositions.get(i);
            Tail tailSegment = tailSegments.get(i);
            tailSegment.setPosition(newPositionAndDirection.position.x, newPositionAndDirection.position.y);
            tailSegment.setDirection(newPositionAndDirection.direction);
        }
    }

    // Helper class to track position and direction
    private class PositionAndDirection {
        PointF position;
        SnakeView.Direction direction;

        PositionAndDirection(PointF position, SnakeView.Direction direction) {
            this.position = position;
            this.direction = direction;
        }
    }

}
