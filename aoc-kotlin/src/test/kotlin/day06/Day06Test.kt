package day06

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

internal class Day06Test {
    var inputFile1 = File(this::class.java.getResource("test1.txt")?.path.orEmpty())
    var inputFile2 = File(this::class.java.getResource("test2.txt")?.path.orEmpty())
    var inputFile3 = File(this::class.java.getResource("test3.txt")?.path.orEmpty())
    var inputFile4 = File(this::class.java.getResource("test4.txt")?.path.orEmpty())
    var inputFile5 = File(this::class.java.getResource("test5.txt")?.path.orEmpty())

    @Test
    fun findStartOfPacketPart1() {
        val result1 = Day06.findStartOfPacket(inputFile1.readText())
        val result2 = Day06.findStartOfPacket(inputFile2.readText())
        val result3 = Day06.findStartOfPacket(inputFile3.readText())
        val result4 = Day06.findStartOfPacket(inputFile4.readText())
        val result5 = Day06.findStartOfPacket(inputFile5.readText())

        assertEquals(7, result1)
        assertEquals(5, result2)
        assertEquals(6, result3)
        assertEquals(10, result4)
        assertEquals(11, result5)
    }

    @Test
    fun findStartOfPacketPart2() {
        val result1 = Day06.findStartOfPacket(inputFile1.readText(), 14)
        val result2 = Day06.findStartOfPacket(inputFile2.readText(), 14)
        val result3 = Day06.findStartOfPacket(inputFile3.readText(), 14)
        val result4 = Day06.findStartOfPacket(inputFile4.readText(), 14)
        val result5 = Day06.findStartOfPacket(inputFile5.readText(), 14)

        assertEquals(19, result1)
        assertEquals(23, result2)
        assertEquals(23, result3)
        assertEquals(29, result4)
        assertEquals(26, result5)
    }
}