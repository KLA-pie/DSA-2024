package org.example
/**
 * `selectionSort` sorts a given array of integers using the selection method.
 *
 * @param array An array of integers of any size that needs to be sorted
 */
fun selectionSort(array: IntArray) {
    val length = array.size
    // Go through the entire array
    for (currentPos in 0 until length - 1) {
        // Obtain the value of the current index iteratively
        var minIndex = currentPos
        for (unsortIndex in currentPos + 1 until length) {
            // Obtain the smaller value if a smaller one is found
            if (array[unsortIndex] < array[minIndex]) {
                minIndex = unsortIndex
            }
        }

        // Swap the minimum element with the current position
        if (minIndex != currentPos) {
            val temp = array[minIndex]
            array[minIndex] = array[currentPos]
            array[currentPos] = temp
        }
    }
}