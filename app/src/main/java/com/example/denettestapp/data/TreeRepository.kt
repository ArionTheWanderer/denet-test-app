package com.example.denettestapp.data

import com.example.denettestapp.data.common.DataState
import com.example.denettestapp.tree.Tree
import kotlinx.coroutines.flow.MutableStateFlow
import org.w3c.dom.Node

/**
 * TODO Сохранение дерева в БД
 */
class TreeRepository: ITreeRepository {
    private val _tree = MutableStateFlow(Tree())

    override fun getNode(nodeId: Int): DataState<Node> {
        TODO("Not yet implemented")
    }

}