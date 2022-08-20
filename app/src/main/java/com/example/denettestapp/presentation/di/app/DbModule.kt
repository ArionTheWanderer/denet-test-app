package com.example.denettestapp.presentation.di.app

import android.app.Application
import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.denettestapp.data.db.NodeDao
import com.example.denettestapp.data.db.PrepopulateEntities
import com.example.denettestapp.data.db.TreeDatabase
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executors
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
        ).fallbackToDestructiveMigration().addCallback(object: RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                val root = PrepopulateEntities.root
                val values = ContentValues(3)
                values.put("_id", root.id)
                values.put("parent_id", root.parentId)
                values.put("name", root.name)
                Executors.newSingleThreadScheduledExecutor().execute {
                    db.insert(
                        "node",
                        SQLiteDatabase.CONFLICT_REPLACE,
                        values
                    )
                }
            }
        }).build()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideNodeDao(db: TreeDatabase): NodeDao {
        return db.nodeDao()
    }
}
