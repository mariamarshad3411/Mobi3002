package com.example.m03_bounce;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

public class BouncingBallView extends View {

    private ArrayList<Shape> shapes = new ArrayList<>();
    private Paint paint = new Paint();


    private float startX, startY;
    private long startTime;


    private float rectLeft = 300;
    private float rectTop = 300;
    private float rectRight = 600;
    private float rectBottom = 500;

    private int score = 0;

    public BouncingBallView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        canvas.drawColor(Color.parseColor("#A8E6CF"));                              ..


        paint.setColor(Color.parseColor("#FFD3B6")); // pastel
        canvas.drawRect(rectLeft, rectTop, rectRight, rectBottom, paint);


        for (Shape shape : shapes) {
            paint.setColor(shape.color);
            if (shape.isSquare) {
                canvas.drawRect(shape.x - shape.size, shape.y - shape.size,
                        shape.x + shape.size, shape.y + shape.size, paint);
            } else {
                canvas.drawCircle(shape.x, shape.y, shape.size, paint);
            }


            shape.x += shape.dx;
            shape.y += shape.dy;


            if ((shape.x - shape.size < 0) || (shape.x + shape.size > getWidth())) shape.dx *= -1;
            if ((shape.y - shape.size < 0) || (shape.y + shape.size > getHeight())) shape.dy *= -1;


            if (shape.x + shape.size > rectLeft &&
                    shape.x - shape.size < rectRight &&
                    shape.y + shape.size > rectTop &&
                    shape.y - shape.size < rectBottom) {

                score++;
                Log.d("BouncingBall", "Collision! Score: " + score);


                if (shape.dx > 0) shape.x = rectLeft - shape.size;
                else shape.x = rectRight + shape.size;

                if (shape.dy > 0) shape.y = rectTop - shape.size;
                else shape.y = rectBottom + shape.size;

                shape.dx *= -1;
                shape.dy *= -1;
            }
        }


        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                startY = event.getY();
                startTime = System.currentTimeMillis();
                return true;

            case MotionEvent.ACTION_UP:
                float endX = event.getX();
                float endY = event.getY();
                long endTime = System.currentTimeMillis();

                float distance = (float) Math.hypot(endX - startX, endY - startY);
                float duration = endTime - startTime;
                float speed = distance / duration;

                boolean isSquare = speed > 0.5;
                int color = getRandomPastelColor();
                int size = 60;

                Random rnd = new Random();
                float dx = rnd.nextBoolean() ? 3 : 12;
                float dy = rnd.nextBoolean() ? 3 : 12;

                shapes.add(new Shape(endX, endY, size, dx, dy, color, isSquare));

                Log.d("BouncingBall", "New shape at: " + endX + "," + endY +
                        " Color: " + color + " isSquare: " + isSquare);

                return true;
        }
        return super.onTouchEvent(event);
    }


    private int getRandomPastelColor() {
        Random rnd = new Random();
        int r = rnd.nextInt(100) + 150; // 150-249
        int g = rnd.nextInt(100) + 150;
        int b = rnd.nextInt(100) + 150;
        return Color.rgb(r, g, b);
    }


    private static class Shape {
        float x, y;
        int size;
        float dx, dy;
        int color;
        boolean isSquare;

        Shape(float x, float y, int size, float dx, float dy, int color, boolean isSquare) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.dx = dx;
            this.dy = dy;
            this.color = color;
            this.isSquare = isSquare;
        }
    }
}
