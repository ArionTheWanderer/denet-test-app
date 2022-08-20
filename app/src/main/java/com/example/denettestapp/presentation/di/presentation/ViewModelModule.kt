package com.example.denettestapp.presentation.di.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.denettestapp.presentation.ui.NodeViewModel
import com.example.denettestapp.presentation.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(NodeViewModel::class)
    abstract fun bindNodeViewModel(nodeViewModel: NodeViewModel): ViewModel

}
