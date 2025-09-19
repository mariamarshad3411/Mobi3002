package com.example.mycalculatorfinal;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText_Num1, editText_Num2, editText_Answer;
    Button button_Add, button_Subtract, button_Multiply, button_Divide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText_Num1 = findViewById(R.id.editText_Num1);
        editText_Num2 = findViewById(R.id.editText_Num2);
        editText_Answer = findViewById(R.id.editText_Answer);

        button_Add = findViewById(R.id.button_Add);
        button_Subtract = findViewById(R.id.button_Subtract);
        button_Multiply = findViewById(R.id.button_Multiply);
        button_Divide = findViewById(R.id.button_Divide);

        button_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { calculate('+'); }
        });

        button_Subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { calculate('-'); }
        });

        button_Multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { calculate('*'); }
        });

        button_Divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { calculate('/'); }
        });
    }

    private void calculate(char operator) {
        String num1Str = editText_Num1.getText().toString();
        String num2Str = editText_Num2.getText().toString();

        if(num1Str.isEmpty() || num2Str.isEmpty()) {
            Toast.makeText(this, getString(R.string.error_empty), Toast.LENGTH_SHORT).show();
            return;
        }

        double num1 = Double.parseDouble(num1Str);
        double num2 = Double.parseDouble(num2Str);
        double result = 0;

        switch(operator) {
            case '+': result = num1 + num2; break;
            case '-': result = num1 - num2; break;
            case '*': result = num1 * num2; break;
            case '/':
                if(num2 == 0) {
                    Toast.makeText(this, getString(R.string.error_divzero), Toast.LENGTH_SHORT).show();
                    return;
                }
                result = num1 / num2;
                break;
        }

        editText_Answer.setText(String.valueOf(result));
    }
}
