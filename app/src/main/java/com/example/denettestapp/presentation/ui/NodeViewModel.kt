package com.example.denettestapp.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.denettestapp.data.repository.ITreeRepository
import com.example.denettestapp.data.common.DataState
import com.example.denettestapp.tree.Node
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
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

    fun getNode(nodeId: Long) = viewModelScope.launch {
        _node.value = DataState.Loading
        val result = treeRepository.getNode(nodeId)
        _node.value = result
    }

    // TODO StateFlow
    fun createNode() = viewModelScope.launch {
        val parentNode = (_node.value as DataState.Data).data.parent
        if (parentNode != null) {
            val result = treeRepository.createNode(parentNode)

        } else {

        }
    }

    // TODO StateFlow
    fun deleteNode() = viewModelScope.launch {
        val node = (_node.value as DataState.Data).data
        if (node != null) {
            val result = treeRepository.deleteNode(node)

        } else {

        }
    }
}
