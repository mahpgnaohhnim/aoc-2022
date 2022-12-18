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

    @Test
    fun getHighestScenicScore() {
        val resultPart2 = Day08.getHighestScenicScore(testFile.readText())
        assertEquals(8, resultPart2)
    }

    @Test
    fun maxOfTest() {
        val emptyTreeList = listOf<Tree>()
        val maxVal = emptyTreeList.maxOfOrNull { it.height } ?: 4
        println(maxVal)
    }
}