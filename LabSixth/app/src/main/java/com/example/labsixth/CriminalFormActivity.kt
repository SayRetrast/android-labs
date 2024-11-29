package com.example.labsixth

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.labsixth.db.db
import com.example.labsixth.db.entities.Crime

class CriminalFormActivity : AppCompatActivity() {
    private lateinit var titleEditText: EditText
    private lateinit var addCriminalButton: Button
    private lateinit var isSolvedCheckBox: CheckBox
    private lateinit var backButton: Button

    private val crimeViewModel: CrimeViewModel by viewModels()

    private val crimeDao = db.crimeDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_criminal_form)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        titleEditText = findViewById(R.id.criminal_title_edittext)
        addCriminalButton = findViewById(R.id.add_criminal_button)
        isSolvedCheckBox = findViewById(R.id.solved_checkbox)
        backButton = findViewById(R.id.back_button)

        addCriminalButton.setOnClickListener {
            val criminalTitle = titleEditText.text.toString()
            val isCriminalSolved = isSolvedCheckBox.isChecked

            crimeDao.insert(Crime(title = criminalTitle, date = null, isSolved = isCriminalSolved))

            finish()
        }

        backButton.setOnClickListener {
            finish()
        }
    }

}
