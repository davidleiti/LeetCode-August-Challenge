/**
 * Example:
 * var ti = TreeNode(5)
 * var v = ti.`val`
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */
class SolutionTree {

    var maxProduct: Long = 0
    
    fun maxProduct(root: TreeNode?): Int {
        return root?.let {
            findMaxProduct(root, 0, buildSumTree(root))
            (maxProduct % 1_000_000_007).toInt()
        } ?: 0
    }
    
    fun findMaxProduct(node: TreeNode, parentSum: Long, sum: TreeNode) {
        if (node.left == null && node.right == null) return

        val nodeSum = parentSum + node.`val`
        val leftSum = sum.left?.`val` ?: 0
        val rightSum = sum.right?.`val` ?: 0
        val product = if (leftSum < rightSum) {
            (leftSum + nodeSum) * rightSum
        } else {
            (rightSum + nodeSum) * leftSum
        }

        maxProduct = if (maxProduct >= product) maxProduct else product.toLong()
        node.left?.let { leftNode -> findMaxProduct(leftNode, nodeSum + rightSum, sum.left!!) }
        node.right?.let { rightNode -> findMaxProduct(rightNode, nodeSum + leftSum, sum.right!!) }
    }

    fun buildSumTree(node: TreeNode): TreeNode {
        val leftNode = node.left?.let { buildSumTree(it) }
        val rightNode = node.right?.let { buildSumTree(it) }
        return TreeNode(node.`val` + (leftNode?.`val` ?: 0) + (rightNode?.`val` ?: 0)).apply {
            leftNode?.let { left = it }
            rightNode?.let { right = it }
        }
    }
}

class SolutionList {
    fun maxProduct(root: TreeNode?): Int {
        return root?.let {
            val sums = LinkedList<Int>()
            val totalSum = getSums(root, sums)
            var maxProduct: Long = 0
            for (sum in sums) {
                val product: Long = sum.toLong() * (totalSum - sum)
                if (product > maxProduct) maxProduct = product
            }
            (maxProduct % 1_000_000_007).toInt()
        } ?: 0
    }

    fun getSums(node: TreeNode, destination: MutableList<Int>): Int {
        val leftSum = node.left?.let { leftNode -> getSums(leftNode, destination) } ?: 0
        val rightSum = node.right?.let { rightNode -> getSums(rightNode, destination) } ?: 0
        val sum = node.`val` + leftSum + rightSum
        destination.add(sum)
        
        return sum
    }
}