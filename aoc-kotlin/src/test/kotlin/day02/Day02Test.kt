package day02

import day02.enums.GameOption
import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

internal class Day02Test {
    private val testFile1 = File(this.javaClass.getResource("test1.txt")?.path.orEmpty())

    @Test
    fun getMatchPoint() {
        val match1 = GameMatch(GameOption.SCISSOR, GameOption.ROCK)
        val match2 = GameMatch(GameOption.SCISSOR, GameOption.PAPER)
        val match3 = GameMatch(GameOption.PAPER, GameOption.PAPER)

        assertEquals(7, Day02.getMatchPoint(match1))
        assertEquals(2, Day02.getMatchPoint(match2))
        assertEquals(5, Day02.getMatchPoint(match3))
    }

    @Test
    fun getMatchByLine() {
        val line1 = "C X"
        val match1 = Day02.getMatchByLine(line1)
        assertEquals(GameOption.SCISSOR, match1.enemyOption)
        assertEquals(GameOption.ROCK, match1.myOption)

        val line2 = "A Y"
        val match2 = Day02.getMatchByLine(line2)
        assertEquals(GameOption.ROCK, match2.enemyOption)
        assertEquals(GameOption.PAPER, match2.myOption)
    }

    @Test
    fun getGameOptionByInput() {
        val input1 = "X"
        assertEquals(GameOption.ROCK, Day02.getGameOptionByInput(input1))
        assertNotEquals(GameOption.SCISSOR, Day02.getGameOptionByInput(input1))

        val input2 = "B"
        assertEquals(GameOption.PAPER, Day02.getGameOptionByInput(input2))
        assertNotEquals(GameOption.ROCK, Day02.getGameOptionByInput(input2))
    }

    @Test
    fun getGameResult() {
        val result = Day02.getGameResult(testFile1.readText())
        assertEquals(15, result)
    }

}