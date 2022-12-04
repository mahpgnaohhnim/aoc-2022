package day04

import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals

internal class Day04Test {
    var inputFile = File(this::class.java.getResource("test1.txt")?.path.orEmpty())

    @Test
    fun getContainingPairSum() {
        val result = Day04.getContainingPairSum(inputFile.readText())
        assertEquals(2, result)

        val resultPart2 = Day04.getContainingPairSum(inputFile.readText(), true)
        assertEquals(4, resultPart2)
    }
}