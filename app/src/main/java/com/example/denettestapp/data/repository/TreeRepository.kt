package com.example.denettestapp.data.repository

import com.example.denettestapp.data.common.DataState
import com.example.denettestapp.data.common.NodeEntityMapper
import com.example.denettestapp.data.db.NodeDao
import com.example.denettestapp.data.db.NodeEntity
import com.example.denettestapp.tree.Node
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * TODO Сохранение дерева в БД
 * Хранить будем в одной таблице (id, id родителя, имя) и маппить при передаче слою presentation
 */
@Singleton
class TreeRepository
@Inject constructor(
    private val nodeDao: NodeDao,
    private val nodeEntityMapper: NodeEntityMapper
): ITreeRepository {

    override suspend fun getNode(nodeId: Long): DataState<Node> = withContext(Dispatchers.IO) {
        val entity = nodeDao.getNode(nodeId) ?: return@withContext DataState.Error("The entity can't be found ")
        val parentEntity: NodeEntity? = if (entity.parentId != null) {
            nodeDao.getNode(entity.parentId)!!
        } else {
            null
        }
        val childEntities = nodeDao.getChildNodes(entity.id)
        val node = nodeEntityMapper.mapToNode(
            parentEntity = parentEntity,
            entity = entity,
            childEntities = childEntities
        )
        return@withContext DataState.Data(node)
    }

    override suspend fun createNode(parentNode: Node): DataState<Long> = withContext(Dispatchers.IO) {
        val newEntity = NodeEntity(id = 0, parentId = parentNode.id, name = getRandomString(8))
        val rowId = nodeDao.insertNode(newEntity)
        if (rowId != -1L) {
            return@withContext DataState.Data(rowId)
        } else {
            return@withContext DataState.Error("The node can't be inserted ")
        }
    }

    override suspend fun deleteNode(node: Node): DataState<Int> = withContext(Dispatchers.IO) {
        val entity = nodeEntityMapper.mapToEntity(node)
        if (entity.parentId != null) {
            val deletedRowsCount = nodeDao.deleteNode(entity)
            if (deletedRowsCount == 1) {
                nodeDao.replaceParent(nodeId = entity.id, parentId = entity.parentId)
                return@withContext DataState.Data(deletedRowsCount)
            }
        }
        return@withContext DataState.Error("The node can't be deleted")

    }

    private fun getRandomString(length: Int) : String {
        val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }

}
