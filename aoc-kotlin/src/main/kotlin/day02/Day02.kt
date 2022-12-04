package day02

import day02.enums.GameOption
import java.io.File

class Day02 {

    companion object {

        fun getMatchByLine(line: String): GameMatch {
            val inputLine = line.split(" ")
            if (inputLine.size != 2) {
                throw Exception("Line is invalid")
            }
            val enemyOption = getGameOptionByInput(inputLine[0])
            val ownOption = getGameOptionByInput(inputLine[1])
            return GameMatch(enemyOption, ownOption)
        }

        fun getGameOptionByInput(input: String): GameOption {
            return when (input) {
                "X", "A" -> GameOption.ROCK
                "Y", "B" -> GameOption.PAPER
                "Z", "C" -> GameOption.SCISSOR
                else -> throw Exception("Match Option is not Valid")
            }
        }

        fun getMatchPoint(match: GameMatch): Int {
            var matchPoint = match.myOption.shapeScore()
            when {
                match.myOption.equals(match.enemyOption) -> matchPoint += 3
                match.myOption.winTo().equals(match.enemyOption) -> matchPoint += 6
            }
            return matchPoint
        }

        fun getGameResult(inputFile: String): Int {
            return inputFile.lines().map { getMatchByLine(it) }.map { getMatchPoint(it) }.sum()
        }
    }
}

fun main() {
    val inputFile = File(Day02::class.java.getResource("input.txt")?.path.orEmpty())
    val resultPart1 = Day02.getGameResult(inputFile.readText())
    println(
            """
        resultPart1:
        $resultPart1
    """.trimIndent()
    )
}