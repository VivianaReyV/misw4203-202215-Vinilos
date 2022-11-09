package com.example.vinilos.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CollectorViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is collectors Fragment"
    }
    val text: LiveData<String> = _text
}