package org.example

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class BinaryTreeTests {

    @Test
    // Test a BST compilation of an empty array
    fun testBSTEmptyArray() {
        val nums = intArrayOf()
        val root = sortedArrayToBST(nums)
        assertNull(root)
    }

    @Test
    // Test a BST compilation of a single element
    fun testBSTSingleElement() {
        val nums = intArrayOf(1)
        val root = sortedArrayToBST(nums)
        assertNotNull(root)
        assertEquals(1, root!!.`val`)
    }

    @Test
    // Test a BST compilation of an odd cardinal array
    fun testBSTOddSize() {
        val nums = intArrayOf(1, 2, 3, 4, 5)
        val root = sortedArrayToBST(nums)
        assertNotNull(root)
        assertEquals(3, root!!.`val`)
        assertEquals(1, root.left!!.`val`)
        assertEquals(4, root.right!!.`val`)
    }

    @Test
    // Test a BST compilation of an even cardinal array
    fun testBSTEvenSize() {
        val nums = intArrayOf(1, 2, 3, 4)
        val root = sortedArrayToBST(nums)
        assertNotNull(root)
        assertEquals(2, root!!.`val`)
        assertEquals(1, root.left!!.`val`)
        assertEquals(3, root.right!!.`val`)
    }

    @Test
    // Test a BST compilation of an even cardinal array
    fun testBSTSingleValue() {
        val nums = intArrayOf(5, 5, 5, 5, 5)
        val root = sortedArrayToBST(nums)
        assertNotNull(root)
        assertEquals(5, root!!.`val`)
        assertEquals(5, root.left!!.`val`)
        assertEquals(5, root.right!!.`val`)
    }


    @Test
    // Test a node deletion of a nonexistent node
    fun testDeleteNodeInvalid() {
        val root = BinaryNode(3)
        root.left = BinaryNode(1)
        root.right = BinaryNode(5)
        root.right!!.left = BinaryNode(4)
        root.right!!.right = BinaryNode(6)

        val modifiedRoot = deleteNode(root, 2)

        assertNotNull(modifiedRoot)
        assertEquals(root, modifiedRoot)
    }

    @Test
    // Test a node deletion of a leaf node
    fun testDeleteLeaf() {
        val root = BinaryNode(3)
        root.left = BinaryNode(1)
        root.right = BinaryNode(5)
        root.right!!.left = BinaryNode(4)
        root.right!!.right = BinaryNode(6)

        val newValue = 6
        val modifiedRoot = deleteNode(root, newValue)

        assertNotNull(modifiedRoot)
        assertNull(modifiedRoot!!.right!!.right)
    }

    @Test
    // Test a node deletion of a single child
    fun testDeleteOneChild() {
        val root = BinaryNode(3)
        root.left = BinaryNode(1)
        root.right = BinaryNode(5)
        root.right!!.left = BinaryNode(4)
        root.right!!.right = BinaryNode(6)

        val newValue = 5
        val modifiedRoot = deleteNode(root, newValue)

        assertNotNull(modifiedRoot)
        assertEquals(6, modifiedRoot!!.right!!.`val`)
    }

    @Test
    // Test a node deletion of a double child
    fun testDeleteTwoChildren() {
        val root = BinaryNode(3)
        root.left = BinaryNode(1)
        root.right = BinaryNode(5)
        root.right!!.left = BinaryNode(4)
        root.right!!.right = BinaryNode(6)

        val newValue = 5
        val modifiedRoot = deleteNode(root, newValue)

        assertNotNull(modifiedRoot)
        assertEquals(6, modifiedRoot!!.right!!.`val`)
    }

    @Test
    // Test a node deletion of a root node
    fun testDeleteRootNode() {
        val root = BinaryNode(3)
        root.left = BinaryNode(1)
        root.right = BinaryNode(5)
        root.right!!.left = BinaryNode(4)
        root.right!!.right = BinaryNode(6)

        val newValue = 3
        val modifiedRoot = deleteNode(root, newValue)

        assertNotNull(modifiedRoot)
        assertEquals(4, modifiedRoot!!.`val`)
    }


    @Test
    // Test a range sum with elements outside the range
    fun testRangeOutOfRange() {
        val root = BinaryNode(10)
        root.left = BinaryNode(5)
        root.right = BinaryNode(15)
        root.left!!.left = BinaryNode(3)
        root.left!!.right = BinaryNode(7)
        root.right!!.right = BinaryNode(18)

        val sum = rangeSumBST(root, 1, 2)
        assertEquals(0, sum)
    }

    @Test
    // Test a range sum with elements in the whole BST
    fun testRangeSumAllNodes() {
        val root = BinaryNode(10)
        root.left = BinaryNode(5)
        root.right = BinaryNode(15)
        root.left!!.left = BinaryNode(3)
        root.left!!.right = BinaryNode(7)
        root.right!!.right = BinaryNode(18)

        val sum = rangeSumBST(root, 3, 18)
        assertEquals(58, sum)
    }

    @Test
    // Test a range sum with the root only in the range
    fun testRangeRoot() {
        val root = BinaryNode(10)
        root.left = BinaryNode(5)
        root.right = BinaryNode(15)
        root.left!!.left = BinaryNode(3)
        root.left!!.right = BinaryNode(7)
        root.right!!.right = BinaryNode(18)

        val sum = rangeSumBST(root, 10, 10)
        assertEquals(10, sum)
    }

    @Test
    // Test a range sum with the leaf nodes in the range
    fun testRangeLeafNodes() {
        val root = BinaryNode(10)
        root.left = BinaryNode(5)
        root.right = BinaryNode(15)
        root.left!!.left = BinaryNode(3)
        root.left!!.right = BinaryNode(7)
        root.right!!.right = BinaryNode(18)

        val sum = rangeSumBST(root, 3, 7)
        assertEquals(15, sum)
    }

    @Test
    // Test a range sum with the root only one node in the range
    fun testRangeSingleNode() {
        val root = BinaryNode(10)
        root.left = BinaryNode(5)
        root.right = BinaryNode(15)
        root.left!!.left = BinaryNode(3)
        root.left!!.right = BinaryNode(7)
        root.right!!.right = BinaryNode(18)

        val sum = rangeSumBST(root, 7, 7)
        assertEquals(7, sum)
    }


    @Test
    // Test a bimodal data set
    fun testModeBimodal() {
        val root = BinaryNode(2)
        root.left = BinaryNode(1)
        root.right = BinaryNode(3)
        root.right!!.right = BinaryNode(3)
        root.left!!.left = BinaryNode(1)

        val modeList = findMode(root)
        assertEquals(listOf(1, 3), modeList)
    }

    @Test
    // Test a uniform data set
    fun testModeSame() {
        // Construct a sample BST
        val root = BinaryNode(5)
        root.left = BinaryNode(5)
        root.right = BinaryNode(5)
        root.left!!.left = BinaryNode(5)
        root.left!!.right = BinaryNode(5)
        root.right!!.left = BinaryNode(5)
        root.right!!.right = BinaryNode(5)

        val modeList = findMode(root)
        assertEquals(listOf(5), modeList)
    }

    @Test
    // Test a multimodal data set
    fun testModeMultiple() {
        val root = BinaryNode(1)
        root.left = BinaryNode(2)
        root.right = BinaryNode(3)
        root.left!!.left = BinaryNode(4)
        root.left!!.right = BinaryNode(5)

        val modeList = findMode(root)
        assertEquals(listOf(4, 2, 5, 1, 3), modeList)
    }

    @Test
    // Test an empty data set
    fun testModeEmpty() {
        val root: BinaryNode? = null

        val modeList = findMode(root)
        assertEquals(emptyList<Int>(), modeList)
    }

    @Test
    // Test a single element data set
    fun testModeSingle() {
        val root = BinaryNode(10)

        val modeList = findMode(root)
        assertEquals(listOf(10), modeList)
    }

}
