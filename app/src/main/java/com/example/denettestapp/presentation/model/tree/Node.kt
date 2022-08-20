package com.example.denettestapp.presentation.model.tree

data class Node(val id: Long, val name: String, val parent: Node? = null, val children: List<Node> = listOf())
