package com.example.labfifth

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var pagesCountInput: EditText
    private lateinit var discountTextView: TextView
    private lateinit var discountSeekBar: SeekBar
    private lateinit var priceButton: Button
    private val price: Int = 30

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        pagesCountInput = findViewById(R.id.pages_count)
        discountTextView = findViewById(R.id.discount_text)
        discountSeekBar = findViewById(R.id.discount_seekbar)
        priceButton = findViewById(R.id.price_button)

        var pagesCount: Int
        var discount: Int = discountSeekBar.progress

        priceButton.setOnClickListener {
            val editTextValue = pagesCountInput.text
            if (editTextValue.isNullOrEmpty() || !editTextValue.isDigitsOnly()) {
                return@setOnClickListener
            }

            pagesCount = editTextValue.toString().toInt()
            val summaryPrice = pagesCount * price
            val summaryPriceWithDiscount = summaryPrice - summaryPrice.toDouble() / 100 * discount

            val summaryPriceIntent = Intent(this, SummaryPriceActivity::class.java).apply {
                putExtra("SUMMARY", summaryPriceWithDiscount)
            }

            startActivity(summaryPriceIntent)
        }

        discountSeekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                val discountText = "Скидка: ${progress}%"
                discountTextView.text = discountText
                discount = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
    }
}