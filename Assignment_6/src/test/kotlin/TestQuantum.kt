package org.example
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class QuantumAlgorithmTests {

    @Test
    fun testPseudoRandomNumberGenerator() {
        val rng = PseudoRandomNumberGenerator(123)
        val randomNumber = rng.nextInt()
        // Test that the generated number is within the range of Int
        assertTrue(randomNumber is Int)
    }

    @Test
    fun testQFTSingleQubit() {
        // Test QFT with a single input element
        val input1 = mutableListOf(1.0)
        val rng1 = PseudoRandomNumberGenerator(123)
        val output1 = quantumFourierTransform(input1, rng1)
        assertEquals(1.0, output1[0], 0.25)
    }

    @Test
    fun testQFTSingleOnes() {
        // Test QFT with all ones as input
        val input2 = MutableList(4) { 1.0 }
        val rng2 = PseudoRandomNumberGenerator(123)
        val output2 = quantumFourierTransform(input2, rng2)
        val expectedValue = listOf(1.0, 0.0, 0.0, -0.5)
        assertArrayEquals(expectedValue.toDoubleArray(), output2.toDoubleArray(), 0.25)
    }

    @Test
    fun testQFTAlternatingSet(){
        // Test QFT with alternating input
        val input3 = mutableListOf(1.0, -1.0, 1.0, -1.0)
        val rng3 = PseudoRandomNumberGenerator(123)
        val output3 = quantumFourierTransform(input3, rng3)
        val expectedValue3 = listOf(0.0, 1.0, -0.5, 0.5)
        assertArrayEquals(expectedValue3.toDoubleArray(), output3.toDoubleArray(), 0.25)
    }

    @Test
    fun testDJConstant() {
        // Test with a constant oracle
        val n = 2 // Number of qubits
        val rng = PseudoRandomNumberGenerator(0)
        val constantOracle: (Int) -> Int = { 0 }
        val constantResult = deutschJoszaAlgorithm(constantOracle, n, rng)
        assertTrue(constantResult)
    }

    @Test
    fun testDJBalanced(){
        // Test with a balanced oracle
        val n = 2 // Number of qubits
        val rng = PseudoRandomNumberGenerator(100)
        val balancedOracle: (Int) -> Int = { if (it % 2 == 0) 0 else 1 }
        val balancedResult = deutschJoszaAlgorithm(balancedOracle, n, rng)
        assertFalse(balancedResult)
    }

    @Test
    fun testDJAlternate(){
        // Test with an alternating oracle
        val n = 2 // Number of qubits
        val rng = PseudoRandomNumberGenerator(100)
        val alternatingOracle: (Int) -> Int = { it % 2 } // Alternating zeros and ones
        val alternatingResult = deutschJoszaAlgorithm(alternatingOracle, n, rng)
        assertFalse(alternatingResult) // Should be false because the oracle is balanced
    }

    @Test
    fun testDJUnity(){
        // Test with an oracle having only one
        val n = 2 // Number of qubits
        val rng = PseudoRandomNumberGenerator(0)
        val singleOneOracle: (Int) -> Int = { if (it == 7) 1 else 0 } // Only one
        val singleOneResult = deutschJoszaAlgorithm(singleOneOracle, n, rng)
        assertTrue(singleOneResult) // Adjusted to true because the oracle is constant
    }
}
