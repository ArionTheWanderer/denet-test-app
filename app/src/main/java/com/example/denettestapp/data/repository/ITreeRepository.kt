package com.example.denettestapp.data.repository

import com.example.denettestapp.data.common.DataState
import org.w3c.dom.Node

interface ITreeRepository {
    fun getNode(nodeId: Int): DataState<Node>
    fun createNode()
    fun deleteNode()
}
