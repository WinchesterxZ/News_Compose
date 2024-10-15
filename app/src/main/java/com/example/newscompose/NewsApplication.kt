package com.example.newscompose

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NewsApplication:Application() {

    init {
        applicationInstance = this
    }
    companion object{
        lateinit var applicationInstance:NewsApplication
        fun getInstance(): Context = applicationInstance.applicationContext

    }

}