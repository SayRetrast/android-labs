package com.example.labsixth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.labsixth.db.db
import com.example.labsixth.db.entities.Crime

class CrimeViewModel : ViewModel() {
    val crimes: LiveData<List<Crime>> get() = db.crimeDao().getAll()
}
