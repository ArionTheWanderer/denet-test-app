package com.example.denettestapp.data.db

import androidx.room.*

@Dao
interface NodeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNode(nodeEntity: NodeEntity)

    @Delete
    suspend fun deleteNode(nodeEntity: NodeEntity)

    @Query("UPDATE node SET parent_id = :parentId WHERE parent_id = :nodeId")
    suspend fun replaceParent(nodeId: Long, parentId: Long)

    @Query("SELECT * FROM node WHERE _id = :nodeId")
    suspend fun getNode(nodeId: Long): NodeEntity?

}
