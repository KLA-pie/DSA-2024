package org.example
/**
 * Matrix represents a class intended to be used for matrix multiplication
 * @param [matrix] A 2D array of integers meant for matrix multiplication
 * @property [size] the size of the matrix
 */
class Matrix(val matrix: Array<Array<Int>>) {

    private val size: Int
        get() = matrix.size

    /**
     * multiply does matrix multiplication the classical method
     *
     * @param [secondMatrix] is the matrix we want to multiply the class to
     *
     * @return The matrix multiplication if the dimensions are compatible and null otherwise
     */
    fun multiply(secondMatrix: Matrix): Matrix? {
        if (size != secondMatrix.size || !squareMatrix() || !secondMatrix.squareMatrix()) {
            return null
        }

        val result = Array(size) { Array(size) { 0 } }

        for (i in 0 until size) {
            for (j in 0 until size) {
                var element = 0
                for (k in 0 until size) {
                    element += matrix[i][k] * secondMatrix.matrix[k][j]
                }
                result[i][j] = element
            }
        }

        return Matrix(result)
    }

    /**
     * strassenMultiply Checks to see if the matrix can be multiplied using the Strassen
     * method
     *
     * @param [secondMatrix] is the matrix we want to multiply the class to
     *
     * @return The matrix multiplication if the dimensions are compatible and null otherwise
     */
    fun strassenMultiply(secondMatrix: Matrix): Matrix? {
        if (size != secondMatrix.size || !powerTwo(size) || !secondMatrix.powerTwo(secondMatrix.size)) {
            return null
        }

        return Matrix(strassenCompute(matrix, secondMatrix.matrix))
    }

    /**
     * strassenCompute calculates the matrix product using the Strassen method
     *
     * @param [matrixA] The first matrix multiple
     * @param [matrixB] The matrix we are multiplying by
     *
     * @return The product of the two matrices
     */
    private fun strassenCompute(matrixA: Array<Array<Int>>, matrixB: Array<Array<Int>>): Array<Array<Int>> {
        val n = matrixA.size

        // Return the base case of matrix multiplication
        if (n == 1) {
            return arrayOf(arrayOf(matrixA[0][0] * matrixB[0][0]))
        }

        val result = Array(n) { Array(n) { 0 } }

        val halfLength = n / 2

        // Initialize 8 submatrix arrays to use
        val a11 = Array(halfLength) { Array(halfLength) { 0 } }
        val a12 = Array(halfLength) { Array(halfLength) { 0 } }
        val a21 = Array(halfLength) { Array(halfLength) { 0 } }
        val a22 = Array(halfLength) { Array(halfLength) { 0 } }
        val b11 = Array(halfLength) { Array(halfLength) { 0 } }
        val b12 = Array(halfLength) { Array(halfLength) { 0 } }
        val b21 = Array(halfLength) { Array(halfLength) { 0 } }
        val b22 = Array(halfLength) { Array(halfLength) { 0 } }

        // Divide matrices into submatrices
        for (i in 0 until halfLength) {
            for (j in 0 until halfLength) {
                a11[i][j] = matrixA[i][j]
                a12[i][j] = matrixA[i][j + halfLength]
                a21[i][j] = matrixA[i + halfLength][j]
                a22[i][j] = matrixA[i + halfLength][j + halfLength]

                b11[i][j] = matrixB[i][j]
                b12[i][j] = matrixB[i][j + halfLength]
                b21[i][j] = matrixB[i + halfLength][j]
                b22[i][j] = matrixB[i + halfLength][j + halfLength]
            }
        }

        // Recurse and compute the new submatrix multiplication
        val p1 = strassenCompute(a11, subtractMatrix(b12, b22))
        val p2 = strassenCompute(addMatrix(a11, a12), b22)
        val p3 = strassenCompute(addMatrix(a21, a22), b11)
        val p4 = strassenCompute(a22, subtractMatrix(b21, b11))
        val p5 = strassenCompute(addMatrix(a11, a22), addMatrix(b11, b22))
        val p6 = strassenCompute(subtractMatrix(a12, a22), addMatrix(b21, b22))
        val p7 = strassenCompute(subtractMatrix(a11, a21), addMatrix(b11, b12))

        val c11 = addMatrix(subtractMatrix(addMatrix(p5, p4), p2), p6)
        val c12 = addMatrix(p1, p2)
        val c21 = addMatrix(p3, p4)
        val c22 = subtractMatrix(subtractMatrix(addMatrix(p5, p1), p3), p7)

        // Combine submatrices into results to return
        for (i in 0 until halfLength) {
            for (j in 0 until halfLength) {
                result[i][j] = c11[i][j]
                result[i][j + halfLength] = c12[i][j]
                result[i + halfLength][j] = c21[i][j]
                result[i + halfLength][j + halfLength] = c22[i][j]
            }
        }

        return result
    }

    /**
     * addMatrix computes the addition of two matrices of the same size
     *
     *  @param [matrixA] The first matrix we are adding with
     *  @param [matrixB] The second matrix we are adding with
     *
     * @return The resultant array from the sum of two matrices
     */
    private fun addMatrix(matrixA: Array<Array<Int>>, matrixB: Array<Array<Int>>): Array<Array<Int>> {
        val n = matrixA.size
        val result = Array(n) { Array(n) { 0 } }

        for (i in 0 until n) {
            for (j in 0 until n) {
                result[i][j] = matrixA[i][j] + matrixB[i][j]
            }
        }

        return result
    }

    /**
     * subtractMatrix computes the difference of two matrices of the same size
     * 
     *  @param [minuend] The matrix we are subtracting from
     *  @param [subtractend] The matrix we are subtracting with
     * 
     * @return The resultant array from the difference of two matrices
     */
    private fun subtractMatrix(minuend: Array<Array<Int>>, subtractend: Array<Array<Int>>): Array<Array<Int>> {
        val n = minuend.size
        val result = Array(n) { Array(n) { 0 } }

        for (i in 0 until n) {
            for (j in 0 until n) {
                result[i][j] = minuend[i][j] - subtractend[i][j]
            }
        }

        return result
    }

    /**
     * squareMatrix checks to see if the matrix has the same width as the length
     * 
     * @return A boolean expression for if the matrix dimensions are the same
     */
    private fun squareMatrix(): Boolean {
        return matrix.all { it.size == size }
    }

    /**
     * powerTwo checks to see if the matrix has a length that is a power of two
     * 
     * @param [size] The dimensional length of the matrix
     * 
     * @return A boolean expression for whether the matrix is a power of two
     */
    private fun powerTwo(size: Int): Boolean {
        return size > 0 && (size and (size - 1)) == 0
    }
}