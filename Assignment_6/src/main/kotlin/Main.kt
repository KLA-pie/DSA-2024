package org.example
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin
/**
 * PseudoRandomNumberGenerator represents a class for generating
 * pseudo-random numbers
 * @property [seed] The seed value used for initializing the generator.
 */
class PseudoRandomNumberGenerator(private var seed: Int) {
    // Constants for the linear congruential generator
    private val a = 1103515245
    private val c = 12345
    private val m = (1L shl 31) - 1

    /**
     * Generates the next pseudo-random integer.
     * @return The next pseudo-random integer generated.
     */
    fun nextInt(): Int {
        seed = ((seed.toLong() * a + c) % m).toInt()
        return seed
    }

    /**
     * Generates the next pseudo-random double value
     * @return The next pseudo-random double value generated.
     */
    fun nextDouble(): Double {
        return nextInt().toDouble() / m
    }
}

/**
 * Performs the Quantum Fourier Transform on the given input list of complex numbers.
 * @param [input] A mutable list of complex numbers representing the input to the
 * QFT. The list should have a length equal to 2^n
 * @param [random] An instance of a PRNG used for generating random angles.
 * @return A list of complex numbers representing the output of the
 * QFT
 */
fun quantumFourierTransform(input: MutableList<Double>, random: PseudoRandomNumberGenerator): List<Double> {
    val n = input.size
    val output = MutableList(n) { 0.0 }

    // Perform Quantum Fourier Transform
    for (k in 0 until n) {
        var sumReal = 0.0
        var sumImaginary = 0.0

        // Apply QFT for each output element
        for (j in 0 until n) {
            val angle = 2 * PI * k * j / n
            val randomAngle = 2 * PI * random.nextDouble() // Generate a random angle
            sumReal += input[j] * cos(angle + randomAngle)
            sumImaginary += input[j] * sin(angle + randomAngle)
        }

        // Calculate the real and imaginary parts of the output
        output[k] = sumReal / n + sumImaginary / n
    }

    return output
}

/**
 * An implementation of the Deutsch-Josza algorithm using
 * a provided blackbox function (oracle)
 * @param [oracle] A function that takes an integer and returns 0 or 1
 * based on the function's behavior.
 * @param [n] The number of qubits used in the algorithm.
 * @param [random] An instance of PRNG used for generating
 * random angles in the Quantum Fourier Transform.
 * @return A boolean statement: True if the oracle function is constant and
 * false if it is balanced.
 */
fun deutschJoszaAlgorithm(oracle: (Int) -> Int, n: Int, random: PseudoRandomNumberGenerator): Boolean {
    val inputSize = 1 shl n
    val input = MutableList(inputSize) { 1.0 / inputSize }

    // Apply Hadamard gates to the input qubits
    for (i in 0 until n) {
        for (j in 0 until (1 shl i)) {
            for (k in 0 until (1 shl (n - i - 1))) {
                val index1 = j + k
                val index2 = index1 + (1 shl (n - i - 1))
                val temp = input[index1]
                input[index1] += input[index2]
                input[index2] = temp - input[index2]
            }
        }
    }

    // Apply the oracle
    for (i in 0 until inputSize) {
        input[i] *= if (oracle(i) == 0) 1.0 else -1.0
    }

    // Apply Hadamard gates to the input qubits again
    for (i in 0 until n) {
        for (j in 0 until (1 shl i)) {
            for (k in 0 until (1 shl (n - i - 1))) {
                val index1 = j + k
                val index2 = index1 + (1 shl (n - i - 1))
                val temp = input[index1]
                input[index1] += input[index2]
                input[index2] = temp - input[index2]
            }
        }
    }

    // Apply Quantum Fourier Transform
    val output = quantumFourierTransform(input, random)

    // Check if the output is constant (Greater than 3/4 of the elements are
    // either all positive or all negative)
    val countPositive = output.count { it > 0 }
    val countNegative = output.count { it < 0 }
    return countPositive > output.size * 3 / 4 || countNegative > output.size * 3 / 4
}
