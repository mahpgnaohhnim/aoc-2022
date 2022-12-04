package day03

import java.io.File

class Day03 {
    companion object {
        fun getPrioritySum(inputText: String): Int {
            return inputText.lines().map { splitStringInHalf(it) }
                    .sumOf { getItemPriority(it[0], it[1]) }
        }

        fun splitStringInHalf(text: String): List<String> {
            return text.chunked(text.length / 2)
        }

        fun getItemPriority(firstHalf: String, lastHalf: String): Int {
            val item = firstHalf.find { lastHalf.contains(it) }.toString()
            return getValueByItem(item)
        }

        fun getValueByItem(item: String): Int {
            val lowerCaseABC = ('a'..'z').joinToString("")
            val upperCaseABC = ('A'..'Z').joinToString("")
            val possibleValues = "$lowerCaseABC$upperCaseABC"
            return possibleValues.indexOf(item) + 1
        }

        fun getPrioritySumPart2(inputText: String): Int {
            return inputText.lines().chunked(3).sumOf { getItemPriorityPart2(it) }
        }

        fun getItemPriorityPart2(elves: List<String>): Int {
            val item = elves[0].find { elves[1].contains(it) && elves[2].contains(it) }.toString()
            return getValueByItem(item)
        }

    }
}

fun main() {
    val inputFile = File(Day03::class.java.getResource("input.txt")?.path.orEmpty())
    val resultPart1 = Day03.getPrioritySum(inputFile.readText())
    val resultPart2 = Day03.getPrioritySumPart2(inputFile.readText())

    println(
            """
        resultPart1:
        $resultPart1
        
        resultPart2:
        $resultPart2
    """.trimIndent()
    )

}