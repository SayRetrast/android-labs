package com.example.fourthlab

import android.os.Bundle
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
        val currentQuestion = 0

        val textView: TextView = findViewById(R.id.question)
        textView.text = questions[currentQuestion]["question"].toString()
    }
}