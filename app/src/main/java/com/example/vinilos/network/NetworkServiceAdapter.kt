package com.example.vinilos.network

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.vinilos.models.Album
import com.example.vinilos.models.Performer
import com.example.vinilos.models.CollectorPerformers
import org.json.JSONArray
import org.json.JSONObject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class NetworkServiceAdapter constructor(context: Context) {
    companion object{
        const val BASE_URL= "https://back-vynils-14.herokuapp.com/"
        private var instance: NetworkServiceAdapter? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: NetworkServiceAdapter(context).also {
                    instance = it
                }
            }
    }
    private val requestQueue: RequestQueue by lazy {
        // applicationContext keeps you from leaking the Activity or BroadcastReceiver if someone passes one in.
        Volley.newRequestQueue(context.applicationContext)
    }

    suspend fun getAlbums() = suspendCoroutine<List<Album>>{ cont ->
        requestQueue.add(getRequest("albums",
            { response ->
                val resp = JSONArray(response)
                val list = mutableListOf<Album>()
                for (i in 0 until resp.length()) {
                    val item = resp.getJSONObject(i)
                    list.add(i, Album(albumId = item.getInt("id"),name = item.getString("name"), cover = item.getString("cover"), recordLabel = item.getString("recordLabel"), releaseDate = item.getString("releaseDate"), genre = item.getString("genre"), description = item.getString("description")))
                }
                cont.resume(list)
            },
            {
                cont.resumeWithException(it)
            }))
    }

    suspend fun postAlbum(body: JSONObject) = suspendCoroutine<JSONObject>{ cont ->
        requestQueue.add(postRequest("albums",
            body,
            { response ->
                cont.resume(response)
            },
            {
                cont.resumeWithException(it)
            }))
    }

    suspend fun getPerformers() = suspendCoroutine<List<Performer>>{ cont ->
        requestQueue.add(getRequest("musicians",
            { response ->
                val resp = JSONArray(response)
                val list = mutableListOf<Performer>()
                for (i in 0 until resp.length()) {
                    val item = resp.getJSONObject(i)
                    list.add(i, Performer(performerId = item.getInt("id"),name = item.getString("name"), image = item.getString("image"), description = item.getString("description"), birthDate = item.getString("birthDate")))
                }
                cont.resume(list)
            },
            {
                cont.resumeWithException(it)
            }))
    }

    //region Collectors
    suspend fun getCollectors() = suspendCoroutine<List<CollectorPerformers>>{ cont ->
        requestQueue.add(getRequest("collectors",
            { response ->
                val resp = JSONArray(response)
                val list = mutableListOf<CollectorPerformers>()
                var collector: JSONObject? = null
                for (i in 0 until resp.length()) {
                    val favPerformers = mutableListOf<Performer>()
                    collector = resp.getJSONObject(i)
                    try{
                        val listFavoritePerformer = collector.getJSONArray("favoritePerformers")
                        var objFavoritePerformer: JSONObject? = null
                        for(j in 0 until listFavoritePerformer.length()){
                            objFavoritePerformer = listFavoritePerformer[j] as JSONObject
                            favPerformers.add(j, Performer(
                                performerId = objFavoritePerformer.getInt("id"),
                                name = objFavoritePerformer.getString("name"),
                                image = objFavoritePerformer.getString("image"),
                                description = objFavoritePerformer.getString("description"),
                                birthDate = if(objFavoritePerformer.has("birthDate")) objFavoritePerformer.getString("birthDate") else ""
                            ))
                        }

                        list.add(i, CollectorPerformers(
                            collectorId = collector.getInt("id"),
                            name = collector.getString("name"),
                            telephone = collector.getString("telephone"),
                            email = collector.getString("email"),
                            favoritePerformers = favPerformers,
                            favoritePerformer1 = if(listFavoritePerformer.length() > 0) (listFavoritePerformer[0] as JSONObject).getString("name") else "",
                            favoritePerformer2 = if(listFavoritePerformer.length() > 1) (listFavoritePerformer[1] as JSONObject).getString("name") else "",
                            favoritePerformer3 = if(listFavoritePerformer.length() > 2) (listFavoritePerformer[2] as JSONObject).getString("name") else ""
                        ))
                    }catch(_:Exception){ }
                }
                cont.resume(list)
            },
            {
                cont.resumeWithException(it)
            }))
    }
    //endregion

    suspend fun postCollector(body: JSONObject) = suspendCoroutine<JSONObject>{ cont ->
        requestQueue.add(postRequest("collectors",
            body,
            { response ->
                cont.resume(response)
            },
            {
                cont.resumeWithException(it)
            }))
    }

    private fun getRequest(path:String, responseListener: Response.Listener<String>, errorListener: Response.ErrorListener): StringRequest {
        return StringRequest(Request.Method.GET, BASE_URL+path, responseListener,errorListener)
    }
    private fun postRequest(path: String, body: JSONObject,  responseListener: Response.Listener<JSONObject>, errorListener: Response.ErrorListener ):JsonObjectRequest{
        return  JsonObjectRequest(Request.Method.POST, BASE_URL+path, body, responseListener, errorListener)
    }
    /*private fun putRequest(path: String, body: JSONObject,  responseListener: Response.Listener<JSONObject>, errorListener: Response.ErrorListener ):JsonObjectRequest{
        return  JsonObjectRequest(Request.Method.PUT, BASE_URL+path, body, responseListener, errorListener)
    }*/
}