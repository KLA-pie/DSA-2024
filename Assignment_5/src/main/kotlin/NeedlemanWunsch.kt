package org.example

/**
 * Alignment represents a class intended to compare gene sequence alignment
 * @param [sequence1] The first string of nucleotide sequences to compare
 * @param [sequence2] The second string of nucleotide sequences to compare
 * @param [score] An integer representing the similarity between genes
 */
data class Alignment(val sequence1: String, val sequence2: String, val score: Int)

/**
 * Performs Needleman-Wunsch algorithm for sequence alignment.
 * @param [sequence1] The first string of nucleotide sequences to compare
 * @param [sequence2] The second string of nucleotide sequences to compare
 * @param [gapPenalty] Penalty for introducing a gap in the alignment.
 * @param [matchScore] An integer score for matching nucleotides.
 * @param [mismatchScore] An integer score for mismatching nucleotides.
 * @return An Alignment object containing aligned sequences and their similarity score.
 */
fun needlemanWunsch(sequence1: String, sequence2: String, gapPenalty: Int, matchScore: Int, mismatchScore: Int): Alignment {
    // Calculate the matrix sizes
    val rows = sequence1.length + 1
    val cols = sequence2.length + 1

    // Initialize matrices
    val scoreMatrix = Array(rows) { IntArray(cols) }
    val traceback = Array(rows) { IntArray(cols) }

    // Initialize matrices
    for (i in 0 until rows) {
        scoreMatrix[i][0] = i * gapPenalty
        traceback[i][0] = -1
    }
    for (j in 0 until cols) {
        scoreMatrix[0][j] = j * gapPenalty
        traceback[0][j] = 1
    }

    // Fill the score and traceback matricies
    for (i in 1 until rows) {
        for (j in 1 until cols) {
            // Calculate scores for each of the movement directions
            val match = if (sequence1[i - 1] == sequence2[j - 1]) matchScore else mismatchScore
            val diagonalScore = scoreMatrix[i - 1][j - 1] + match
            val upScore = scoreMatrix[i - 1][j] + gapPenalty
            val leftScore = scoreMatrix[i][j - 1] + gapPenalty

            // Choose the highest score
            val maxScore = maxOf(diagonalScore, upScore, leftScore)
            scoreMatrix[i][j] = maxScore

            // Set the corresponding traceback direction based on the maximum score
            traceback[i][j] = when (maxScore) {
                diagonalScore -> 0
                upScore -> -1
                else -> 1
            }
        }
    }

    // Traceback to the constructed sequence
    var aligned1 = ""
    var aligned2 = ""
    var i = rows - 1
    var j = cols - 1

    while (i > 0 || j > 0) {
        when (traceback[i][j]) {
            0 -> {
                aligned1 = sequence1[i - 1] + aligned1
                aligned2 = sequence2[j - 1] + aligned2
                i--
                j--
            }

            -1 -> {
                aligned1 = sequence1[i - 1] + aligned1
                aligned2 = "-$aligned2"
                i--
            }

            1 -> {
                aligned1 = "-$aligned1"
                aligned2 = sequence2[j - 1] + aligned2
                j--
            }
        }
    }

    // Return the alignment along with its score
    return Alignment(aligned1, aligned2, scoreMatrix[rows - 1][cols - 1])
}
