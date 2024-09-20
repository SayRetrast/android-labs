package com.example.fitrstlab

import android.os.Bundle
import android.view.Gravity
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

        var toastText: String = "";
        val toast = Toast.makeText(this, toastText, Toast.LENGTH_SHORT)

        val editText = findViewById<EditText>(R.id.editText)

        editText.addTextChangedListener {
            val editTextValue = editText.text.toString()

            if (editTextValue == "") {
                return@addTextChangedListener;
            }

            val numberValue = editTextValue.toIntOrNull()
            if (numberValue != null) {
                if (numberValue in 0..9) {
                    toastText = "Это цифра!"
                    toastHandler(toast, toastText)
                }
                return@addTextChangedListener;
            }

            when (editTextValue) {
                "&" -> {
                    toastText = "Это спец символ!"
                    toastHandler(toast, toastText)
                }
                "#" -> {
                    toastText = "Это спец символ!"
                    toastHandler(toast, toastText)
                }
                "<" -> {
                    toastText = "Это спец символ!"
                    toastHandler(toast, toastText)
                }
                else -> {
                    toastText = "Непредусмотренный вариант!"
                    toastHandler(toast, toastText)
                }
            }
        }
    }

    private fun toastHandler(toast: Toast, text: String) {
        toast.setText(text)
        toast.show()
    }
}