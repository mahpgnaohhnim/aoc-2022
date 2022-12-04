package day04

import java.io.File

class Day04 {
    companion object {
        fun getContainingPairSum(inputText: String, isPart2: Boolean = false): Int {
            return inputText.lines().map { it.split(',') }.filter {
                when (isPart2) {
                    false -> isPairContainingAll(it)
                    true -> isPairContainingPartly(it)
                }
            }.size
        }

        fun isPairContainingAll(pair: List<String>): Boolean {
            val range1 = getRangeByAssignment(pair[0])
            val range2 = getRangeByAssignment(pair[1])
            return range1.containsAll(range2) || range2.containsAll(range1)
        }

        fun isPairContainingPartly(pair: List<String>): Boolean {
            val range1 = getRangeByAssignment(pair[0])
            val range2 = getRangeByAssignment(pair[1])
            return range1.any(range2::contains) || range2.any(range1::contains)
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
    val result2 = Day04.getContainingPairSum(inputFile.readText(), true)

    println(
            """
        result1:
        $result1
        
        result2:
        $result2
    """.trimIndent()
    )
}