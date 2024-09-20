package com.example.secondlab

import android.os.Bundle
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

        val toast = Toast.makeText(this, "", Toast.LENGTH_SHORT)

        val editText = findViewById<EditText>(R.id.editText)

        editText.addTextChangedListener {
            val editTextValue = editText.text.toString()

            if (editTextValue == "") {
                return@addTextChangedListener;
            }

            val numberValue = editTextValue.toIntOrNull()
            if (numberValue != null) {
                if (numberValue <= 0) {
                    toastHandler(toast, "Это не натуральное число");
                    return@addTextChangedListener;
                }

                var facrotial: Int = 1
                for (i in 2..numberValue) {
                    facrotial *= i;
                }

                return@addTextChangedListener;
            }
        }
    }

    private fun toastHandler(toast: Toast, text: String) {
        toast.setText(text)
        toast.show()
    }
}