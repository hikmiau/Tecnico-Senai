package com.example.calculadora_senai;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

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

        // ===== OPERADORES (incluindo divisão) =====
        findViewById(R.id.btn_add).setOnClickListener(v -> onOperatorButtonClick("+"));
        findViewById(R.id.btn_subtract).setOnClickListener(v -> onOperatorButtonClick("-"));
        findViewById(R.id.btn_multiply).setOnClickListener(v -> onOperatorButtonClick("x"));
        findViewById(R.id.btn_divide).setOnClickListener(v -> onOperatorButtonClick("÷")); // NOVO

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
        findViewById(R.id.btn_m_v).setOnClickListener(v -> onMemoryView());
    }
}