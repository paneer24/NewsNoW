package com.example.newsnow

import android.app.Application
import android.util.Log
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NewsApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        Log.d(TAG, "Firebase initialized: ${FirebaseApp.getInstance() != null}")
    }
    
    companion object {
        private const val TAG = "NewsApplication"
    }
}
