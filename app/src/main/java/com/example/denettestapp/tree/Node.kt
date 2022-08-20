package com.example.denettestapp.tree

data class Node(val id: Long, val name: String, val parent: Node? = null, val children: List<Node> = listOf())
