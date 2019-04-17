package br.edu.ifsp.scl.movievolley

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class MovieApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

}