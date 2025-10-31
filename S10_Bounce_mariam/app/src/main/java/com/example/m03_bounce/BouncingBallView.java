package com.example.m03_bounce;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * BouncingBallView with Logcat instrumentation.
 */
public class BouncingBallView extends View {

    private static final String TAG = "BounceApp";
    private ArrayList<Ball> balls = new ArrayList<>();
    private Ball ball_1;
    private Box box;
    DBClass DBtest;
    private float previousX;
    private float previousY;
    Random rand = new Random();

    public BouncingBallView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.d(TAG, "BouncingBallView constructor");

        box = new Box(Color.BLACK);


        balls.add(new Ball(Color.GREEN));
        ball_1 = balls.get(0);


        balls.add(new Ball(Color.CYAN));
        Log.d(TAG, "Initial balls added: count = " + balls.size());


        try {
            DBtest = new DBClass(context);
            List<DataModel> ALL = DBtest.findAll();
            Log.d(TAG, "DB findAll returned " + (ALL == null ? 0 : ALL.size()) + " rows");
            if (ALL != null) {
                for (DataModel one : ALL) {
                    Log.d(TAG, "Loading DataModel from DB: " + one.toString());
                    balls.add(new Ball(Color.YELLOW, one.getModelX(),
                            one.getModelY(), one.getModelDX(), one.getModelDY()));
                }
            }
            Log.d(TAG, "Total balls after DB load: " + balls.size());
        } catch (Exception e) {
            Log.d(TAG, "BouncingBallView: DB load failed or DB not present", e);
        }

        // focus & touch
        this.setFocusable(true);
        this.setFocusableInTouchMode(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        box.draw(canvas);
        for (Ball b : balls) {
            b.draw(canvas);
            b.moveWithCollisionDetection(box);
        }

        postInvalidateOnAnimation();
    }

    @Override
    public void onSizeChanged(int w, int h, int oldW, int oldH) {
        box.set(0, 0, w, h);
        Log.d(TAG, "onSizeChanged: w=" + w + " h=" + h);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float currentX = event.getX();
        float currentY = event.getY();
        float deltaX, deltaY;
        float scalingFactor = 5.0f / ((box.xMax > box.yMax) ? box.yMax : box.xMax);
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                deltaX = currentX - previousX;
                deltaY = currentY - previousY;
                ball_1.speedX += deltaX * scalingFactor;
                ball_1.speedY += deltaY * scalingFactor;


                Ball b = new Ball(Color.BLUE, previousX, previousY, deltaX, deltaY);
                balls.add(b);
                Log.d(TAG, "onTouchEvent: added ball at " + previousX + "," + previousY
                        + " speed=" + deltaX + "," + deltaY + " totalBalls=" + balls.size());

                if (balls.size() > 100) {
                    Log.d(TAG, "Too many balls, clearing and resetting");
                    balls.clear();
                    balls.add(new Ball(Color.RED));
                    ball_1 = balls.get(0);
                }
                break;
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "Touch down at: " + currentX + "," + currentY);
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "Touch up at: " + currentX + "," + currentY);
                break;
        }

        previousX = currentX;
        previousY = currentY;
        return true;
    }


    public void RussButtonPressed() {
        Log.d(TAG, "RussButtonPressed: adding random ball");
        int viewWidth = this.getMeasuredWidth();
        int viewHeight = this.getMeasuredHeight();
        int x = Math.max(10, rand.nextInt(Math.max(10, viewWidth)));
        int y = Math.max(10, rand.nextInt(Math.max(10, viewHeight)));
        int dx = rand.nextInt(50) - 25;
        int dy = rand.nextInt(50) - 25;
        balls.add(new Ball(Color.RED, x, y, dx, dy));
        Log.d(TAG, "RussButtonPressed: added ball at " + x + "," + y + " dx=" + dx + " dy=" + dy);
    }


    public void addBall(Ball b) {
        if (b == null) return;
        balls.add(b);
        Log.d(TAG, "addBall(): added ball => x=" + b.x + " y=" + b.y + " dx=" + b.speedX + " dy=" + b.speedY
                + " totalBalls=" + balls.size());
    }

    // called by MainActivity clear button
    public void clearBalls() {
        balls.clear();
        balls.add(new Ball(Color.RED));
        ball_1 = balls.get(0);
        Log.d(TAG, "clearBalls(): cleared and reset to 1 ball");
    }
}
