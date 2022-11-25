package com.example.vinilos.models

data class CollectorPerformers (
    val collectorId:Int?,
    val name:String,
    val email:String,
    val telephone:String,
    val favoritePerformers:List<Performer>,
    val favoritePerformer1: String?,
    val favoritePerformer2: String?,
    val favoritePerformer3: String?
)