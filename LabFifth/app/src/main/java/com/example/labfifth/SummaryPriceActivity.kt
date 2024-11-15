package com.example.labfifth

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SummaryPriceActivity: AppCompatActivity() {
    private lateinit var summaryPriceTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_summary_price)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        summaryPriceTextView = findViewById(R.id.summary_price)

        val summaryPrice = intent.getDoubleExtra("SUMMARY", 0.0)
        val summaryPriceText = "Сумма заказа: $summaryPrice"
        summaryPriceTextView.text = summaryPriceText
    }
}