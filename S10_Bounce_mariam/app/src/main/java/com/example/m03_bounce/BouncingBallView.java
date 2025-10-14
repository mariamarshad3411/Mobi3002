package com.example.m03_bounce;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BouncingBallView extends View {

    private List<Ball> balls = new ArrayList<>();
    private Paint paint = new Paint();
    private Random random = new Random();

    public BouncingBallView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint.setAntiAlias(true);
    }

    public void addBall(int x, int y, int dx, int dy) {
        int radius = 20;
        int color = Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));
        balls.add(new Ball(x, y, dx, dy, radius, color));
        invalidate();
    }

    public void clearBalls() {
        balls.clear();
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (Ball ball : balls) {
            paint.setColor(ball.color);
            canvas.drawCircle(ball.x, ball.y, ball.radius, paint);

            // Update ball position
            ball.x += ball.dx;
            ball.y += ball.dy;

            // Bounce off walls
            if (ball.x <= ball.radius || ball.x >= getWidth() - ball.radius) {
                ball.dx = -ball.dx;
            }
            if (ball.y <= ball.radius || ball.y >= getHeight() - ball.radius) {
                ball.dy = -ball.dy;
            }
        }

        // Redraw
        postInvalidateOnAnimation();
    }

    private static class Ball {
        int x, y, dx, dy, radius, color;

        Ball(int x, int y, int dx, int dy, int radius, int color) {
            this.x = x;
            this.y = y;
            this.dx = dx;
            this.dy = dy;
            this.radius = radius;
            this.color = color;
        }
    }
}
