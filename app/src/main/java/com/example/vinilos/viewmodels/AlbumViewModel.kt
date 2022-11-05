package com.example.vinilos.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.vinilos.models.Album
import com.example.vinilos.repositories.AlbumRepository
import com.google.gson.Gson
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
        albumsRepository.refreshData({
            _albums.postValue(it)
            _eventNetworkError.value = false
            _isNetworkErrorShown.value = false
        },{
            _eventNetworkError.value = true
        })
    }

    fun createAlbum(albumToCreate: Album) {
        albumsRepository.createAlbum(albumToCreate,
            {
                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false
                _eventCreateAlbumSuccess.value = true
            },{
                _eventNetworkError.value = true
                _eventCreateAlbumSuccess.value = false
                _isCreateAlbumSuccessShown.value = false
            }
        )
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