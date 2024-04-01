package org.example

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

/**
 * Tests the functionality of the AssociativeArray class.
 */
class AssociativeArrayTest {

    /**
     * Test case to verify insertion and retrieval of key-value pairs.
     * Verifies that inserted values can be retrieved correctly.
     */
    @Test
    fun testInsertionAndRetrieval() {
        val associativeArray = AssociativeArray<Int, String>()
        associativeArray[1] = "2"
        associativeArray[2] = "69"
        associativeArray[3] = "four"

        assertEquals("2", associativeArray[1])
        assertEquals("69", associativeArray[2])
        assertEquals("four", associativeArray[3])
    }

    /**
     * Test case to verify updating of values for existing keys.
     * Verifies that updating a value for an existing key works correctly.
     */
    @Test
    fun testUpdate() {
        val associativeArray = AssociativeArray<Int, String>()
        associativeArray[1] = "One"
        associativeArray[1] = "Update"

        assertEquals("Update", associativeArray[1])
    }

    /**
     * Test case to verify removal of key-value pairs.
     * Verifies that a key-value pair can be successfully removed from the associative array.
     */
    @Test
    fun testRemoval() {
        val associativeArray = AssociativeArray<Int, String>()
        associativeArray[1] = "Seven"
        associativeArray[2] = "2"

        assertTrue(associativeArray.remove(1))
        assertFalse(associativeArray.contains(1))
    }

    /**
     * Test case to verify the size of the associative array.
     * Verifies that the size method returns the correct number of elements.
     */
    @Test
    fun testSize() {
        val associativeArray = AssociativeArray<Int, String>()
        associativeArray[1] = "Meaning of Life"
        associativeArray[2] = "42"

        assertEquals(2, associativeArray.size())
    }

    /**
     * Test case to verify retrieval of key-value pairs.
     * Verifies that the keyValuePairs method returns all key-value pairs stored in the associative array.
     */
    @Test
    fun testKeyValuePairs() {
        val associativeArray = AssociativeArray<Int, String>()
        associativeArray[1] = "7"
        associativeArray[2] = "pi"

        val pairs = associativeArray.keyValuePairs()
        assertTrue(pairs.contains(1 to "7"))
        assertTrue(pairs.contains(2 to "pi"))
    }

    /**
     * Test case to verify rehashing of the associative array.
     * Verifies that the associative array correctly rehashes and maintains functionality with a large number of elements.
     */
    @Test
    fun testRehash() {
        val associativeArray = AssociativeArray<Int, String>()
        for (i in 1..1000) {
            associativeArray[i] = "Value$i"
        }
        assertEquals("Value500", associativeArray[500])
    }
}
