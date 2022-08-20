package com.example.denettestapp.data.db

import androidx.room.*

@Dao
interface NodeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNode(nodeEntity: NodeEntity): Long

    @Delete
    suspend fun deleteNode(nodeEntity: NodeEntity): Int

    @Query("UPDATE node SET parent_id = :parentId WHERE parent_id = :nodeId")
    suspend fun replaceParent(nodeId: Long, parentId: Long)

    @Query("SELECT * FROM node WHERE _id = :nodeId")
    suspend fun getNode(nodeId: Long): NodeEntity?

    @Query("SELECT * FROM node WHERE parent_id = :parentId")
    suspend fun getChildNodes(parentId: Long): List<NodeEntity>

}
