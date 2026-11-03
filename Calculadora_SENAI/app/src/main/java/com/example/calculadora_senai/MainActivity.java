package com.example.calculadora_senai;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.orion_calculator_senai_v2.R;

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

        // ===== BOTÕES NUMÉRICOS =====
        int[] numberButtonIds = {
                R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4,
                R.id.btn_5, R.id.btn_6, R.id.btn_7, R.id.btn_8, R.id.btn_9
        };
        for (int id : numberButtonIds) {
            findViewById(id).setOnClickListener(v ->
                    onNumberButtonClick(((Button) v).getText().toString())
            );
        }

        // ===== VÍRGULA =====
        findViewById(R.id.btn_decimal).setOnClickListener(v -> onDecimalButtonClick());

        // ===== OPERADORES =====
        findViewById(R.id.btn_add).setOnClickListener(v -> onOperatorButtonClick("+"));
        findViewById(R.id.btn_subtract).setOnClickListener(v -> onOperatorButtonClick("-"));
        findViewById(R.id.btn_multiply).setOnClickListener(v -> onOperatorButtonClick("x"));
        findViewById(R.id.btn_divide).setOnClickListener(v -> onOperatorButtonClick("÷"));

        // ===== IGUAL =====
        findViewById(R.id.btn_equals).setOnClickListener(v -> onEqualsButtonClick());

        // ===== FUNÇÕES ESPECIAIS =====
        findViewById(R.id.btn_sign).setOnClickListener(v -> onSignButtonClick());
        findViewById(R.id.btn_percent).setOnClickListener(v -> onPercentButtonClick());
        findViewById(R.id.btn_c).setOnClickListener(v -> onClearButtonClick());
        findViewById(R.id.btn_ce).setOnClickListener(v -> onClearEntryButtonClick());
        findViewById(R.id.btn_backspace).setOnClickListener(v -> onBackspaceButtonClick());
        findViewById(R.id.btn_reciprocal).setOnClickListener(v -> onReciprocalButtonClick());
        findViewById(R.id.btn_square).setOnClickListener(v -> onSquareButtonClick());
        findViewById(R.id.btn_cube_root).setOnClickListener(v -> onCubeRootButtonClick());

        // ===== MEMÓRIA =====
        findViewById(R.id.btn_mc).setOnClickListener(v -> onMemoryClear());
        findViewById(R.id.btn_mr).setOnClickListener(v -> onMemoryRecall());
        findViewById(R.id.btn_m_plus).setOnClickListener(v -> onMemoryAdd());
        findViewById(R.id.btn_m_minus).setOnClickListener(v -> onMemorySubtract());
        findViewById(R.id.btn_ms).setOnClickListener(v -> onMemoryStore());
        findViewById(R.id.btn_mv).setOnClickListener(v -> onMemoryView());
    }

    // ==================== NÚMEROS ====================
    private void onNumberButtonClick(String digit) {
        if (resetScreen) {
            currentInput = "";
            resetScreen = false;
        }

        if (currentInput.equals("0") && digit.equals("0")) {
            return;
        }

        if (currentInput.equals("0") && !digit.equals("0")) {
            currentInput = digit;
        } else {
            currentInput += digit;
        }

        updateDisplay();
    }

    // ===== VÍRGULA =====
    private void onDecimalButtonClick() {
        if (resetScreen) {
            currentInput = "0.";
            resetScreen = false;
            updateDisplay();
            return;
        }

        if (currentInput.contains(".")) {
            return;
        }

        if (currentInput.isEmpty()) {
            currentInput = "0.";
        } else {
            currentInput += ".";
        }

        updateDisplay();
    }

    // ==================== OPERADORES ====================
    private void onOperatorButtonClick(String operator) {
        if (!Double.isNaN(operand1)) {
            onEqualsButtonClick();
        }

        try {
            operand1 = Double.parseDouble(currentInput);
        } catch (NumberFormatException e) {
            operand1 = 0.0;
        }

        currentOperator = operator;
        resetScreen = true;
    }

    private void onEqualsButtonClick() {
        if (Double.isNaN(operand1) || currentOperator.isEmpty()) {
            return;
        }

        try {
            operand2 = Double.parseDouble(currentInput);
        } catch (NumberFormatException e) {
            operand2 = 0.0;
        }

        double result = 0.0;

        switch (currentOperator) {
            case "+":
                result = operand1 + operand2;
                break;
            case "-":
                result = operand1 - operand2;
                break;
            case "x":
                result = operand1 * operand2;
                break;
            case "÷":
                if (operand2 == 0) {
                    display.setText("Error");
                    resetCalculator();
                    return;
                }
                result = operand1 / operand2;
                break;
        }

        currentInput = formatResult(result);
        operand1 = result;
        resetScreen = true;
        currentOperator = "";
        updateDisplay();
    }

    private String formatResult(double result) {
        if (result == (long) result) {
            return String.valueOf((long) result);
        } else {
            return String.format("%.10f", result)
                    .replaceAll("0*$", "")
                    .replaceAll("\\.$", "");
        }
    }

    // ==================== FUNÇÕES ESPECIAIS ====================
    private void onSignButtonClick() {
        if (currentInput.isEmpty() || currentInput.equals("0")) return;
        try {
            double value = Double.parseDouble(currentInput);
            value = -value;
            currentInput = formatResult(value);
            updateDisplay();
        } catch (NumberFormatException ignored) {}
    }

    private void onPercentButtonClick() {
        if (currentInput.isEmpty()) return;
        try {
            double value = Double.parseDouble(currentInput);
            value = value / 100;
            currentInput = formatResult(value);
            updateDisplay();
        } catch (NumberFormatException ignored) {}
    }

    private void onClearButtonClick() {
        resetCalculator();
        updateDisplay();
    }

    private void onClearEntryButtonClick() {
        currentInput = "0";
        updateDisplay();
    }

    private void onBackspaceButtonClick() {
        if (currentInput.length() > 0) {
            currentInput = currentInput.substring(0, currentInput.length() - 1);
        }
        if (currentInput.isEmpty() || currentInput.equals("-")) {
            currentInput = "0";
        }
        updateDisplay();
    }

    private void onReciprocalButtonClick() {
        if (currentInput.isEmpty() || currentInput.equals("0")) return;
        try {
            double value = Double.parseDouble(currentInput);
            if (value != 0) {
                value = 1 / value;
                currentInput = formatResult(value);
                updateDisplay();
                resetScreen = true;
            } else {
                display.setText("ERRO");
                resetCalculator();
            }
        } catch (NumberFormatException ignored) {}
    }

    private void onSquareButtonClick() {
        if (currentInput.isEmpty()) return;
        try {
            double value = Double.parseDouble(currentInput);
            value = value * value;
            currentInput = formatResult(value);
            updateDisplay();
            resetScreen = true;
        } catch (NumberFormatException ignored) {}
    }

    private void onCubeRootButtonClick() {
        if (currentInput.isEmpty()) return;
        try {
            double value = Double.parseDouble(currentInput);
            value = Math.cbrt(value);
            currentInput = formatResult(value);
            updateDisplay();
            resetScreen = true;
        } catch (NumberFormatException ignored) {}
    }

    // ==================== MEMÓRIA ====================
    private void onMemoryClear() {
        memory = 0.0;
        display.setText("MC");
    }

    private void onMemoryRecall() {
        currentInput = formatResult(memory);
        updateDisplay();
        resetScreen = true;
    }

    private void onMemoryAdd() {
        try {
            double value = Double.parseDouble(currentInput);
            memory += value;
            display.setText("M+");
        } catch (NumberFormatException e) { }
        resetScreen = true;
    }

    private void onMemorySubtract() {
        try {
            double value = Double.parseDouble(currentInput);
            memory -= value;
            display.setText("M-");
        } catch (NumberFormatException e) { }
        resetScreen = true;
    }

    private void onMemoryStore() {
        try {
            memory = Double.parseDouble(currentInput);
            display.setText("MS");
        } catch (NumberFormatException e) {
            memory = 0.0;
        }
        resetScreen = true;
    }

    private void onMemoryView() {
        display.setText("M: " + formatResult(memory));
    }

    // ==================== AUXILIARES ====================
    private void resetCalculator() {
        currentInput = "0";
        currentOperator = "";
        operand1 = Double.NaN;
        resetScreen = true;
    }

    private void updateDisplay() {
        display.setText(currentInput.isEmpty() ? "0" : currentInput);
    }
}