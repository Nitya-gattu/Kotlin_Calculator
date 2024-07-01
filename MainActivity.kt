package com.example.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initialize_views()
    }
    fun initialize_views(){
        val num1 = findViewById<EditText>(R.id.num1)
        val num2 = findViewById<EditText>(R.id.num2)
        val resultTextView = findViewById<TextView>(R.id.myTxt)
        val addButton = findViewById<Button>(R.id.addition)
        val subtractButton = findViewById<Button>(R.id.subraction)
        val multiplyButton = findViewById<Button>(R.id.mul)
        val divideButton = findViewById<Button>(R.id.div)
        val modButton = findViewById<Button>(R.id.mod)

        addButton.setOnClickListener { performOperation(num1, num2, resultTextView, "+") }
        subtractButton.setOnClickListener { performOperation(num1, num2, resultTextView, "-") }
        multiplyButton.setOnClickListener { performOperation(num1, num2, resultTextView, "*") }
        divideButton.setOnClickListener { performOperation(num1, num2, resultTextView, "/") }
        modButton.setOnClickListener { performOperation(num1, num2, resultTextView, "%") }
    }

    private fun performOperation(num1EditText: EditText, num2EditText: EditText, resultTextView: TextView, operation: String) {
        val num1Str = num1EditText.text.toString()
        val num2Str = num2EditText.text.toString()

        if (num1Str.isEmpty() || num2Str.isEmpty()) {
            resultTextView.text = "Please enter both numbers"
            return
        }

        val num1 = num1Str.toDoubleOrNull()
        val num2 = num2Str.toDoubleOrNull()

        if (num1 == null || num2 == null) {
            resultTextView.text = "Invalid input"
            return
        }

        val result = when (operation) {
            "+" -> num1 + num2
            "-" -> num1 - num2
            "*" -> num1 * num2
            "/" -> if (num2 != 0.0) num1 / num2 else {
                resultTextView.text = "Cannot divide by zero"
                return
            }
            "%" -> num1 % num2
            else -> 0.0
        }

        resultTextView.text = result.toString()
        }
    }
