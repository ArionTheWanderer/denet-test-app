package com.example.denettestapp.presentation.ui.common

import androidx.appcompat.app.AppCompatActivity
import com.example.denettestapp.presentation.TreeApplication

open class BaseActivity: AppCompatActivity() {

    private val appComponent get() = (application as TreeApplication).appComponent

    val activityComponent by lazy {
        appComponent.getActivityComponentFactory().create(this)
    }

    private val presentationComponent by lazy {
        activityComponent.getPresentationComponentFactory().create()
    }

    protected val injector get() = presentationComponent
}
