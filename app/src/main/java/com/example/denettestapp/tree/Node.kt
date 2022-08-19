package com.example.denettestapp.tree

data class Node(val name: String, val childs: List<Node> = listOf(), val parent: Node? = null)
