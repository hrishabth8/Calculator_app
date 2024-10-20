package com.example.simplecal;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView display;
    private String currentNumber = "";
    private String operator = "";
    private double firstOperand = Double.NaN;
    private double secondOperand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);
    }

    public void onDigitClick(View view) {
        Button button = (Button) view;
        currentNumber += button.getText().toString();
        display.setText(currentNumber);
    }

    public void onOperatorClick(View view) {
        if (!Double.isNaN(firstOperand)) {
            onEqualClick(null); // If there's already a first operand, calculate the previous operation.
        }
        Button button = (Button) view;
        operator = button.getText().toString();
        firstOperand = Double.parseDouble(currentNumber);
        currentNumber = "";
        display.setText(operator);
    }

    public void onEqualClick(View view) {
        if (operator.isEmpty()) {
            return;
        }
        secondOperand = Double.parseDouble(currentNumber);
        double result = 0;

        switch (operator) {
            case "+":
                result = firstOperand + secondOperand;
                break;
            case "-":
                result = firstOperand - secondOperand;
                break;
            case "*":
                result = firstOperand * secondOperand;
                break;
            case "/":
                if (secondOperand != 0) {
                    result = firstOperand / secondOperand;
                } else {
                    display.setText("Error"); // Division by zero.
                    return;
                }
                break;
        }

        display.setText(String.valueOf(result));
        firstOperand = result;
        currentNumber = "";
        operator = "";
    }

    public void onClearClick(View view) {
        currentNumber = "";
        firstOperand = Double.NaN;
        secondOperand = Double.NaN;
        operator = "";
        display.setText("0");
    }
}
