package com.example.m03_bounce;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

/**
 * MainActivity for Bounce app - with Logcat instrumentation.
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "BounceApp";
    private BouncingBallView bbView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "MainActivity onCreate - app started");

        // find the view
        bbView = findViewById(R.id.bouncingBallView);

        // Inputs
        EditText editX = findViewById(R.id.editX);
        EditText editY = findViewById(R.id.editY);
        EditText editDX = findViewById(R.id.editDX);
        EditText editDY = findViewById(R.id.editDY);

        Button addBallButton = findViewById(R.id.addBallButton);
        Button clearButton = findViewById(R.id.clearButton);

        addBallButton.setOnClickListener(v -> {
            try {
                float x = Float.parseFloat(editX.getText().toString());
                float y = Float.parseFloat(editY.getText().toString());
                float dx = Float.parseFloat(editDX.getText().toString());
                float dy = Float.parseFloat(editDY.getText().toString());

                Log.d(TAG, "Add Ball pressed - values: X=" + x + " Y=" + y + " DX=" + dx + " DY=" + dy);


                int color = 0xFF0000FF; // blue as example
                Ball newBall = new Ball(color, x, y, dx, dy);
                bbView.addBall(newBall);


                Log.d(TAG, "Ball added to BouncingBallView");

            } catch (NumberFormatException e) {
                Log.d(TAG, "Add Ball: invalid number format in inputs", e);
            } catch (Exception e) {
                Log.d(TAG, "Add Ball: unexpected error", e);
            }
        });

        clearButton.setOnClickListener(v -> {
            Log.d(TAG, "Clear button pressed - clearing balls");
            bbView.clearBalls();
            Log.d(TAG, "Clear complete - balls cleared from view");
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "MainActivity onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "MainActivity onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "MainActivity onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "MainActivity onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "MainActivity onDestroy");
    }
}
