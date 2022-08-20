package com.example.denettestapp.data.common

import com.example.denettestapp.data.db.NodeEntity
import com.example.denettestapp.tree.Node
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NodeEntityMapper @Inject constructor() {

    fun mapToNode(parentEntity: NodeEntity, entity: NodeEntity, childEntities: List<NodeEntity>): Node {
        val parentNode = Node(id = parentEntity.id, name = parentEntity.name, parent = null, children = listOf())
        val childNodes: MutableList<Node> = mutableListOf()
        for (childEntity in childEntities) {
            val childNode = Node(id = childEntity.id, name = childEntity.name, children = listOf())
            childNodes.add(childNode)
        }
        return Node(
            id = entity.id,
            name = entity.name,
            parent = parentNode,
            children = childNodes
        )
    }

    fun mapToEntity(node: Node): NodeEntity {
        return NodeEntity(
            id = node.id,
            parentId = node.parent?.id ?: 0L,
            name = node.name
        )
    }

}
