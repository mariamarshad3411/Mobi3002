package com.example.mariamhelloworldfinal;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.mariamhelloworldfinal.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText = findViewById(R.id.editText_message);
        Button button = findViewById(R.id.button_send);

        button.setOnClickListener(view -> {
            String message = editText.getText().toString().trim();
            if (!message.isEmpty()) {
                Intent intent = new Intent(MainActivity.this, DisplayMessageActivity.class);
                intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);
            } else {
                editText.setError("Please enter a message");
            }
        });
    }
}
