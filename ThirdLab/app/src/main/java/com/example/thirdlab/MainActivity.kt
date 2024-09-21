package com.example.thirdlab

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
            this.checkPalindrome()
        }
    }

    private fun checkPalindrome() {
        val toast = Toast.makeText(this, "", Toast.LENGTH_SHORT)

        val editText = findViewById<EditText>(R.id.editText)
        val editTextValue = editText.text.toString()

        var formattedString = ""
        for (i in editTextValue.indices) {
            val letter = editTextValue[i]
            if (letter.isLetter()) {
                formattedString += letter.lowercaseChar()
            }
        }

        for (i in formattedString.indices) {
            val letterOne = formattedString[i]
            val letterTwo = formattedString[formattedString.length - 1 - i]

            if (letterOne != letterTwo) {
                toastHandler(toast, "Строка не палиндром")
                return;
            }
        }

        toastHandler(toast, "Строка палиндром")
    }

    private fun toastHandler(toast: Toast, text: String) {
        toast.setText(text)
        toast.show()
    }
}