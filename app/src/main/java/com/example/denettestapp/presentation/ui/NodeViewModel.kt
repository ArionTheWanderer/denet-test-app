package com.example.denettestapp.presentation.ui

import androidx.lifecycle.ViewModel
import com.example.denettestapp.data.repository.ITreeRepository
import com.example.denettestapp.data.common.DataState
import com.example.denettestapp.tree.Node
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

/**
 * TODO Создать инфраструктуру DI для создания вьюмоделей
 */
class NodeViewModel
@Inject constructor(
    private val treeRepository: ITreeRepository
): ViewModel() {
    private val _node = MutableStateFlow<DataState<Node>>(DataState.Init)

    val node: StateFlow<DataState<Node>>
        get() = _node

    fun getNode(nodeId: Int) {

    }

    fun createNode() {

    }

    fun deleteNode() {

    }
}
