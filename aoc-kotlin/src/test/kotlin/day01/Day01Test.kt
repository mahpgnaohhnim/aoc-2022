package day01

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File

internal class Day01Test {
    private val file1 = File(this.javaClass.getResource("test1.txt")?.path.orEmpty())
    private val file2 = File(this.javaClass.getResource("test2.txt")?.path.orEmpty())

    @Test
    fun getMostCalories() {
        val resultFile1 = Day01.getMostCalories(file1.readText())
        assertEquals(24000, resultFile1)

        val resultFile2 = Day01.getMostCalories(file2.readText())
        assertEquals(10000, resultFile2)
    }

    @Test
    fun getTopCalories() {
        val resultFile1 = Day01.getTopCalories(file1.readText(),2)
        assertEquals(35000, resultFile1)

        val resultFile2 = Day01.getTopCalories(file2.readText(),3)
        assertEquals(20034,resultFile2 )
    }
}