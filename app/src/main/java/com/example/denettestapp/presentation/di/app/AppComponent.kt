package com.example.denettestapp.presentation.di.app

import android.app.Application
import com.example.denettestapp.presentation.di.activity.ActivityComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        TreeModule::class,
        DbModule::class
    ]
)
interface AppComponent  {
    fun getActivityComponentFactory(): ActivityComponent.Factory

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance application: Application): AppComponent
    }
}
