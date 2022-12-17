package day08


import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals

internal class Day08Test {
    val testFile = File(this::class.java.getResource("test.txt")?.path.orEmpty())

    @Test
    fun countVisibleTrees() {
        val resultPart1 = Day08.countVisibleTrees(testFile.readText())
        assertEquals(21, resultPart1)
    }
}