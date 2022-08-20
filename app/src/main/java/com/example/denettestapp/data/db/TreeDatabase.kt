package com.example.denettestapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NodeEntity::class], version = 1)
abstract class TreeDatabase: RoomDatabase() {
    abstract fun nodeDao(): NodeDao

    companion object {
        const val DB_NAME = "tree_database"
    }
}
