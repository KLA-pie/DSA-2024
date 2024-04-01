package org.example

/**
 * Lemuel-Ziv encoding implementation using an associative array.
 */
class LempelZivEncoder {
    companion object {
        // Initialize the dictionary with single character strings
        private val dictionary = AssociativeArray<String, Int>().apply {
            for (i in 0..255) {
                this[i.toChar().toString()] = i
            }
        }
    }

    /**
     * Encodes the input string using Lempel-Ziv .
     * @param input The input string to be encoded.
     * @return The encoded string.
     */
    fun encode(input: String): String {
        val encodedOutput = StringBuilder()
        var currentPrefix = ""

        for (char in input) {
            val currentSequence = currentPrefix + char
            if (dictionary.contains(currentSequence)) {
                currentPrefix = currentSequence
            } else {
                // Append encoded value of the prefix to the output
                encodedOutput.append("${dictionary[currentPrefix] ?: -1} ")
                // Add the new sequence to the dictionary
                dictionary[currentSequence] = dictionary.size()
                // Reset the prefix to the current character
                currentPrefix = char.toString()
            }
        }

        // Append the encoded value of the last prefix
        if (currentPrefix.isNotEmpty()) {
            encodedOutput.append("${dictionary[currentPrefix] ?: -1}")
        }

        return encodedOutput.toString()
    }
}

fun main() {
    // Test inputs
    val inputs = listOf(
        "ABABCABABCDABCDABDE",
        "MISSISSIPPI",
        "SHESELLSSEASHELLS",
        "ABRACADABRA",
        "THEQUICKBROWNFOXJUMPSOVERTHELAZYDOG"
    )

    // Encode each input string using Lempel-Ziv algorithm
    for ((index, input) in inputs.withIndex()) {
        val encoder = LempelZivEncoder()
        val encoded = encoder.encode(input)
        println("Encoded string $index: $encoded")

        // Calculate compression percentage
        val originalSize = input.length
        val compressedSize = encoded.split(" ").size // Assuming each entry is separated by a space
        val compressionPercentage = (1 - compressedSize.toDouble() / originalSize.toDouble()) * 100
        println("Compression percentage for string $index: $compressionPercentage%")
    }
}