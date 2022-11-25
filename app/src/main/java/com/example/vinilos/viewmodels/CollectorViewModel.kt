package com.example.vinilos.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.vinilos.models.Collector
import com.example.vinilos.repositories.CollectorRepository
import kotlinx.coroutines.*
import org.json.JSONObject

class CollectorViewModel(application: Application) : AndroidViewModel(application) {

    private val collectorsRepository = CollectorRepository(application)
    private val _text = MutableLiveData<String>().apply {
        value = "This is create collector Fragment"
    }
    private val _collectors = MutableLiveData<List<Collector>>()
    private val _collector = MutableLiveData<JSONObject>()

    val collectors: LiveData<List<Collector>>
        get() = _collectors

    val collector: LiveData<JSONObject>
        get() = _collector

    private var _eventNetworkError = MutableLiveData(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    private var _eventCreateCollectorSuccess = MutableLiveData(false)

    val eventCreateCollectorSuccess: LiveData<Boolean>
        get() = _eventCreateCollectorSuccess

    private var _isCreateCollectorSuccessShown = MutableLiveData(false)

    val isCreateCollectorSuccessShown: LiveData<Boolean>
        get() = _isCreateCollectorSuccessShown
    private val viewModelJob = SupervisorJob()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    fun createCollector(collectorToCreate: Collector) {
        uiScope.launch {
            try {
                collectorsRepository.createCollector(collectorToCreate)
                _eventNetworkError.postValue(false)
                _isNetworkErrorShown.postValue(false)
                _eventCreateCollectorSuccess.postValue(true)
            } catch (e: Exception) {
                _eventNetworkError.value = true
            }
        }
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    fun onSuccessCollectorCreatedShown() {
        _isCreateCollectorSuccessShown.value = true
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CollectorViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CollectorViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }

    val text: LiveData<String> = _text
}