package com.example.calculadora_senai;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView display;
    private String currentInput = "";
    private String currentOperator = "";
    private double operand1 = Double.NaN;
    private double operand2;
    private boolean resetScreen = false;
    private double memory = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);

        int[] numberButtonIds = {
                R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4,
                R.id.btn_5, R.id.btn_6, R.id.btn_7, R.id.btn_8, R.id.btn_9
        };

        for (int id : numberButtonIds) {
            findViewById(id).setOnClickListener(v ->
                    onNumberButtonClick(((Button) v).getText().toString())
            );
        }

        findViewById(R.id.btn_decimal).setOnClickListener(v -> onDecimalButtonClick());

        findViewById(R.id.btn_add).setOnClickListener(v -> onOperatorButtonClick("+"));
        findViewById(R.id.btn_subtract).setOnClickListener(v -> onOperatorButtonClick("-"));
        findViewById(R.id.btn_multiply).setOnClickListener(v -> onOperatorButtonClick("x"));
        findViewById(R.id.btn_divide).setOnClickListener(v -> onOperatorButtonClick("÷"));

        findViewById(R.id.btn_equals).setOnClickListener(v -> onEqualsButtonClick());

        findViewById(R.id.btn_sign).setOnClickListener(v -> onSignButtonClick());
        findViewById(R.id.btn_percent).setOnClickListener(v -> onPercentButtonClick());
        findViewById(R.id.btn_c).setOnClickListener(v -> onClearButtonClick());
        findViewById(R.id.btn_ce).setOnClickListener(v -> onClearEntryButtonClick());
        findViewById(R.id.btn_backspace).setOnClickListener(v -> onBackspaceButtonClick());
        findViewById(R.id.btn_reciprocal).setOnClickListener(v -> onReciprocalButtonClick());
        findViewById(R.id.btn_square).setOnClickListener(v -> onSquareButtonClick());
        findViewById(R.id.btn_cube_root).setOnClickListener(v -> onCubeRootButtonClick());

        findViewById(R.id.btn_mc).setOnClickListener(v -> onMemoryClear());
        findViewById(R.id.btn_mr).setOnClickListener(v -> onMemoryRecall());
        findViewById(R.id.btn_m_plus).setOnClickListener(v -> onMemoryAdd());
        findViewById(R.id.btn_m_minus).setOnClickListener(v -> onMemorySubtract());
        findViewById(R.id.btn_ms).setOnClickListener(v -> onMemoryStore());
        findViewById(R.id.btn_m_v).setOnClickListener(v -> onMemoryView());
    }

    private void onNumberButtonClick(String number) {
        if (resetScreen) {
            currentInput = "";
            resetScreen = false;
        }

        if ("0".equals(currentInput)) {
            currentInput = number;
        } else {
            currentInput += number;
        }

        display.setText(currentInput);
    }

    private void onDecimalButtonClick() {
        if (resetScreen) {
            currentInput = "";
            resetScreen = false;
        }

        if (currentInput.isEmpty()) {
            currentInput = "0,";
        } else if (!currentInput.contains(",")) {
            currentInput += ",";
        }

        display.setText(currentInput);
    }

    private void onOperatorButtonClick(String operator) {
        if (currentInput.isEmpty() && !Double.isNaN(operand1)) {
            currentOperator = operator;
            return;
        }

        if (!Double.isNaN(operand1) && !currentOperator.isEmpty() && !currentInput.isEmpty()) {
            operand2 = parseValue(currentInput);
            double result = calculate(operand1, operand2, currentOperator);
            if (Double.isNaN(result) || Double.isInfinite(result)) {
                showError();
                return;
            }
            operand1 = result;
            display.setText(formatValue(result));
        } else if (!currentInput.isEmpty()) {
            operand1 = parseValue(currentInput);
        }

        currentOperator = operator;
        currentInput = "";
        resetScreen = false;
    }

    private void onEqualsButtonClick() {
        if (currentOperator.isEmpty() || currentInput.isEmpty() || Double.isNaN(operand1)) {
            return;
        }

        operand2 = parseValue(currentInput);
        double result = calculate(operand1, operand2, currentOperator);
        if (Double.isNaN(result) || Double.isInfinite(result)) {
            showError();
            return;
        }

        String formatted = formatValue(result);
        display.setText(formatted);
        currentInput = formatted;
        operand1 = result;
        currentOperator = "";
        resetScreen = true;
    }

    private void onSignButtonClick() {
        if (currentInput.isEmpty()) {
            currentInput = display.getText().toString();
        }

        if ("0".equals(currentInput) || "Erro".equals(currentInput)) {
            return;
        }

        if (currentInput.startsWith("-")) {
            currentInput = currentInput.substring(1);
        } else {
            currentInput = "-" + currentInput;
        }

        display.setText(currentInput);
    }

    private void onPercentButtonClick() {
        if (currentInput.isEmpty()) {
            return;
        }

        double value = parseValue(currentInput);

        if (!Double.isNaN(operand1) && !currentOperator.isEmpty()) {
            value = operand1 * value / 100.0;
        } else {
            value = value / 100.0;
        }

        currentInput = formatValue(value);
        display.setText(currentInput);
    }

    private void onClearButtonClick() {
        currentInput = "";
        currentOperator = "";
        operand1 = Double.NaN;
        operand2 = 0.0;
        resetScreen = false;
        display.setText("0");
    }

    private void onClearEntryButtonClick() {
        currentInput = "";
        display.setText("0");
    }

    private void onBackspaceButtonClick() {
        if (currentInput.isEmpty()) {
            return;
        }

        currentInput = currentInput.substring(0, currentInput.length() - 1);
        if (currentInput.isEmpty() || "-".equals(currentInput)) {
            currentInput = "";
            display.setText("0");
            return;
        }

        display.setText(currentInput);
    }

    private void onReciprocalButtonClick() {
        double value = getActiveValue();
        if (value == 0.0) {
            showError();
            return;
        }

        double result = 1.0 / value;
        currentInput = formatValue(result);
        display.setText(currentInput);
        resetScreen = true;
    }

    private void onSquareButtonClick() {
        double value = getActiveValue();
        double result = value * value;

        currentInput = formatValue(result);
        display.setText(currentInput);
        resetScreen = true;
    }

    private void onCubeRootButtonClick() {
        double value = getActiveValue();
        double result = Math.cbrt(value);

        currentInput = formatValue(result);
        display.setText(currentInput);
        resetScreen = true;
    }

    private void onMemoryClear() {
        memory = 0.0;
    }

    private void onMemoryRecall() {
        currentInput = formatValue(memory);
        display.setText(currentInput);
        resetScreen = true;
    }

    private void onMemoryAdd() {
        memory += getActiveValue();
    }

    private void onMemorySubtract() {
        memory -= getActiveValue();
    }

    private void onMemoryStore() {
        memory = getActiveValue();
    }

    private void onMemoryView() {
        currentInput = formatValue(memory);
        display.setText(currentInput);
    }

    private double calculate(double first, double second, String operator) {
        switch (operator) {
            case "+":
                return first + second;
            case "-":
                return first - second;
            case "x":
                return first * second;
            case "÷":
                if (second == 0.0) {
                    return Double.NaN;
                }
                return first / second;
            default:
                return Double.NaN;
        }
    }

    private double parseValue(String value) {
        try {
            return Double.parseDouble(value.replace(',', '.'));
        } catch (NumberFormatException exception) {
            return 0.0;
        }
    }

    private double getActiveValue() {
        String source = currentInput.isEmpty() ? display.getText().toString() : currentInput;
        return parseValue(source);
    }

    private String formatValue(double value) {
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            return "Erro";
        }

        if (Math.abs(value) < 1e-12) {
            value = 0.0;
        }

        if (value == Math.rint(value)) {
            return String.valueOf((long) value);
        }

        String text = String.format(Locale.US, "%.12f", value);
        text = text.replaceAll("0+$", "").replaceAll("\\.$", "");
        return text.replace('.', ',');
    }

    private void showError() {
        display.setText("Erro");
        currentInput = "";
        currentOperator = "";
        operand1 = Double.NaN;
        operand2 = 0.0;
        resetScreen = true;
    }
}
