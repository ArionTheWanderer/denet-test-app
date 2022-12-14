package com.example.denettestapp.presentation.di.activity

import androidx.appcompat.app.AppCompatActivity
import com.example.denettestapp.presentation.di.presentation.PresentationComponent
import dagger.BindsInstance
import dagger.Subcomponent

@ActivityScope
@Subcomponent
interface ActivityComponent {
    fun getPresentationComponentFactory(): PresentationComponent.Factory

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance activity: AppCompatActivity): ActivityComponent
    }
}
