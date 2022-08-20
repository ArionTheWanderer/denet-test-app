package com.example.denettestapp.presentation.di.presentation

import com.example.denettestapp.presentation.ui.NodeFragment
import dagger.Subcomponent

@PresentationScope
@Subcomponent(
    modules = [
        ViewModelModule::class
    ]
)
interface PresentationComponent {
    fun inject(nodeFragment: NodeFragment)

    @Subcomponent.Factory
    interface Factory{
        fun create(): PresentationComponent
    }
}
