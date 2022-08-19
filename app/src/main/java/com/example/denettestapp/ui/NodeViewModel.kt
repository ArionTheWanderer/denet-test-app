package com.example.denettestapp.ui

import androidx.lifecycle.ViewModel
import com.example.denettestapp.data.ITreeRepository
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

    val partyList: StateFlow<DataState<Node>>
        get() = _node
}
