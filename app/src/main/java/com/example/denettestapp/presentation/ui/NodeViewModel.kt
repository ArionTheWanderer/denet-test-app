package com.example.denettestapp.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.denettestapp.data.repository.ITreeRepository
import com.example.denettestapp.data.common.DataState
import com.example.denettestapp.presentation.model.tree.Node
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class NodeViewModel
@Inject constructor(
    private val treeRepository: ITreeRepository
): ViewModel() {
    private val _node = MutableStateFlow<DataState<Node>>(DataState.Init)

    private val _error = MutableStateFlow("")

    private val _isDeleted = MutableStateFlow(false)

    val node: StateFlow<DataState<Node>>
        get() = _node

    val error: StateFlow<String>
        get() = _error

    val isDeleted: StateFlow<Boolean>
        get() = _isDeleted

    fun getNode(nodeId: Long) = viewModelScope.launch {
        _node.value = DataState.Loading
        val result = treeRepository.getNode(nodeId)
        _node.value = result
    }

    fun createNode() = viewModelScope.launch {
        val node = (_node.value as? DataState.Data)?.data
        if (node != null) {
            val result = treeRepository.createNode(node)
            if (result is DataState.Error) {
                _error.value = result.error
            } else if (result is DataState.Data) {
                getNode((_node.value as? DataState.Data)?.data?.id ?: 1)
            }
        } else {
            _error.value = "The node can't be inserted"
        }
    }

    fun deleteNode() = viewModelScope.launch {
        val node = (_node.value as? DataState.Data)?.data
        val parentNode = node?.parent
        if (parentNode != null) {
            val result = treeRepository.deleteNode(node)
            if (result is DataState.Error) {
                _error.value = "The node can't be deleted"
            } else if (result is DataState.Data) {
                _isDeleted.value = true
            }
        } else {
            _error.value = "The node can't be deleted"
        }
    }

    fun clearError() {
        _error.value = ""
    }
}
