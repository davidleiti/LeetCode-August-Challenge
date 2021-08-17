class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

class SolutionRecursive {
    
    fun goodNodes(root: TreeNode?): Int = root?.let { node -> goodNodesRec(node, root.`val`) } ?: 0
    
    fun goodNodesRec(node: TreeNode, branchMax: Int): Int {
        val newMax = if (node.`val` > branchMax) node.`val` else branchMax
        val leftCount = node.left?.let { leftNode -> goodNodesRec(leftNode, newMax) } ?: 0
        val rightCount = node.right?.let { rightNode -> goodNodesRec(rightNode, newMax) } ?: 0
        return leftCount + rightCount + if (newMax == node.`val`) 1 else 0
    }
}

class SolutionIterative { 

    fun goodNodes(root: TreeNode?): Int {
        var count = 0 
        val nodes: Stack<Pair<TreeNode, Int>> = Stack()
        root?.let { nodes.push(it to it.`val`) }
        
        while (nodes.isNotEmpty()) {
            val (node, previousMax) = nodes.pop()
            var branchMax = previousMax
            if (node.`val` >= branchMax) { 
                branchMax = node.`val`
                count++
            }
            
            node.left?.let { leftNode -> nodes.push(leftNode to branchMax) }
            node.right?.let { rightNode -> nodes.push(rightNode to branchMax) }
        }
        
        return count
    }
}