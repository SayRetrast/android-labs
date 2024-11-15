package com.example.fourthlab

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuizViewModel : ViewModel() {
    private val questions = arrayOf(
        mapOf("question" to "Является ли Земля четвертой планетой от Солнца?", "answer" to false),
        mapOf("question" to "Существует ли жизнь на других планетах в нашей галактике?", "answer" to false),
        mapOf("question" to "Является ли вода химическим соединением?", "answer" to true),
        mapOf("question" to "Могут ли некоторые животные спать под водой?", "answer" to true),
        mapOf("question" to "Является ли электричество формой энергии?", "answer" to true)
    )

    private val _currentQuestion = MutableLiveData<Int>().apply { value = 0 }
    val currentQuestion: LiveData<Int> get() = _currentQuestion

    private val _correctAnswers = MutableLiveData<Int>().apply { value = 0 }
    val correctAnswers: LiveData<Int> get() = _correctAnswers

    private val _questionText = MutableLiveData<String>().apply {
        value = questions[_currentQuestion.value ?: 0]["question"].toString()
    }
    val questionText: LiveData<String> get() = _questionText

    fun checkAnswer(isTrue: Boolean) {
        val correctAnswer = questions[_currentQuestion.value ?: 0]["answer"] as Boolean
        if (isTrue == correctAnswer) {
            _correctAnswers.value = (_correctAnswers.value ?: 0) + 1
        }
    }

    fun nextQuestion() {
        val nextQuestionIndex = (_currentQuestion.value ?: 0) + 1
        if (nextQuestionIndex < questions.size) {
            _currentQuestion.value = nextQuestionIndex
            _questionText.value = questions[nextQuestionIndex]["question"].toString()
        }
    }

    fun getFinalScore(): String {
        return "Количество правильных ответов: ${_correctAnswers.value}"
    }
}
