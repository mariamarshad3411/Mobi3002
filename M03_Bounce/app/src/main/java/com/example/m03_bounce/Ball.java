package com.example.m03_bounce;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

public class Ball {
    public float x, y;
    public float vx, vy;
    public float radius;
    private Paint paint;

    public Ball(float x, float y, float vx, float vy, float radius, int color) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.radius = radius;

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(color);
    }

    public void moveWithCollisionDetection(RectF box) {
        x += vx;
        y += vy;

        // bounce left/right
        if (x - radius < box.left) {
            x = box.left + radius;
            vx = -vx;
        } else if (x + radius > box.right) {
            x = box.right - radius;
            vx = -vx;
        }

        // bounce top/bottom
        if (y - radius < box.top) {
            y = box.top + radius;
            vy = -vy;
        } else if (y + radius > box.bottom) {
            y = box.bottom - radius;
            vy = -vy;
        }
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle(x, y, radius, paint);
    }
}
