package com.example.vinilos.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.vinilos.models.CollectorPerformers
import com.example.vinilos.repositories.CollectorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject

class CollectorPerformerViewModel (application: Application): AndroidViewModel(application) {

    private val collectorsRepository = CollectorRepository(application)

    private val _collectors = MutableLiveData<List<CollectorPerformers>>()
    private val _collector = MutableLiveData<JSONObject>()

    val collectors: LiveData<List<CollectorPerformers>>
        get() = _collectors

    val collector: LiveData<JSONObject>
        get() = _collector

    private var _eventNetworkError = MutableLiveData(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    init {
        refreshDataFromNetwork()
    }

    private fun refreshDataFromNetwork() {
        try {
            viewModelScope.launch(Dispatchers.Default) {
                withContext(Dispatchers.IO) {
                    val data = collectorsRepository.refreshData()
                    _collectors.postValue(data)
                }
                _eventNetworkError.postValue(false)
                _isNetworkErrorShown.postValue(false)
            }
        }
        catch (e:Exception){
            _eventNetworkError.value = true
        }
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CollectorPerformerViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CollectorPerformerViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}