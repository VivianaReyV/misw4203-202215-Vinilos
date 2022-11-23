package com.example.vinilos.repositories

import android.app.Application
import com.example.vinilos.models.Collector
import com.example.vinilos.network.NetworkServiceAdapter

class CollectorRepository (val application: Application){
    suspend fun refreshData(): List<Collector> {
        return NetworkServiceAdapter.getInstance(application).getCollectors()
    }
}