package com.example.vinilos.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.vinilos.models.Performer
import com.example.vinilos.repositories.PerformerRepository
import org.json.JSONObject

class PerformerViewModel (application: Application) : AndroidViewModel(application) {

    private val performersRepository = PerformerRepository(application)
    private val _text = MutableLiveData<String>().apply {
        value = "This is performers Fragment"
    }
    val text: LiveData<String> = _text

    private val _performers = MutableLiveData<List<Performer>>()
    private val _performer = MutableLiveData<JSONObject>()

    val performers: LiveData<List<Performer>>
        get() = _performers

    val performer: LiveData<JSONObject>
        get() = _performer

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    init {
        refreshDataFromNetwork()
    }
    private fun refreshDataFromNetwork() {
        performersRepository.refreshData({
            _performers.postValue(it)
            _eventNetworkError.value = false
            _isNetworkErrorShown.value = false
        },{
            _eventNetworkError.value = true
        })
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PerformerViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return PerformerViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }

}