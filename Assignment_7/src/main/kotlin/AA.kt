package org.example
import kotlin.math.abs

/**
 * Represents a mapping of keys to values.
 * @param K the type of the keys
 * @param V the type of the values
 * @property capacity the initial capacity of the associative array
 * @property size the number of elements stored in the associative array
 * @property resize the threshold for resizing the associative array
 * @property entries an array of mutable lists to store key-value pairs
 * @property primeHash a prime number used in the hash division
 */
class AssociativeArray<K, V> {
    private data class Entry<K, V>(val key: K, var value: V)

    private var capacity = 16
    private var size = 0
    private var resize = capacity * 0.75
    private var entries = Array<MutableList<Entry<K, V>>>(capacity) { mutableListOf() }
    private val primeHash = 98317

    /**
     * Computes the hash value for the given key.
     * @param key the key whose hash is to be computed
     * @return the hash value
     */
    private fun hash(key: K): Int {
        return abs(key.hashCode() * primeHash % capacity)
    }

    /**
     * Rehashes the array when the load factor exceeds the threshold.
     */
    private fun rehash() {
        val oldTable = entries
        capacity *= 2
        entries = Array(capacity) { mutableListOf() }
        resize = capacity * 0.75
        size = 0
        for (bucket in oldTable) {
            for (entry in bucket) {
                set(entry.key, entry.value)
            }
        }
    }

    /**
     * Inserts or updates the mapping from the key to the value.
     * If the key already exists, update the value
     * @param k the key
     * @param v the value
     */
    operator fun set(k: K, v: V) {
        val index = hash(k)
        val bucket = entries[index]
        for (entry in bucket) {
            if (entry.key == k) {
                entry.value = v
                return
            }
        }
        bucket.add(Entry(k, v))
        size++
        if (size > resize) {
            rehash()
        }
    }

    /**
     * Checks if the associative array contains the specified key.
     * @param k the key to check for existence
     * @return A Boolean true if the key exists
     */
    operator fun contains(k: K): Boolean {
        val index = hash(k)
        val bucket = entries[index]
        for (entry in bucket) {
            if (entry.key == k) {
                return true
            }
        }
        return false
    }

    /**
     * Retrieves the value associated with the specified key.
     * @param k the key whose associated value is to be retrieved
     * @return the value associated with the key, or null if the key doesn't exist
     */
    operator fun get(k: K): V? {
        val index = hash(k)
        val bucket = entries[index]
        for (entry in bucket) {
            if (entry.key == k) {
                return entry.value
            }
        }
        return null
    }

    /**
     * Removes the specified key from the associative array.
     * @param k the key to remove
     * @return true if the key was successfully removed, false if the key was not found
     */
    fun remove(k: K): Boolean {
        val index = hash(k)
        val bucket = entries[index]
        val iterator = bucket.iterator()
        while (iterator.hasNext()) {
            val entry = iterator.next()
            if (entry.key == k) {
                iterator.remove()
                size--
                return true
            }
        }
        return false
    }

    /**
     * Retrieves the number of elements stored in the associative array.
     * @return the number of elements
     */
    fun size(): Int {
        return size
    }

    /**
     * Retrieves the list of key-value pairs stored in the associative array.
     * @return the list of key-value pairs
     */
    fun keyValuePairs(): List<Pair<K, V>> {
        val pairs = mutableListOf<Pair<K, V>>()
        for (bucket in entries) {
            for (entry in bucket) {
                pairs.add(entry.key to entry.value)
            }
        }
        return pairs
    }
}
