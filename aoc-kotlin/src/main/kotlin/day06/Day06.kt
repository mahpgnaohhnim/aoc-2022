package day06

import java.io.File

class Day06 {
    companion object {
        fun findStartOfPacket(inputText: String, distinctLength: Int = 4): Int {
            for (i in 0 until inputText.length) {
                val marker = inputText.substring(i, i + distinctLength)
                if (!hasDuplicatedChar(marker)) {
                    return i + distinctLength
                }
            }
            return 6
        }

        fun hasDuplicatedChar(marker: String): Boolean {
            val duplicated = marker.groupingBy { it }.eachCount().count { it.value > 1 }
            return duplicated > 0
        }
    }
}

fun main() {
    val inputFile = File(Day06::class.java.getResource("input.txt")?.path.orEmpty())

    val resultPart1 = Day06.findStartOfPacket(inputFile.readText())
    val resultPart2 = Day06.findStartOfPacket(inputFile.readText(), 14)
    println(
            """
        resultPart1:
        $resultPart1
        
        resultPart2:
        $resultPart2
    """.trimIndent()
    )
}