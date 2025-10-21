package com.example.m03_bounce;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class BouncingBallView extends View {

    private static final String TAG = "BouncingBallView";

    private class Ball {
        float x, y;
        float vx, vy;
        float radius;
        int color;

        Ball(float x, float y, float radius, int color) {
            this.x = x;
            this.y = y;
            this.radius = radius;
            this.color = color;
            this.vx = 0;
            this.vy = 0;
        }
    }

    private List<Ball> balls = new ArrayList<>();
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    // Gravity vector
    private float gx = 0f;
    private float gy = 9.8f;

    private long lastTime;

    public BouncingBallView(Context context) {
        super(context);
        init();
    }

    public BouncingBallView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BouncingBallView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        lastTime = SystemClock.elapsedRealtime();
        setBackgroundColor(Color.WHITE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        long now = SystemClock.elapsedRealtime();
        float dt = (now - lastTime) / 1000f;
        lastTime = now;

        
        for (Ball ball : balls) {
            ball.vx += gx * dt;
            ball.vy += gy * dt;

            ball.x += ball.vx * dt * 100;
            ball.y += ball.vy * dt * 100;


            if (ball.x - ball.radius < 0) {
                ball.x = ball.radius;
                ball.vx *= -0.7f;
            }
            if (ball.x + ball.radius > getWidth()) {
                ball.x = getWidth() - ball.radius;
                ball.vx *= -0.7f;
            }
            if (ball.y - ball.radius < 0) {
                ball.y = ball.radius;
                ball.vy *= -0.7f;
            }
            if (ball.y + ball.radius > getHeight()) {
                ball.y = getHeight() - ball.radius;
                ball.vy *= -0.7f;
            }

            paint.setColor(ball.color);
            canvas.drawCircle(ball.x, ball.y, ball.radius, paint);
        }

        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                Ball ball = new Ball(event.getX(), event.getY(), 50, Color.RED);
                balls.add(ball);
                Log.d(TAG, "New ball at: " + event.getX() + ", " + event.getY());
                break;
            case MotionEvent.ACTION_UP:

                if (!balls.isEmpty()) {
                    Ball last = balls.get(balls.size() - 1);
                    last.vx = (float) (Math.random() * 500 - 250);
                    last.vy = (float) (Math.random() * 500 - 250);
                    Log.d(TAG, "Ball fling vx=" + last.vx + " vy=" + last.vy);
                }
                break;
        }
        return true;
    }


    public void setGravityVector(float gx, float gy) {
        this.gx = gx;
        this.gy = gy;
        Log.d(TAG, "Gravity set to gx=" + gx + " gy=" + gy);
    }


    public void RussButtonPressed() {
        Log.d(TAG, "Russ button pressed!");
        if (!balls.isEmpty()) {
            balls.clear();
            Log.d(TAG, "All balls cleared!");
        }
    }
}
