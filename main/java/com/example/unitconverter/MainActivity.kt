package com.example.unitconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputValue = findViewById<EditText>(R.id.inputValue)
        val unitSpinner = findViewById<Spinner>(R.id.unitSpinner)
        val convertButton = findViewById<Button>(R.id.convertButton)
        val resultText = findViewById<TextView>(R.id.resultText)

        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.unit_options,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        unitSpinner.adapter = adapter

        convertButton.setOnClickListener {
            val value = inputValue.text.toString().toDoubleOrNull()
            if (value != null) {
                val result = when (unitSpinner.selectedItemPosition) {
                    0 -> convertTemperature(value)
                    1 -> convertLength(value)
                    2 -> convertWeight(value)
                    else -> 0.0
                }
                resultText.text = "Result: $result"
            } else {
                resultText.text = "Please enter a valid number"
            }
        }
    }

    private fun convertTemperature(celsius: Double): Double {
        return (celsius * 9/5) + 32
    }

    private fun convertLength(meters: Double): Double {
        return meters * 3.28084
    }

    private fun convertWeight(kilograms: Double): Double {
        return kilograms * 2.20462
    }
}
