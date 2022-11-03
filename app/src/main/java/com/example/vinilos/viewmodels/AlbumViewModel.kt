package com.example.vinilos.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.vinilos.models.Album
import com.example.vinilos.network.NetworkServiceAdapter
import com.google.gson.Gson
import org.json.JSONObject

class AlbumViewModel (application: Application) : AndroidViewModel(application) {

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

    init {
         refreshDataFromNetwork()
         createAlbumFromNetwork()
    }
    private fun refreshDataFromNetwork() {
        NetworkServiceAdapter.getInstance(getApplication()).getAlbums({
            _albums.postValue(it)
            _eventNetworkError.value = false
            _isNetworkErrorShown.value = false
        },{
            _eventNetworkError.value = true
        })
        /*val albumToCreate = Album(null,"Un verano sin ti",
            "https://i.pinimg.com/564x/aa/5f/ed/aa5fed7fac61cc8f41d1e79db917a7cd.jpg",
            "2022-08-01T00:00:00-05:00",
            "un verano sin ti es de bad bunny",
            "Rock",
            "Elektra"
        )
        val gson = Gson()
        val jsonBody = gson.toJson(albumToCreate)
        val jsonObject = JSONObject(jsonBody)

        NetworkServiceAdapter.getInstance(getApplication()).postAlbum(jsonObject, {
            _album.postValue(it)
            _eventNetworkError.value = false
            _isNetworkErrorShown.value = false
        },{
            _eventNetworkError.value = true
        })*/

    }

    private fun createAlbumFromNetwork() {
        val albumToCreate = Album(null,"Un verano sin ti",
            "https://i.pinimg.com/564x/aa/5f/ed/aa5fed7fac61cc8f41d1e79db917a7cd.jpg",
            "2022-08-01T00:00:00-05:00",
            "un verano sin ti es de bad bunny",
            "Rock",
            "Elektra"
        )
        val gson = Gson()
        val jsonBody = gson.toJson(albumToCreate)
        val jsonObject = JSONObject(jsonBody)

        NetworkServiceAdapter.getInstance(getApplication()).postAlbum(jsonObject, {
            _album.postValue(it)
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
            if (modelClass.isAssignableFrom(AlbumViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AlbumViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
    val text: LiveData<String> = _text
}