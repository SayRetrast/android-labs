package com.example.labsixth

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.marginBottom
import androidx.core.view.marginStart
import com.example.labsixth.db.db

class MainActivity : AppCompatActivity() {
    private lateinit var criminalFormButton: Button
    private lateinit var relativeLayout: RelativeLayout

    private val crimeDao = db.crimeDao()
    private val crimes = crimeDao.getAll()


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

//        for (crime in crimes) {
//            val textView = TextView(this).apply {
//                id = View.generateViewId()
//                text = crime.title
//            }
//
//            val layoutParams = RelativeLayout.LayoutParams(
//                RelativeLayout.LayoutParams.WRAP_CONTENT,
//                RelativeLayout.LayoutParams.WRAP_CONTENT
//            ).apply {
//                addRule(RelativeLayout.ALIGN_PARENT_TOP)
//                setMargins(0, 40 * crime.id, 0, 0)
//            }
//
//            relativeLayout.addView(textView, layoutParams)
//        }

        crimes.forEachIndexed { index, crime ->
            val textViewText = "Title: ${crime.title}\n${if (crime.isSolved) "solved" else "not solved"}"

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
                setMargins(0, 200 * index + 1, 0, 0)
            }

            relativeLayout.addView(textView, layoutParams)
        }

        criminalFormButton.setOnClickListener {
            val criminalFormIntent = Intent(this, CriminalFormActivity::class.java)
            startActivity(criminalFormIntent)
        }
    }
}