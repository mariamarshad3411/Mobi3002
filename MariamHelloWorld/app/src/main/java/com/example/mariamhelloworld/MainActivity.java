package com.example.mariamhelloworld;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public void openCalculator(View view) {
        Intent intent = new Intent(this, CalculatorActivity.class);
        startActivity(intent);
    }

    private static final String TAG = "STACK OVERFLOW LOG";

    public static final String EXTRA_MESSAGE = "com.example.mariamhelloworld.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = findViewById(R.id.textView);
        tv.setText("Hi from Mariam !");

        Log.i(TAG, "onCreate: greeting set to 'Hi from Mariam !'");
    }

    public void sendMessage(View view) {
        EditText editText = findViewById(R.id.edit_message);
        String input = editText.getText().toString();

        String fullMessage = input + " — Sent by Mariam";

        Log.i(TAG, "sendMessage: fullMessage = " + fullMessage);

        Intent intent = new Intent(this, DisplayMessageActivity.class);
        intent.putExtra(EXTRA_MESSAGE, fullMessage);
        startActivity(intent);
    }
}
