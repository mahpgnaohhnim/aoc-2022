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

        fun getHighestScenicScore(inputText: String): Int {
            val data = createTreeList(inputText)
            return data.maxOf { it.scenicScore }
        }

        private fun createTreeList(inputText: String): List<Tree> {
            val allTrees = inputText.lines().mapIndexed { index, line -> parseLine(line, index) }.flatten()
            allTrees.forEach { findVisibility(it, allTrees) }
            allTrees.forEach { calculateScenicScore(it, allTrees) }
            return allTrees
        }

        private fun parseLine(line: String, rowIndex: Int): List<Tree> {
            return line.mapIndexed { colIndex, c ->
                Tree(
                        id = "$rowIndex$colIndex",
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

        private fun calculateScenicScore(currentTree: Tree, allTrees: List<Tree>) {
            val colLength = allTrees.maxOf { it.colIndex }
            val rowLength = allTrees.maxOf { it.rowIndex }
            val unblockedTopTree = mutableSetOf<Tree>()
            val unblockedBottomTree = mutableSetOf<Tree>()
            val unblockedRightTree = mutableSetOf<Tree>()
            val unblockedLeftTree = mutableSetOf<Tree>()

            for (i in currentTree.rowIndex downTo 0) {
                val targetTree = allTrees.find { it.rowIndex == i && it.colIndex == currentTree.colIndex } ?: continue
                if (targetTree.id == currentTree.id) {
                    continue
                }
                unblockedTopTree.add(targetTree)
                if (targetTree.height >= currentTree.height) {
                    break
                }
            }

            for (i in currentTree.rowIndex..rowLength) {
                val targetTree = allTrees.find { it.rowIndex == i && it.colIndex == currentTree.colIndex } ?: continue
                if (targetTree.id == currentTree.id) {
                    continue
                }
                unblockedBottomTree.add(targetTree)
                if (targetTree.height >= currentTree.height) {
                    break
                }
            }

            for (i in currentTree.colIndex downTo 0) {
                val targetTree = allTrees.find { it.rowIndex == currentTree.rowIndex && it.colIndex == i } ?: continue
                if (targetTree.id == currentTree.id) {
                    continue
                }
                unblockedLeftTree.add(targetTree)
                if (targetTree.height >= currentTree.height) {
                    break
                }
            }

            for (i in currentTree.colIndex..colLength) {
                val targetTree = allTrees.find { it.rowIndex == currentTree.rowIndex && it.colIndex == i } ?: continue
                if (targetTree.id == currentTree.id) {
                    continue
                }
                unblockedRightTree.add(targetTree)
                if (targetTree.height >= currentTree.height) {
                    break
                }
            }

            currentTree.scenicScore =
                unblockedTopTree.size * unblockedBottomTree.size * unblockedLeftTree.size * unblockedRightTree.size
        }
    }
}

fun main() {
    val resultPart1 = Day08.countVisibleTrees(Day08.inputFile.readText())
    val resultPart2 = Day08.getHighestScenicScore(Day08.inputFile.readText())

    println(
            """
        resultPart1:
        $resultPart1
        
        resultPart2:
        $resultPart2
    """.trimIndent()
    )
}