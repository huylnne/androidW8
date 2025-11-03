package com.example.androidbtvn

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var tvExpression: TextView
    private lateinit var tvDisplay: TextView

    private var currentInput = ""
    private var operator = ""
    private var firstNumber = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvExpression = findViewById(R.id.tvExpression)
        tvDisplay = findViewById(R.id.tvDisplay)

        val buttons = listOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
            R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9
        )

        // Các nút số
        for (id in buttons) {
            findViewById<Button>(id).setOnClickListener {
                currentInput += (it as Button).text
                tvDisplay.text = currentInput
            }
        }

        findViewById<Button>(R.id.btnAdd).setOnClickListener { setOperator("+") }
        findViewById<Button>(R.id.btnSub).setOnClickListener { setOperator("-") }
        findViewById<Button>(R.id.btnMul).setOnClickListener { setOperator("x") }
        findViewById<Button>(R.id.btnDiv).setOnClickListener { setOperator("/") }

        findViewById<Button>(R.id.btnEqual).setOnClickListener { calculate() }

        findViewById<Button>(R.id.btnC).setOnClickListener {
            currentInput = ""
            firstNumber = 0
            operator = ""
            tvExpression.text = ""
            tvDisplay.text = "0"
        }

        findViewById<Button>(R.id.btnCE).setOnClickListener {
            currentInput = ""
            tvDisplay.text = "0"
        }

        findViewById<Button>(R.id.btnBS).setOnClickListener {
            if (currentInput.isNotEmpty()) {
                currentInput = currentInput.dropLast(1)
                tvDisplay.text = if (currentInput.isEmpty()) "0" else currentInput
            }
        }
    }

    private fun setOperator(op: String) {
        if (currentInput.isNotEmpty()) {
            firstNumber = currentInput.toInt()
            operator = op
            tvExpression.text = "$firstNumber $operator"
            currentInput = ""
        }
    }

    private fun calculate() {
        if (currentInput.isNotEmpty()) {
            val secondNumber = currentInput.toInt()
            val result = when (operator) {
                "+" -> firstNumber + secondNumber
                "-" -> firstNumber - secondNumber
                "x" -> firstNumber * secondNumber
                "/" -> if (secondNumber != 0) firstNumber / secondNumber else 0
                else -> 0
            }

            // Hiển thị phép tính + kết quả như Windows Calculator
            tvExpression.text = "$firstNumber $operator $secondNumber ="
            tvDisplay.text = result.toString()

            currentInput = result.toString()
        }
    }
}
