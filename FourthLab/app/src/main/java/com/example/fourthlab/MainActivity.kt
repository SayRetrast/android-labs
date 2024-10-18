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
    private  val questions = arrayOf(
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
            "answer" to true,
        ),
        mapOf(
            "question" to "Могут ли некоторые животные спать под водой?",
            "answer" to true,
        ),
        mapOf(
            "question" to "Является ли электричество формой энергии?",
            "answer" to true,
        ),
    )
    private var currentQuestion = 0
    private var correctAnswers = 0

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("currentQuestion", this.currentQuestion)
        outState.putInt("correctAnswers", this.correctAnswers)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val textView: TextView = findViewById(R.id.question)
        val buttonFalse: Button = findViewById(R.id.button_false)
        val buttonTrue: Button = findViewById(R.id.button_true)
        val buttonNext: Button = findViewById(R.id.button_next)

        if (savedInstanceState != null) {
            currentQuestion = savedInstanceState.getInt("currentQuestion")
            correctAnswers = savedInstanceState.getInt("correctAnswers")

            if (currentQuestion == 5) {
                textView.text = "Количество правильных ответов: ${this.correctAnswers}"
                return
            }
        }

        textView.text = this.questions[this.currentQuestion]["question"].toString()

        buttonFalse.setOnClickListener {
            if (this.questions[this.currentQuestion]["answer"] == false) {
                this.correctAnswers += 1
            }

            buttonFalse.visibility = View.INVISIBLE
            buttonTrue.visibility = View.INVISIBLE
        }

        buttonTrue.setOnClickListener {
            if (this.questions[this.currentQuestion]["answer"] == true) {
                this.correctAnswers += 1
            }

            buttonFalse.visibility = View.INVISIBLE
            buttonTrue.visibility = View.INVISIBLE
        }

        buttonNext.setOnClickListener {
            if (buttonTrue.visibility == View.VISIBLE && buttonFalse.visibility == View.VISIBLE) {
                return@setOnClickListener
            }

            this.currentQuestion += 1

            if (this.currentQuestion == 5) {
                buttonNext.isEnabled = false
                buttonNext.isClickable = false
                buttonNext.visibility = View.INVISIBLE

                textView.text = "Количество правильных ответов: ${this.correctAnswers}"

                return@setOnClickListener
            }

            textView.text = this.questions[this.currentQuestion]["question"].toString()
            buttonFalse.visibility = View.VISIBLE
            buttonTrue.visibility = View.VISIBLE
        }
    }
}