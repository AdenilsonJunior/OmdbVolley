package br.edu.ifsp.scl.movievolley

import android.os.AsyncTask
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.JsonParser

class SearchAsyncTask(private val searchType: MainActivity.SearchType) : AsyncTask<String, Unit, Unit>() {

    private var callback: SearchCallback? = null

    override fun doInBackground(vararg params: String) {
        val queue = Volley.newRequestQueue(MovieApplication.context)
        val url = "http://www.omdbapi.com/?${getSearchParameter(params[0])}&apikey=a18a47d1"
        queue.add(
                StringRequest(Request.Method.GET,  url,
                        Response.Listener<String>{
                            val response = JsonParser().parse(it).asJsonObject

                            if(response.get("Response").asString != "False"){
                                val movieResponse = Gson().fromJson(response, MovieResponse::class.java)
                                callback?.onSuccess(movieResponse)
                            }else{
                                callback?.onMovieNotFound()
                            }

                        },
                        Response.ErrorListener {
                            callback?.onError(it)
                        }))
    }

    private fun getSearchParameter(searchQuery: String) =
            when (searchType) {
                MainActivity.SearchType.TITLE -> {
                    "t=$searchQuery"
                }
                MainActivity.SearchType.ID -> {
                    "i=$searchQuery"
                }
            }

    fun attachCallback(callback: SearchCallback) {
        this.callback = callback
    }

    fun detachCallback() {
        callback = null
    }
}

interface SearchCallback {

    fun onSuccess(movieResponse: MovieResponse)
    fun onError(e: Exception)
    fun onMovieNotFound()

}