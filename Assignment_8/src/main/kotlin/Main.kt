package org.example

// Pseudocode for closest element query
/**
function closestElement(root, query):
    closest = root.value
    minDistance = distance(root.value, query)

    while root is not null:
        if root.value == query:
            return root.value

        if distance(root.value, query) < minDistance:
            minDistance = distance(root.value, query)
            closest = root.value

        if query < root.value:
            root = root.left
        else:
            root = root.right

    return closest
 */

/**
 * BinaryNode represents a node in a binary tree.
 * @property `val` The integer value of the node.
 * @property left Reference to the lower child node.
 * @property right Reference to the upper child node.
 */
class BinaryNode(var `val`: Int) {
    var left: BinaryNode? = null
    var right: BinaryNode? = null
}

/**
 * Converts a sorted array to a binary search tree (BST).
 * @param nums The sorted array of integers.
 * @return The root of the BST.
 */
fun sortedArrayToBST(nums: IntArray): BinaryNode? {
    return helper(nums, 0, nums.size - 1)
}

/**
 * Build a BST recursively from a sorted array.
 * @param nums The sorted array of integers.
 * @param left The left index of the current subarray.
 * @param right The right index of the current subarray.
 * @return The root of the BST constructed from the given subarray.
 */
fun helper(nums: IntArray, left: Int, right: Int): BinaryNode? {
    if (left > right) { // No elements in the subarray.
        return null
    }

    val center = (left + right) / 2
    val root = BinaryNode(nums[center]) // Create a new node with the middle value.

    // Build left and right subtrees by recursion
    root.left = helper(nums, left, center - 1)
    root.right = helper(nums, center + 1, right)

    return root
}

/**
 * Deletes a node with the given value from the BST.
 * @param root The root of the BST.
 * @param value The value of the node to be deleted.
 * @return The root of the modified BST.
 */
fun deleteNode(root: BinaryNode?, value: Int): BinaryNode? {
    if (root == null) return null

    when {
        value < root.`val` -> root.left = deleteNode(root.left, value) // Traverse left subtree.
        value > root.`val` -> root.right = deleteNode(root.right, value) // Traverse right subtree.
        else -> {
            // Replace node with its successor or predecessor.
            if (root.left == null) return root.right
            else if (root.right == null) return root.left

            root.`val` = minValue(root.right!!)
            root.right = deleteNode(root.right, root.`val`)
        }
    }
    return root
}

/**
 * Finds the minimum value in a BST.
 * @param node The root node of the subtree to search for the minimum value.
 * @return The minimum value found in the subtree.
 */
fun minValue(node: BinaryNode): Int {
    var minVal = node.`val`
    var current = node
    while (current.left != null) {
        minVal = current.left!!.`val`
        current = current.left!!
    }
    return minVal
}

/**
 * Calculates the sum of values within a specified range in a BST.
 * @param root The root of the BST.
 * @param lower The lower bound of the range.
 * @param upper The upper bound of the range.
 * @return An integer for the sum of values within the specified range.
 */
fun rangeSumBST(root: BinaryNode?, lower: Int, upper: Int): Int {
    if (root == null) return 0

    var sum = 0

    // Traverse the tree and add values within the range.
    if (root.`val` in lower..upper) {
        sum += root.`val`
    }

    if (root.`val` > lower) {
        sum += rangeSumBST(root.left, lower, upper)
    }

    if (root.`val` < upper) {
        sum += rangeSumBST(root.right, lower, upper)
    }

    return sum
}

/**
 * Finds the mode(s) in a BST.
 * @param root The root of the BST.
 * @return A list of mode values.
 */
fun findMode(root: BinaryNode?): List<Int> {
    val modeList = mutableListOf<Int>()
    var prev: Int? = null
    var maxCount = 0
    var currentCount = 0

    /**
     * Traverses the BST in-order and updates mode information.
     * @param node The current node being processed.
     */
    fun traverse(node: BinaryNode?) {
        if (node == null) return

        traverse(node.left)

        // Count occurrences of each value and update mode list.
        if (prev != null && prev == node.`val`) {
            currentCount++
        } else {
            currentCount = 1
        }

        if (currentCount > maxCount) {
            modeList.clear()
            modeList.add(node.`val`)
            maxCount = currentCount
        } else if (currentCount == maxCount) {
            modeList.add(node.`val`)
        }

        prev = node.`val`

        traverse(node.right)
    }

    traverse(root)

    return modeList
}
