package com.example.secondlab

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener

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

        val button = findViewById<Button>(R.id.btn)
        button.setOnClickListener {
            this.findNumbers()
        }
    }

    private  fun findNumbers() {
        val toast = Toast.makeText(this, "", Toast.LENGTH_SHORT)

        val editText = findViewById<EditText>(R.id.editText)
        val editTextValue = editText.text.toString()

        val numberValue = editTextValue.toIntOrNull()
        if (numberValue != null) {
            if (numberValue <= 0) {
                toastHandler(toast, "Это не натуральное число");
                return;
            }

            val factorial: Int = calcFactorial(numberValue)

            for (i in 1..numberValue) {
                val calculated = i * (i + 1) * (i + 2);

                if (calculated == factorial) {
                    toastHandler(toast, "Удалось найти числа: ${i}, ${i + 1}, ${i + 2}");
                    return;
                }
            }

            toastHandler(toast, "Не удалось найти таких чисел");
        }
    }

    private fun calcFactorial(number: Int): Int {
        var acc: Int = 1;
        for (i in 2..number) {
            acc *= i;
        }

        return acc;
    }

    private fun toastHandler(toast: Toast, text: String) {
        toast.setText(text)
        toast.show()
    }
}