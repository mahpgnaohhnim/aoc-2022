package day08

import java.io.File

class Day08 {
    companion object {
        val inputFile = File(this::class.java.getResource("input.txt")?.path.orEmpty())

        fun countVisibleTrees(inputText: String): Int {
            val data = createTreeList(inputText)
            val visibleTrees = data.filter { it.visible ?: false }
            return visibleTrees.size
        }

        private fun createTreeList(inputText: String): List<Tree> {
            val allTrees = inputText.lines().mapIndexed { index, line -> parseLine(line, index) }.flatten()
            allTrees.forEach { findVisibility(it, allTrees) }
            return allTrees
        }

        private fun parseLine(line: String, rowIndex: Int): List<Tree> {
            return line.mapIndexed { colIndex, c ->
                Tree(
                        rowIndex = rowIndex,
                        colIndex = colIndex,
                        height = c.digitToInt()
                )
            }
        }

        private fun findVisibility(currentTree: Tree, allTrees: List<Tree>) {
            val leftNeighbors =
                allTrees.filter { it.rowIndex == currentTree.rowIndex && it.colIndex < currentTree.colIndex }
            val rightNeighbors =
                allTrees.filter { it.rowIndex == currentTree.rowIndex && it.colIndex > currentTree.colIndex }
            val neighborsAbove =
                allTrees.filter { it.rowIndex < currentTree.rowIndex && it.colIndex == currentTree.colIndex }
            val neighborsBelow =
                allTrees.filter { it.rowIndex > currentTree.rowIndex && it.colIndex == currentTree.colIndex }

            val visibleAbove = neighborsAbove.isEmpty() || !neighborsAbove.any { it.height >= currentTree.height }
            val visibleBelow = neighborsBelow.isEmpty() || !neighborsBelow.any { it.height >= currentTree.height }
            val visibleFromLeft = leftNeighbors.isEmpty() || !leftNeighbors.any { it.height >= currentTree.height }
            val visibleFromRight = rightNeighbors.isEmpty() || !rightNeighbors.any { it.height >= currentTree.height }
            
            currentTree.visible = visibleAbove || visibleBelow || visibleFromLeft || visibleFromRight
        }
    }
}

fun main() {
    val resultPart1 = Day08.countVisibleTrees(Day08.inputFile.readText())

    println(
            """
        resultPart1:
        $resultPart1
    """.trimIndent()
    )
}