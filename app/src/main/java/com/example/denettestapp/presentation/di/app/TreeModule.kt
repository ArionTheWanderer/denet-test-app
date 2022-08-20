package com.example.denettestapp.presentation.di.app

import com.example.denettestapp.data.repository.ITreeRepository
import com.example.denettestapp.data.repository.TreeRepository
import dagger.Binds
import dagger.Module

@Module
abstract class TreeModule {

    @Binds
    abstract fun provideTreeRepository(treeRepository: TreeRepository): ITreeRepository

}
