package com.example.vinilos.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.vinilos.models.Album
import com.example.vinilos.models.Performer
import com.example.vinilos.network.NetworkServiceAdapter
import com.google.gson.Gson
import org.json.JSONObject

class PerformerRepository (val application: Application){
    suspend fun refreshData(): List<Performer> {
        return NetworkServiceAdapter.getInstance(application).getPerformers()
    }
}