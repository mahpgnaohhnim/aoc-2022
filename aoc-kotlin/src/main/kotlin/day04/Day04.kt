package day04

import java.io.File

class Day04 {
    companion object {
        fun getContainingPairSum(inputText: String): Int {
            return inputText.lines().map { it.split(',') }.filter { isPairContaining(it) }.size
        }

        fun isPairContaining(pair: List<String>): Boolean {
            val range1 = getRangeByAssignment(pair[0])
            val range2 = getRangeByAssignment(pair[1])
            return range1.containsAll(range2) || range2.containsAll(range1)
        }

        fun getRangeByAssignment(elveInput: String): List<Int> {
            val section = elveInput.split('-').map { it.toInt() }
            return (section[0]..section[1]).toList()
        }
    }
}

fun main() {
    val inputFile = File(Day04::class.java.getResource("input.txt")?.path.orEmpty())
    val result1 = Day04.getContainingPairSum(inputFile.readText())

    println(
            """
        result1:
        $result1
    """.trimIndent()
    )
}