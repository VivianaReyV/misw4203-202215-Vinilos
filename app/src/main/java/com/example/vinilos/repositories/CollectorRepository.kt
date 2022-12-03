package com.example.vinilos.repositories

import android.app.Application
import com.example.vinilos.models.Collector
import com.example.vinilos.models.CollectorPerformers
import com.example.vinilos.network.NetworkServiceAdapter
import com.google.gson.Gson
import org.json.JSONObject

class CollectorRepository (val application: Application){
    suspend fun refreshData(): List<CollectorPerformers> {
        return NetworkServiceAdapter.getInstance(application).getCollectors()
    }

    suspend fun createCollector(collectorToCreate: Collector){
        val gson = Gson()
        val jsonBody = gson.toJson(collectorToCreate)
        val jsonObject = JSONObject(jsonBody)
        NetworkServiceAdapter.getInstance(application).postCollector(jsonObject)
    }
}