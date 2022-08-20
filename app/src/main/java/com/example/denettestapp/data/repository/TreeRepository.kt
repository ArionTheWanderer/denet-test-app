package com.example.denettestapp.data.repository

import com.example.denettestapp.data.common.DataState
import com.example.denettestapp.data.common.NodeEntityMapper
import com.example.denettestapp.tree.Tree
import kotlinx.coroutines.flow.MutableStateFlow
import org.w3c.dom.Node
import javax.inject.Inject
import javax.inject.Singleton

/**
 * TODO Сохранение дерева в БД
 * Хранить будем в одной таблице (id, id родителя, имя) и маппить при передаче слою presentation
 */
@Singleton
class TreeRepository
@Inject constructor(
    private val nodeEntityMapper: NodeEntityMapper
): ITreeRepository {
    private val _tree = MutableStateFlow(Tree())

    override fun getNode(nodeId: Int): DataState<Node> {
        TODO("Not yet implemented")
    }

    override fun createNode() {
        TODO("Not yet implemented")
    }

    override fun deleteNode() {
        TODO("Not yet implemented")
    }

}
