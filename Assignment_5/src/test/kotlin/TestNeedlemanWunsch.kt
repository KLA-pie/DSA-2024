import org.example.needlemanWunsch
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class TestNeedlemanWunsch {

    @Test
    fun exactCopy() {
        val seq1 = "AGTACGCA"
        val seq2 = "AGTACGCA"

        val gapPenalty = -1
        val matchScore = 1
        val mismatchScore = -1

        val alignment = needlemanWunsch(seq1, seq2, gapPenalty, matchScore, mismatchScore)

        assertEquals("AGTACGCA", alignment.seq1)
        assertEquals("AGTACGCA", alignment.seq2)
    }

    @Test
    fun nucleotideMismatch() {
        val seq1 = "AGTACGCA"
        val seq2 = "AGTAAGCA"  // Mismatch at index 4

        val gapPenalty = -1
        val matchScore = 1
        val mismatchScore = -1

        val alignment = needlemanWunsch(seq1, seq2, gapPenalty, matchScore, mismatchScore)

        assertEquals("AGTACGCA", alignment.seq1)
        assertEquals("AGTAAGCA", alignment.seq2)
    }

    @Test
    fun singleGap() {
        val seq1 = "AGTACGCA"
        val seq2 = "ATACCA"  // Gap inserted at the beginning

        val gapPenalty = -2  // Increased gap penalty
        val matchScore = 1
        val mismatchScore = -1

        val alignment = needlemanWunsch(seq1, seq2, gapPenalty, matchScore, mismatchScore)

        assertEquals("AGTACGCA", alignment.seq1)
        assertEquals("A-TAC-CA", alignment.seq2)
    }

    @Test
    fun differentLength() {
        val seq1 = "AGTACGCA"
        val seq2 = "ACGCA"  // Shorter sequence

        val gapPenalty = -1
        val matchScore = 1
        val mismatchScore = -1

        val alignment = needlemanWunsch(seq1, seq2, gapPenalty, matchScore, mismatchScore)

        assertEquals("AGTACGCA", alignment.seq1)
        assertEquals("---ACGCA", alignment.seq2)
    }

    @Test
    fun middleGap() {
        val seq1 = "AGTACGCA"
        val seq2 = "AGT--CGCA" // Gap inserted in the middle

        val gapPenalty = -1
        val matchScore = 1
        val mismatchScore = -1

        val alignment = needlemanWunsch(seq1, seq2, gapPenalty, matchScore, mismatchScore)

        assertEquals("AGT-ACGCA", alignment.seq1)
        assertEquals("AGT--CGCA", alignment.seq2)
    }

    @Test
    fun endGap() {
        val seq1 = "AGTACGCA"
        val seq2 = "AGTACGCA--" // Gap inserted at the end

        val gapPenalty = -1
        val matchScore = 1
        val mismatchScore = -1

        val alignment = needlemanWunsch(seq1, seq2, gapPenalty, matchScore, mismatchScore)

        assertEquals("AGTACGCA--", alignment.seq1)
        assertEquals("AGTACGCA--", alignment.seq2)
    }

    @Test
    fun startGap() {
        val seq1 = "AGTACGCA"
        val seq2 = "--AGTACGCA" // Gap inserted at the beginning

        val gapPenalty = -1
        val matchScore = 1
        val mismatchScore = -1

        val alignment = needlemanWunsch(seq1, seq2, gapPenalty, matchScore, mismatchScore)

        assertEquals("--AGTACGCA", alignment.seq1)
        assertEquals("--AGTACGCA", alignment.seq2)
    }

    @Test
    fun manyGaps() {
        val seq1 = "AGTACGCA"
        val seq2 = "-A--GTC--C--A" // Multiple gaps inserted

        val gapPenalty = -1
        val matchScore = 1
        val mismatchScore = -1

        val alignment = needlemanWunsch(seq1, seq2, gapPenalty, matchScore, mismatchScore)

        assertEquals("-A--GTAC-GC--A", alignment.seq1)
        assertEquals("-A--GT-C--C--A", alignment.seq2)
    }
}
