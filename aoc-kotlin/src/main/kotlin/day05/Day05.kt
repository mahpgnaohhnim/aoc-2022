package day05

import java.io.File

class Day05 {
    companion object {
        fun getResultText(inputText: String, part2: Boolean = false): String {
            val parts = inputText.split("\n\n")
            val data = parseData(parts[0])
            executeCommands(parts[1], data, part2)
            return data.map {
                when {
                    it.isEmpty() -> ""
                    else -> it.last()
                }
            }.joinToString("")
        }

        fun parseData(input: String): List<MutableList<String>> {
            val lines = input.lines().asReversed().toMutableList()
            val data = getEmptyDataByBaseLine(lines[0])
            lines.removeFirst()
            lines.map { parseDateLine(it, data) }
            return data
        }

        fun parseDateLine(line: String, data: List<MutableList<String>>) {
            val pattern = "(\\[)|(])".toRegex()
            val chunks = line.chunked(4).map { it.replace(pattern, "").trim() }
            chunks.forEachIndexed { index, chunk ->
                val dataGroup = data[index]
                if (chunk.isNotBlank()) {
                    dataGroup.add(chunk)
                }
            }
        }

        fun getEmptyDataByBaseLine(baseLine: String): List<MutableList<String>> {
            val chunks = baseLine.chunked(4)
            return chunks.map { mutableListOf() }
        }

        fun executeCommands(commandsPart: String, data: List<MutableList<String>>, part2: Boolean) {
            commandsPart.lines().forEach { line ->
                val match = Regex("move (\\d+) from (\\d+) to (\\d+)").find(line)
                if (match != null) {
                    val (count, fromGroupId, targetGroupId) = match.destructured

                    val fromGroup = data[fromGroupId.toInt() - 1]
                    val targetGroup = data[targetGroupId.toInt() - 1]
                    if (part2) {
                        val groupData = fromGroup.takeLast(count.toInt())
                        targetGroup.addAll(groupData)
                        repeat(count.toInt()) {
                            fromGroup.removeLast()
                        }
                    } else {
                        repeat(count.toInt()) {
                            targetGroup.add(fromGroup.last())
                            fromGroup.removeLast()
                        }
                    }
                }
            }
        }
    }
}

fun main() {
    val inputFile = File(Day05::class.java.getResource("input.txt")?.path.orEmpty())
    val resultPart1 = Day05.getResultText(inputFile.readText())
    val resultPart2 = Day05.getResultText(inputFile.readText(), true)

    println(
            """
        resultPart1:
        $resultPart1
        
        resultPart2:
        $resultPart2
    """.trimIndent()
    )
}
