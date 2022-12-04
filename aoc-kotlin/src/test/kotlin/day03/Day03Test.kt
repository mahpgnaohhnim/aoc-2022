package day03

import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals

internal class Day03Test {
    var inputFile = File(this::class.java.getResource("test1.txt")?.path.orEmpty())

    @Test
    fun getPrioritySum() {
        val result = Day03.getPrioritySum(inputFile.readText())
        assertEquals(157, result)

        val result2 = Day03.getPrioritySumPart2(inputFile.readText())
        assertEquals(70, result2)
    }
}