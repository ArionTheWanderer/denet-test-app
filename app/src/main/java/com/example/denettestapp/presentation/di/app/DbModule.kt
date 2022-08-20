package com.example.denettestapp.presentation.di.app

import android.app.Application
import androidx.room.Room
import com.example.denettestapp.data.db.NodeDao
import com.example.denettestapp.data.db.TreeDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DbModule {
    @JvmStatic
    @Singleton
    @Provides
    fun provideDb(app: Application): TreeDatabase {
        return Room.databaseBuilder(
            app,
            TreeDatabase::class.java,
            TreeDatabase.DB_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideNodeDao(db: TreeDatabase): NodeDao {
        return db.nodeDao()
    }
}
