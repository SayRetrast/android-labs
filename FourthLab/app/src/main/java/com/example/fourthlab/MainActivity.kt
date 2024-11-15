package com.example.fourthlab

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {

    private val quizViewModel: QuizViewModel by viewModels()

    private lateinit var textView: TextView
    private lateinit var buttonFalse: Button
    private lateinit var buttonTrue: Button
    private lateinit var buttonNext: Button

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("currentQuestion", quizViewModel.currentQuestion.value ?: 0)
        outState.putInt("correctAnswers", quizViewModel.correctAnswers.value ?: 0)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        quizViewModel.restoreState(savedInstanceState.getInt("currentQuestion", 0), savedInstanceState.getInt("correctAnswers", 0))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.question)
        buttonFalse = findViewById(R.id.button_false)
        buttonTrue = findViewById(R.id.button_true)
        buttonNext = findViewById(R.id.button_next)

        quizViewModel.questionText.observe(this, Observer { questionText ->
            textView.text = questionText
        })

        quizViewModel.correctAnswers.observe(this, Observer {
            if (quizViewModel.currentQuestion.value == 5) {
                textView.text = quizViewModel.getFinalScore()
                buttonFalse.visibility = View.INVISIBLE
                buttonTrue.visibility = View.INVISIBLE
                buttonNext.visibility = View.INVISIBLE
            }
        })

        buttonFalse.setOnClickListener {
            quizViewModel.checkAnswer(isTrue = false)
            buttonFalse.visibility = View.INVISIBLE
            buttonTrue.visibility = View.INVISIBLE
        }

        buttonTrue.setOnClickListener {
            quizViewModel.checkAnswer(isTrue = true)
            buttonFalse.visibility = View.INVISIBLE
            buttonTrue.visibility = View.INVISIBLE
        }

        buttonNext.setOnClickListener {
            if (buttonTrue.visibility == View.VISIBLE && buttonFalse.visibility == View.VISIBLE) {
                return@setOnClickListener
            }

            quizViewModel.nextQuestion()

            if (quizViewModel.currentQuestion.value == 5) {
                buttonNext.isEnabled = false
                buttonNext.isClickable = false
                buttonNext.visibility = View.INVISIBLE
            } else {
                buttonFalse.visibility = View.VISIBLE
                buttonTrue.visibility = View.VISIBLE
            }
        }
    }
}
