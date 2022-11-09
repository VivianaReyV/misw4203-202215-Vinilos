package com.example.vinilos.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.vinilos.models.Album
import com.example.vinilos.repositories.AlbumRepository
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject

class AlbumViewModel (application: Application) : AndroidViewModel(application) {

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

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    private var _eventCreateAlbumSuccess = MutableLiveData<Boolean>(false)

    val eventCreateAlbumSuccess: LiveData<Boolean>
        get() = _eventCreateAlbumSuccess

    private var _isCreateAlbumSuccessShown = MutableLiveData<Boolean>(false)

    val isCreateAlbumSuccessShown: LiveData<Boolean>
        get() = _isCreateAlbumSuccessShown

    init {
         refreshDataFromNetwork()
    }
    private fun refreshDataFromNetwork() {
        try {
            viewModelScope.launch(Dispatchers.Default) {
                withContext(Dispatchers.IO) {
                    var data = albumsRepository.refreshData()
                    _albums.postValue(data)
                }
                _eventNetworkError.postValue(false)
                _isNetworkErrorShown.postValue(false)
            }
        }
        catch (e:Exception){
            Log.d("Error", e.toString())
            _eventNetworkError.value = true
        }
    }

    fun createAlbum(albumToCreate: Album) {
        try{
            viewModelScope.launch(Dispatchers.Default)  {
                albumsRepository.createAlbum(albumToCreate)
                _eventNetworkError.postValue(false)
                _isNetworkErrorShown.postValue(false)
                _eventCreateAlbumSuccess.postValue(true)
            }
        }
        catch (e:Exception){
            Log.d("Error", e.toString())
            _eventNetworkError.value = true
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