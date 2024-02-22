package org.example
/**
 * `radixSort` sorts a given array of integers using the radix method. (base 10 LSD)
 *
 * @param array An array of integers of any size that needs to be sorted
 */
fun radixSort(array: IntArray) {
    // Set the radix (base) to 10
    val radix = 10

    // Get the maximum number of digits
    val maxDigits = getMaxDigits(array)

    var digitPlace = 1
    val sortedArray = IntArray(array.size)

    // Repeat the sorting process for each digit position
    // from least significant to most significant
    repeat(maxDigits) {
        // Count the occurrences of each digit
        val digitCount = IntArray(radix)

        for (number in array) {
            val currentDigit = getDigit(number, digitPlace)
            digitCount[currentDigit]++
        }

        // Adjust the count array to store the final positions of the digits
        for (i in 1 until radix) {
            digitCount[i] += digitCount[i - 1]
        }

        // Place the elements into the sorted array based on their digit values
        for (i in array.size - 1 downTo 0) {
            val currentDigit = getDigit(array[i], digitPlace)
            sortedArray[digitCount[currentDigit] - 1] = array[i]
            digitCount[currentDigit]--
        }

        // Copy the sorted array back into the original array and move to the next
        // digit
        sortedArray.copyInto(array)
        digitPlace *= radix
    }
}

/**
 * Gets the maximum number of digits among the integers in the array.
 *
 * @param array An array of integers
 * @return The maximum number of digits
 */
fun getMaxDigits(array: IntArray): Int {
    var maxNumber = Integer.MIN_VALUE
    for (number in array) {
        if (number > maxNumber) {
            maxNumber = number
        }
    }
    // Determine the number of digits in the maximum number
    return when {
        maxNumber == Int.MIN_VALUE -> 1
        else -> maxNumber.toString().length
    }
}

/**
 * Obtains the digit at a specific place in a number.
 *
 * @param number The number we need to obtain the digit by
 * @param place The place value (1 for ones, 10 for tens, etc.)
 * @return The digit at the specified place
 */
fun getDigit(number: Int, place: Int): Int {
    return (number / place) % 10
}