package com.cs407.calculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firstNumber = findViewById<EditText>(R.id.firstNumber)
        val secondNumber = findViewById<EditText>(R.id.secondNumber)
        val buttonAdd = findViewById<Button>(R.id.buttonAdd)
        val buttonSubtract = findViewById<Button>(R.id.buttonSubtract)
        val buttonMultiply = findViewById<Button>(R.id.buttonMultiply)
        val buttonDivide = findViewById<Button>(R.id.buttonDivide)

        buttonAdd.setOnClickListener { performOperation(firstNumber, secondNumber, "+") }
        buttonSubtract.setOnClickListener { performOperation(firstNumber, secondNumber, "-") }
        buttonMultiply.setOnClickListener { performOperation(firstNumber, secondNumber, "*") }
        buttonDivide.setOnClickListener { performOperation(firstNumber, secondNumber, "/") }
    }

    private fun performOperation(firstNumber: EditText, secondNumber: EditText, operation: String) {
        try {
            val num1 = firstNumber.text.toString().toInt()
            val num2 = secondNumber.text.toString().toInt()
            val result = when (operation) {
                "+" -> num1 + num2
                "-" -> num1 - num2
                "*" -> num1 * num2
                "/" -> if (num2 != 0) num1 / num2 else throw ArithmeticException("Divide by zero")
                else -> 0
            }
            val intent = Intent(this, ResultActivity::class.java).apply {
                putExtra("result", result.toString())
            }
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }
}