package com.example.fourthlab

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CheatActivity : AppCompatActivity() {

    private lateinit var answerTextView: TextView
    private lateinit var buttonShowAnswer: Button
    private lateinit var buttonBack: Button
    private lateinit var androidApiTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        answerTextView = findViewById(R.id.cheat_answer)
        buttonShowAnswer = findViewById(R.id.show_answer)
        buttonBack = findViewById(R.id.close_cheat)
        androidApiTextView = findViewById(R.id.android_api)

        val androidApiText = "Android API: ${Build.VERSION.SDK_INT}"
        androidApiTextView.text = androidApiText

        val isAnswerTrue = intent.getBooleanExtra("ANSWER", false)
        val answerText = if (isAnswerTrue) "Правильный ответ: Да" else "Правильный ответ: Нет"

        buttonShowAnswer.setOnClickListener {
            answerTextView.text = answerText
        }

        buttonBack.setOnClickListener {
            finish()
        }
    }
}
