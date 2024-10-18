package com.example.fourthlab

import android.os.Bundle
import android.view.View
import android.widget.Button
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

        val questions = arrayOf(
            mapOf(
                "question" to "Является ли Земля четвертой планетой от Солнца?",
                "answer" to false,
            ),
            mapOf(
                "question" to "Существует ли жизнь на других планетах в нашей галактике?",
                "answer" to false,
            ),
            mapOf(
                "question" to "Является ли вода химическим соединением?",
                "answer" to false,
            ),
            mapOf(
                "question" to "Могут ли некоторые животные спать под водой?",
                "answer" to false,
            ),
            mapOf(
                "question" to "Является ли электричество формой энергии?",
                "answer" to false,
            ),
        )
        var currentQuestion = 0
        var correctAnswers = 0

        val textView: TextView = findViewById(R.id.question)
        textView.text = questions[currentQuestion]["question"].toString()

        val buttonFalse: Button = findViewById(R.id.button_false)
        val buttonTrue: Button = findViewById(R.id.button_true)
        val buttonNext: Button = findViewById(R.id.button_next)

        buttonFalse.setOnClickListener {
            if (questions[currentQuestion]["answer"] == false) {
                correctAnswers += 1
            }

            buttonFalse.visibility = View.INVISIBLE
            buttonTrue.visibility = View.INVISIBLE
        }

        buttonTrue.setOnClickListener {
            if (questions[currentQuestion]["answer"] == true) {
                correctAnswers += 1
            }

            buttonFalse.visibility = View.INVISIBLE
            buttonTrue.visibility = View.INVISIBLE
        }

        buttonNext.setOnClickListener {
            currentQuestion += 1
            textView.text = questions[currentQuestion]["question"].toString()

            buttonFalse.visibility = View.VISIBLE
            buttonTrue.visibility = View.VISIBLE
        }
    }
}