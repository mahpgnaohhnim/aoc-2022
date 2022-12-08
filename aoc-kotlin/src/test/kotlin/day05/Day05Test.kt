package day05

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.File

internal class Day05Test{
    var inputFile = File(this::class.java.getResource("test1.txt")?.path.orEmpty())

    @Test
    fun getResultText() {
        val result = Day05.getResultText(inputFile.readText())
        assertEquals("CMZ", result)
    }
}
