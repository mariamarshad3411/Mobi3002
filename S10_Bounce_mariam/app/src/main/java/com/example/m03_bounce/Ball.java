package com.example.m03_bounce;

import android.graphics.Color;
import java.util.Random;

public class Ball {
    float x, y;
    float dx, dy;
    int radius = 20; // default radius
    int color;

    public Ball(float x, float y, float dx, float dy) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;

        // Random color
        Random rand = new Random();
        color = Color.rgb(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
    }
}
