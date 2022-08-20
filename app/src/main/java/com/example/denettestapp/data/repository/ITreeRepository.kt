package com.example.denettestapp.data.repository

import com.example.denettestapp.data.common.DataState
import com.example.denettestapp.tree.Node

interface ITreeRepository {
    suspend fun getNode(nodeId: Long): DataState<Node>
    suspend fun createNode(parentNode: Node): DataState<Long>
    suspend fun deleteNode(node: Node): DataState<Int>
}
