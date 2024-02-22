package org.example
/**
 * `mergeSort` sorts a given array of integers using the merge method.
 *
 * @param array An array of integers of any size that needs to be sorted
 */
fun mergeSort(array: IntArray) {
    // Recurse until the array size is a unit of 1
    if (array.size <= 1){
        return
    }

    // Divide the arrays in half
    val midIndex = array.size / 2
    val leftHalf = array.copyOfRange(0, midIndex)
    val rightHalf = array.copyOfRange(midIndex, array.size)

    // Recurse
    mergeSort(leftHalf)
    mergeSort(rightHalf)

    // Build and merge each array half that has been sorted
    merge(leftHalf, rightHalf, array)
}

/**
 * `merge` sorts a given array of integers using the insertion method.
 *
 * @param leftHalf The left half of a split array
 * @param rightHalf The right half of a split array
 * @param array An array of integers of any size that needs to be sorted
 */
fun merge(leftHalf: IntArray, rightHalf: IntArray, array: IntArray) {
    var leftIndex = 0
    var rightIndex = 0
    var mergedIndex = 0

    // Merge the two halves into the original array
    while (leftIndex < leftHalf.size && rightIndex < rightHalf.size) {
        if (leftHalf[leftIndex] <= rightHalf[rightIndex]) {
            array[mergedIndex++] = leftHalf[leftIndex++]
        } else {
            array[mergedIndex++] = rightHalf[rightIndex++]
        }
    }

    // Add remaining elements from the left half, if any
    while (leftIndex < leftHalf.size) {
        array[mergedIndex++] = leftHalf[leftIndex++]
    }

    // Add remaining elements from the right half, if any
    while (rightIndex < rightHalf.size) {
        array[mergedIndex++] = rightHalf[rightIndex++]
    }
}
