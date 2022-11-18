package com.example.vinilos.repositories

import android.app.Application
import com.example.vinilos.models.Album
import com.example.vinilos.network.NetworkServiceAdapter
import com.google.gson.Gson
import org.json.JSONObject

class AlbumRepository (val application: Application){
    suspend fun refreshData(): List<Album> {
        return NetworkServiceAdapter.getInstance(application).getAlbums()
    }

    suspend fun createAlbum(albumToCreate: Album){
        val gson = Gson()
        val jsonBody = gson.toJson(albumToCreate)
        val jsonObject = JSONObject(jsonBody)
        NetworkServiceAdapter.getInstance(application).postAlbum(jsonObject)
    }
}
