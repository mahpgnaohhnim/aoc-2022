package day01

import java.io.File

class Day01 {
    companion object{
        fun getMostCalories(input: String): Int{
            val calories =  input.split("\n\n").map { calories -> calories.lines().map { it.toInt() } }
            return calories.maxOf { it.sum() }
        }

        fun getTopCalories(input: String, top: Int = 1): Int{
            val calories =  input.split("\n\n").map { calories -> calories.lines().map { it.toInt() } }
            return calories.map { it.sum() }
                .sortedDescending()
                .take(top)
                .sum()
        }
    }
}

fun main(){
    val inputFile = File(Day01.javaClass.getResource("input.txt")?.path.orEmpty())

    fun challengePart1(){
        val result = Day01.getMostCalories(inputFile.readText())
        println("""
            Result day 01 part 1:
            $result
            """.trimIndent())
    }

    fun challengePart2(){
        val result =Day01.getTopCalories(inputFile.readText(),3)
        println("""
            Result day 01 part2:
            $result
            """.trimIndent())
    }

    challengePart1()
    challengePart2()
}