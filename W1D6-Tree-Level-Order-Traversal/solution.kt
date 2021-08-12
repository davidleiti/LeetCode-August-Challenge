/**
 * Definition for a Node.
 * class Node(var `val`: Int) {
 *     var children: List<Node?> = listOf()
 * }
 */
class Solution {
    fun levelOrder(root: Node?): List<List<Int>> {
        if (root == null) return listOf()
        
        val levels: MutableList<MutableList<Int>> = mutableListOf()
        val nodes = Stack<Pair<Int, Node>>().apply { push(0 to root) }
        
        while (nodes.isNotEmpty()) { 
            val (level, node) = nodes.pop()
            levels.getOrNull(level)?.add(node.`val`) ?: run { 
                levels.add(mutableListOf<Int>(node.`val`))
            }

            for (index in node.children.lastIndex downTo 0) { 
                node.children[index]?.let { nodes.push(level + 1 to it) }
            }
        }
        
        return levels
    }
}