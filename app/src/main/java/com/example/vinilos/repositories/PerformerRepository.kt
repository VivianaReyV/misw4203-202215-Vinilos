package com.example.vinilos.repositories

import android.app.Application
import com.example.vinilos.models.Performer
import com.example.vinilos.network.NetworkServiceAdapter

class PerformerRepository (val application: Application){
    suspend fun refreshData(): List<Performer> {
        return NetworkServiceAdapter.getInstance(application).getPerformers()
    }
}