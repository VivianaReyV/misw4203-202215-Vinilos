package com.example.vinilos.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.vinilos.models.Performer
import com.example.vinilos.network.NetworkServiceAdapter
import com.google.gson.Gson
import org.json.JSONObject

class PerformerRepository (val application: Application){
    fun refreshData(onSuccess: (List<Performer>)->Unit, onError: (error: VolleyError)->Unit) {
        NetworkServiceAdapter.getInstance(application).getPerformers({
            onSuccess(it)
        },
            onError
        )
    }
}