package org.example

import kotlin.random.Random
import kotlin.system.measureNanoTime
import kotlin.math.sqrt

/**
 * Run a set of tests to benchmark
 */
fun main() {
    // Define array sizes for benchmarking
    val arraySizes = intArrayOf(1000, 5000, 10000, 50000, 100000)
    // Number of iterations for each array size
    val iterations = 10

    // Map sorting algorithm names to their respective functions
    val sortingAlgorithms = mapOf(
        "Radix Sort" to ::radixSort,
        "Selection Sort" to ::selectionSort,
        "Merge Sort" to ::mergeSort,
        "Insertion Sort" to ::insertionSort
    )

    // Iterate through each array size
    for (size in arraySizes) {
        println("Array size: $size")

        // Iterate through each sorting algorithm
        for ((name, sortFunc) in sortingAlgorithms) {
            val times = mutableListOf<Long>()
            // Repeat sorting and benchmarking for the specified number of iterations
            repeat(iterations) {
                // Generate a random array of the current size
                val arr = generateRandomArray(size)
                // Measure the sorting time using the current sorting algorithm
                times.add(measureSortTime(sortFunc, arr))
            }
            // Print benchmarking results for the current sorting algorithm
            printBenchmarkResult(name, times)
        }

        println("--------------------------------------------------------")
    }
}

/**
 * Generates a random array of integers.
 *
 * @param size The size of the array to generate
 * @return The randomly generated array
 */
fun generateRandomArray(size: Int): IntArray {
    return IntArray(size) { Random.nextInt(1000) }
}

/**
 * Measures the execution time of a sorting function on a given array.
 *
 * @param sortFunc The sorting function to benchmark
 * @param arr The array to be sorted
 * @return The execution time of the sorting function in nanoseconds
 */
fun measureSortTime(sortFunc: (IntArray) -> Unit, arr: IntArray): Long {
    return measureNanoTime { sortFunc(arr) }
}

/**
 * Prints benchmarking results for a sorting algorithm.
 *
 * @param name The name of the sorting algorithm
 * @param times The list of execution times for the algorithm
 */
fun printBenchmarkResult(name: String, times: List<Long>) {
    // Calculate minimum, maximum, average, and standard deviation of execution times
    val minTime = times.minOrNull() ?: 0
    val maxTime = times.maxOrNull() ?: 0
    val avgTime = times.average().toLong()
    val stdDevTime = calculateStandardDeviation(times)

    // Print benchmarking results
    println("$name:")
    println("Minimum time: $minTime ns")
    println("Maximum time: $maxTime ns")
    println("Average time: $avgTime ns")
    println("Standard deviation: $stdDevTime ns")
}

/**
 * Calculates the standard deviation of a list of values.
 *
 * @param times The list of values
 * @return The standard deviation of the values
 */
fun calculateStandardDeviation(times: List<Long>): Double {
    // Calculate the average of the values
    val avg = times.average()
    // Calculate the sum of squares of differences from the average
    val sum = times.fold(0.0) { acc, time -> acc + (time - avg) * (time - avg) }
    // Calculate the square root of the mean of the sum of squares
    return sqrt(sum / times.size)
}
