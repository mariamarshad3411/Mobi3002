package com.example.mariamhelloworld;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayMessageActivity extends AppCompatActivity {
    private static final String TAG = "STACK OVERFLOW LOG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        setTitle(getString(R.string.title_display_activity));

        String message = getIntent().getStringExtra(MainActivity.EXTRA_MESSAGE);
        if (message == null) message = "(no message)";

        TextView tv = findViewById(R.id.text_message);
        tv.setText(message);

        View root = findViewById(R.id.display_root);
        String lower = message.toLowerCase();

        int bg = Color.parseColor("#FFFFFF");

        if (lower.contains("red")) {
            bg = Color.parseColor("#FFCDD2");
        } else if (lower.contains("blue")) {
            bg = Color.parseColor("#BBDEFB");
        } else if (lower.contains("green")) {
            bg = Color.parseColor("#C8E6C9");
        } else if (lower.contains("yellow")) {
            bg = Color.parseColor("#FFF9C4");
        } else if (lower.contains("purple") || lower.contains("lilac")) {
            bg = Color.parseColor("#E1BEE7");
        }

        root.setBackgroundColor(bg);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Log.i(TAG, "Non-blocking delay finished — UI stayed smooth.");
        }, 5000);
    }
}
