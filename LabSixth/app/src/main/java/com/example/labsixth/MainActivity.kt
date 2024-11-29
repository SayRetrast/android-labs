package com.example.labsixth

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import com.example.labsixth.db.db

class MainActivity : AppCompatActivity() {
    private val crimeDao = db.crimeDao()
    private val crimes = crimeDao.getAll()

    private lateinit var criminalFormButton: Button
    private lateinit var relativeLayout: RelativeLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        criminalFormButton = findViewById(R.id.criminal_form_button)
        relativeLayout = findViewById(R.id.relative_layout)

        crimes.observe(this, Observer { crimes ->
            crimes.forEachIndexed { index, crime ->
                val textViewText = "Title: ${crime.title} (${if (crime.isSolved) "solved" else "not solved"})"

                val textView = TextView(this).apply {
                    id = crime.id
                    text = textViewText
                    textSize = 16f
                }

                val layoutParams = RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    addRule(RelativeLayout.ALIGN_PARENT_TOP)
                    setMargins(0, 120 * (index + 1), 0, 0)
                }

                relativeLayout.addView(textView, layoutParams)
            }
        })

        criminalFormButton.setOnClickListener {
            val criminalFormIntent = Intent(this, CriminalFormActivity::class.java)
            startActivity(criminalFormIntent)
        }
    }
}