package com.example.mariamhelloworldfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);


        setTitle(getString(R.string.display_message_activity));

        try {
            Thread.sleep(5000); // 5 seconds delay for skipped frames demo
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ConstraintLayout layout = findViewById(R.id.activity_display_message);
        TextView textView = findViewById(R.id.textView);

        String message = getIntent().getStringExtra(MainActivity.EXTRA_MESSAGE);
        textView.setText("You typed: " + message);

        if (message != null) {
            switch (message.toLowerCase()) {
                case "red":
                    layout.setBackgroundColor(Color.RED);
                    break;
                case "blue":
                    layout.setBackgroundColor(Color.BLUE);
                    break;
                case "green":
                    layout.setBackgroundColor(Color.GREEN);
                    break;
                case "yellow":
                    layout.setBackgroundColor(Color.YELLOW);
                    break;
                case "purple":
                    layout.setBackgroundColor(Color.parseColor("#800080"));
                    break;
                default:
                    layout.setBackgroundColor(Color.WHITE);
            }
        }
    }
}

