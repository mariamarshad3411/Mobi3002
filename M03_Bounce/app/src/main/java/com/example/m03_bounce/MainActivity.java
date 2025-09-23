package com.example.m03_bounce;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private BouncingBallView bouncingBallView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        bouncingBallView = new BouncingBallView(this, null);
        setContentView(bouncingBallView);
    }
}
