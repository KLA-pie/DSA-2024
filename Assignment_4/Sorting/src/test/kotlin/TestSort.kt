import org.example.mergeSort
import org.example.insertionSort
import org.example.radixSort
import org.example.selectionSort
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class MergeTest {

    @Test
    fun test_merge() {
        val array = intArrayOf(38, 27, 43, 3, 9, 82, 10)
        val expected = intArrayOf(3, 9, 10, 27, 38, 43, 82)
        mergeSort(array)
        assertArrayEquals(expected, array)
    }

    @Test
    fun test_empty_merge() {
        val array = intArrayOf()
        val expected = intArrayOf()
        mergeSort(array)
        assertArrayEquals(expected, array)
    }

    @Test
    fun test_single_merge() {
        val array = intArrayOf(69)
        val expected = intArrayOf(69)
        mergeSort(array)
        assertArrayEquals(expected, array)
    }

    @Test
    fun test_sorted_merge() {
        val array = intArrayOf(12, 20, 314, 498, 2006)
        val expected = intArrayOf(12, 20, 314, 498, 2006)
        mergeSort(array)
        assertArrayEquals(expected, array)
    }
}

class InsertionTest {

    @Test
    fun test_insert() {
        val array = intArrayOf(38, 27, 43, 3, 9, 82, 10)
        val expected = intArrayOf(3, 9, 10, 27, 38, 43, 82)
        insertionSort(array)
        assertArrayEquals(expected, array)
    }

    @Test
    fun test_empty_insert() {
        val array = intArrayOf()
        val expected = intArrayOf()
        insertionSort(array)
        assertArrayEquals(expected, array)
    }

    @Test
    fun test_single_insert() {
        val array = intArrayOf(69)
        val expected = intArrayOf(69)
        insertionSort(array)
        assertArrayEquals(expected, array)
    }

    @Test
    fun test_sorted_insert() {
        val array = intArrayOf(12, 20, 314, 498, 2006)
        val expected = intArrayOf(12, 20, 314, 498, 2006)
        insertionSort(array)
        assertArrayEquals(expected, array)
    }
}

class RadixTest {

    @Test
    fun test_radix() {
        val array = intArrayOf(38, 27, 43, 3, 9, 82, 10)
        val expected = intArrayOf(3, 9, 10, 27, 38, 43, 82)
        radixSort(array)
        assertArrayEquals(expected, array)
    }

    @Test
    fun test_empty_radix() {
        val array = intArrayOf()
        val expected = intArrayOf()
        radixSort(array)
        assertArrayEquals(expected, array)
    }

    @Test
    fun test_single_radix() {
        val array = intArrayOf(69)
        val expected = intArrayOf(69)
        radixSort(array)
        assertArrayEquals(expected, array)
    }

    @Test
    fun test_sorted_radix() {
        val array = intArrayOf(12, 20, 314, 498, 2006)
        val expected = intArrayOf(12, 20, 314, 498, 2006)
        radixSort(array)
        assertArrayEquals(expected, array)
    }
}

class SelectionTest {

    @Test
    fun test_select() {
        val array = intArrayOf(38, 27, 43, 3, 9, 82, 10)
        val expected = intArrayOf(3, 9, 10, 27, 38, 43, 82)
        selectionSort(array)
        assertArrayEquals(expected, array)
    }

    @Test
    fun test_empty_select() {
        val array = intArrayOf()
        val expected = intArrayOf()
        selectionSort(array)
        assertArrayEquals(expected, array)
    }

    @Test
    fun test_single_select() {
        val array = intArrayOf(69)
        val expected = intArrayOf(69)
        selectionSort(array)
        assertArrayEquals(expected, array)
    }

    @Test
    fun test_sorted_select() {
        val array = intArrayOf(12, 20, 314, 498, 2006)
        val expected = intArrayOf(12, 20, 314, 498, 2006)
        selectionSort(array)
        assertArrayEquals(expected, array)
    }
}