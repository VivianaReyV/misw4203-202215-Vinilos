package com.example.vinilos.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.vinilos.models.Album
import com.example.vinilos.repositories.AlbumRepository
import kotlinx.coroutines.*
import org.json.JSONObject

class AlbumViewModel(application: Application) : AndroidViewModel(application) {

    private val albumsRepository = AlbumRepository(application)
    private val _text = MutableLiveData<String>().apply {
        value = "This is album Fragment"
    }
    private val _albums = MutableLiveData<List<Album>>()
    private val _album = MutableLiveData<JSONObject>()

    val albums: LiveData<List<Album>>
        get() = _albums

    val album: LiveData<JSONObject>
        get() = _album

    private var _eventNetworkError = MutableLiveData(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    private var _eventCreateAlbumSuccess = MutableLiveData(false)

    val eventCreateAlbumSuccess: LiveData<Boolean>
        get() = _eventCreateAlbumSuccess

    private var _isCreateAlbumSuccessShown = MutableLiveData(false)

    val isCreateAlbumSuccessShown: LiveData<Boolean>
        get() = _isCreateAlbumSuccessShown
    private val viewModelJob = SupervisorJob()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    init {
        refreshDataFromNetwork()
    }

    private fun refreshDataFromNetwork() {
        uiScope.launch {
            try {
                val data = albumsRepository.refreshData()
                _albums.postValue(data)
                //}
                _eventNetworkError.postValue(false)
                _isNetworkErrorShown.postValue(false)
            } catch (e: Exception) {
                _eventNetworkError.value = true
            }
        }


    }

    fun createAlbum(albumToCreate: Album) {
        uiScope.launch {
            try {
                albumsRepository.createAlbum(albumToCreate)
                _eventNetworkError.postValue(false)
                _isNetworkErrorShown.postValue(false)
                _eventCreateAlbumSuccess.postValue(true)
            } catch (e: Exception) {
                _eventNetworkError.value = true
            }
        }
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    fun onSuccessAlbumCreatedShown() {
        _isCreateAlbumSuccessShown.value = true
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AlbumViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AlbumViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }

    val text: LiveData<String> = _text
}