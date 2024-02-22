package org.example
/**
 * `insertionSort` sorts a given array of integers using the insertion method.
 *
 * @param array An array of integers of any size that needs to be sorted
 */
fun insertionSort(array: IntArray) {
    for (indexElement in 1 until array.size) {

        // Choose the element to be inserted
        val elementInsert = array[indexElement]
        // Move the elements that are greater than element
        // and insert to one position ahead of their current position
        var greaterIndex = indexElement - 1
        while (greaterIndex >= 0 && array[greaterIndex] > elementInsert) {
            array[greaterIndex + 1] = array[greaterIndex]
            greaterIndex--
        }
        // Insert the element in the correcgt spot
        array[greaterIndex + 1] = elementInsert
    }
}
