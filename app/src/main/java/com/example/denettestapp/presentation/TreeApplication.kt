package com.example.denettestapp.presentation

import android.app.Application
import com.example.denettestapp.presentation.di.app.AppComponent
import com.example.denettestapp.presentation.di.app.DaggerAppComponent

class TreeApplication: Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }
}
