package com.example.m03_bounce;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private BouncingBallView bbView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bbView = findViewById(R.id.bouncingBallView);

        EditText editX = findViewById(R.id.editX);
        EditText editY = findViewById(R.id.editY);
        EditText editDX = findViewById(R.id.editDX);
        EditText editDY = findViewById(R.id.editDY);

        Button addBallButton = findViewById(R.id.addBallButton);
        Button clearButton = findViewById(R.id.clearButton);

        addBallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x = Integer.parseInt(editX.getText().toString());
                int y = Integer.parseInt(editY.getText().toString());
                int dx = Integer.parseInt(editDX.getText().toString());
                int dy = Integer.parseInt(editDY.getText().toString());

                bbView.addBall(x, y, dx, dy);
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bbView.clearBalls();
            }
        });
    }
}
