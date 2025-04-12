package com.example.pratice

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private lateinit var display: TextView
    private var currentInput = ""
    private var result = 0.0
    private var lastOperation = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.linear_activity)

        display = findViewById(R.id.display)
        setUpNumberButton(R.id.btn0, "0")
        setUpNumberButton(R.id.btn1, "1")
        setUpNumberButton(R.id.btn2, "2")
        setUpNumberButton(R.id.btn3, "3")
        setUpNumberButton(R.id.btn4, "4")
        setUpNumberButton(R.id.btn5, "5")
        setUpNumberButton(R.id.btn6, "6")
        setUpNumberButton(R.id.btn7, "7")
        setUpNumberButton(R.id.btn8, "8")
        setUpNumberButton(R.id.btn9, "9")

        setUpOperatorButton(R.id.btnPlus, "+")
        setUpOperatorButton(R.id.btnMinus, "-")
        setUpOperatorButton(R.id.btnMultiply, "x")
        setUpOperatorButton(R.id.btnDivide, "/")


        setUpFunctionButton(R.id.btnC, "C")
        setUpFunctionButton(R.id.btnCE, "CE")
        setUpFunctionButton(R.id.btnEqual, "=")

    }

    private fun setUpNumberButton(buttonId: Int, value: String) {
        val button: Button = findViewById<Button>(buttonId)
        button.setOnClickListener {
            currentInput += value
            display.text = currentInput
        }
    }

    private fun setUpOperatorButton(buttonId: Int, operation: String) {
        val button: Button = findViewById(buttonId)
        button.setOnClickListener {
            if (currentInput.isNotEmpty()) {
                if (lastOperation.isNotEmpty()) {
                    calculateResult()
                }
                lastOperation = operation
                result = currentInput.toDouble()
                currentInput = ""
                display.text = result.toString()
            }
        }
    }

    private fun setUpFunctionButton(buttonId: Int, function: String) {
        val button: Button = findViewById(buttonId)
        button.setOnClickListener {
            when (function) {
                "C" -> {
                    currentInput = ""
                    result = 0.0
                    lastOperation = ""
                    display.text = "0"
                }
                "CE" -> {
                    if (currentInput.isNotEmpty()) {
                        currentInput = currentInput.substring(0, currentInput.length - 1)
                        display.text = if (currentInput.isEmpty()) "0" else currentInput
                    }
                }
                "=" -> {
                    if (currentInput.isNotEmpty() && lastOperation.isNotEmpty()) {
                        calculateResult()
                        display.text = result.toString()
                        currentInput = ""
                        lastOperation = ""
                    }
                }
            }
        }
    }

    private fun calculateResult() {
        val inputNumber = currentInput.toDouble()
        when (lastOperation) {
            "+" -> result += inputNumber
            "-" -> result -= inputNumber
            "x" -> result *= inputNumber
            "/" -> {
                if (inputNumber != 0.0) {
                    result /= inputNumber
                } else {
                    display.text = "Error"
                    return
                }
            }
        }
        currentInput = ""
    }

}