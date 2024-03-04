import org.example.Matrix
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class MatrixTest {

    @Test
    fun standardMultiply() {
        val matrixA = Matrix(
            arrayOf(
                arrayOf(1, 2, 3),
                arrayOf(4, 5, 6),
                arrayOf(7, 8, 9)
            )
        )

        val matrixB = Matrix(
            arrayOf(
                arrayOf(9, 8, 7),
                arrayOf(6, 5, 4),
                arrayOf(3, 2, 1)
            )
        )

        val expected = Matrix(
            arrayOf(
                arrayOf(30, 24, 18),
                arrayOf(84, 69, 54),
                arrayOf(138, 114, 90)
            )
        )

        assertTrue(areMatricesEqual(expected.matrix, matrixA.multiply(matrixB)?.matrix))
    }

    @Test
    fun strassenMultiply() {
        val matrixA = Matrix(
            arrayOf(
                arrayOf(1, 2, 3, 4),
                arrayOf(5, 6, 7, 8),
                arrayOf(9, 10, 11, 12),
                arrayOf(13, 14, 15, 16)
            )
        )

        val matrixB = Matrix(
            arrayOf(
                arrayOf(1, 2, 3, 4),
                arrayOf(5, 6, 7, 8),
                arrayOf(9, 10, 11, 12),
                arrayOf(13, 14, 15, 16)
            )
        )

        val expected = Matrix(
            arrayOf(
                arrayOf(90, 100, 110, 120),
                arrayOf(202, 228, 254, 280),
                arrayOf(314, 356, 398, 440),
                arrayOf(426, 484, 542, 600)
            )
        )

        assertTrue(areMatricesEqual(expected.matrix, matrixA.strassenMultiply(matrixB)?.matrix))
    }

    private fun areMatricesEqual(matrixA: Array<Array<Int>>, matrixB: Array<Array<Int>>?): Boolean {
        if (matrixB == null || matrixA.size != matrixB.size || matrixA[0].size != matrixB[0].size) {
            return false
        }

        for (i in matrixA.indices) {
            for (j in matrixA[i].indices) {
                if (matrixA[i][j] != matrixB[i][j]) {
                    return false
                }
            }
        }

        return true
    }
}
