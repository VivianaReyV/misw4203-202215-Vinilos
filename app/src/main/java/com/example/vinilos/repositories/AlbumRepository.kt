package com.example.vinilos.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.vinilos.models.Album
import com.example.vinilos.network.NetworkServiceAdapter
import com.google.gson.Gson
import org.json.JSONObject

class AlbumRepository (val application: Application){
    fun refreshData(onSuccess: (List<Album>)->Unit, onError: (error: VolleyError)->Unit) {
        NetworkServiceAdapter.getInstance(application).getAlbums({
            onSuccess(it)
        },
            onError
        )
    }

    fun createAlbum(albumToCreate: Album, onSuccess:(resp:JSONObject)->Unit, onError: (error: VolleyError)->Unit){
        val gson = Gson()
        val jsonBody = gson.toJson(albumToCreate)
        val jsonObject = JSONObject(jsonBody)
        NetworkServiceAdapter.getInstance(application).postAlbum(jsonObject, {
            onSuccess(it)
        },{
            onError
        })
    }
}
