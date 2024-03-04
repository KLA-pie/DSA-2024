package org.example

import kotlin.system.measureTimeMillis
import kotlin.random.Random
import kotlin.math.sqrt

/**
 * Performs calculate the running conditions of both matrix multiplication
 */
fun main() {
    val sizes = listOf(32, 64, 128, 256) // Use Matrix Sizes of Base 2
    val trials = 10 

    for (size in sizes) { // Iterate through all possible matrix sizes

        val traditionalTimes = mutableListOf<Long>() // Initialize time list
        val strassenTimes = mutableListOf<Long>()

        for (trial in 1..trials) {
            val matrixA = randomMatrix(size)
            val matrixB = randomMatrix(size)

            val traditionalTime = measureTimeMillis {
                matrixA.multiply(matrixB)
            }
            traditionalTimes.add(traditionalTime)

            val strassenTime = measureTimeMillis {
                matrixA.strassenMultiply(matrixB)
            }
            strassenTimes.add(strassenTime)
            
        }
        // Print the result out after the last trial run
        println("Standard - Average: ${averageTime(traditionalTimes)} ms, Min: ${traditionalTimes.minOrNull()} ms, Max: ${traditionalTimes.maxOrNull()} ms, Std Dev: ${stdTime(traditionalTimes)} ms")
        println("Strassen's - Average: ${averageTime(strassenTimes)} ms, Min: ${strassenTimes.minOrNull()} ms, Max: ${strassenTimes.maxOrNull()} ms, Std Dev: ${stdTime(strassenTimes)} ms\n")
    }
}

/**
 * Generate a randomly assorted matrix
 *
 * @param [size] The desired matrix size
 *
 * @return A random matrix
 */
fun randomMatrix(size: Int): Matrix {
    val matrix = Array(size) { Array(size) { Random.nextInt(100) } }
    return Matrix(matrix)
}

/**
 * Calculate the average of all times
 *
 * @param [times] A list of times in milliseconds of all runtimes
 *
 * @return The average of all elements in the list
 */
fun averageTime(times: List<Long>): Double {
    return times.average()
}

/**
 * Calculate the standard deviation of all times
 *
 * @param [times] A list of times in milliseconds of all runtimes
 *
 * @return The standard deviation of all elements in the list
 */
fun stdTime(times: List<Long>): Double {
    val average = averageTime(times)
    val variance = times.map { (it - average) * (it - average) }.average()
    return sqrt(variance)
}