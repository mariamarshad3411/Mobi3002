package com.example.m03_bounce;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

public class Ball {

    private static final float DEFAULT_RADIUS = 50f;
    private static final float DEFAULT_SPEED = 5f;
    private static final String TAG = "Ball";

    public float x, y;        // Position
    public float speedX, speedY;  // Speed components
    public int color;         // Ball color
    public float radius;      // Ball size
    private Paint paint;


    public Ball(float x, float y, float dx, float dy) {
        this.x = x;
        this.y = y;
        this.speedX = dx;
        this.speedY = dy;
        this.color = Color.RED;
        this.radius = DEFAULT_RADIUS;
        paint = new Paint();
        paint.setColor(color);
    }


    public Ball(int color) {
        this.color = color;
        this.x = 200;
        this.y = 200;
        this.speedX = DEFAULT_SPEED;
        this.speedY = DEFAULT_SPEED;
        this.radius = DEFAULT_RADIUS;
        paint = new Paint();
        paint.setColor(color);
    }


    public Ball(int color, float x, float y, float dx, float dy) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.speedX = dx;
        this.speedY = dy;
        this.radius = DEFAULT_RADIUS;
        paint = new Paint();
        paint.setColor(color);
    }


    public void draw(Canvas canvas) {
        canvas.drawCircle(x, y, radius, paint);
    }


    public void moveWithCollisionDetection(Box box) {
        x += speedX;
        y += speedY;


        if (x + radius > box.xMax) {
            speedX = -Math.abs(speedX);
            x = box.xMax - radius;
        } else if (x - radius < box.xMin) {
            speedX = Math.abs(speedX);
            x = box.xMin + radius;
        }


        if (y + radius > box.yMax) {
            speedY = -Math.abs(speedY);
            y = box.yMax - radius;
        } else if (y - radius < box.yMin) {
            speedY = Math.abs(speedY);
            y = box.yMin + radius;
        }

        Log.d(TAG, "Ball moved => x=" + x + ", y=" + y + ", dx=" + speedX + ", dy=" + speedY);
    }


    public float getModelX() { return x; }
    public float getModelY() { return y; }
}
