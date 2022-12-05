package day01

import java.io.File

fun main() {
    fun parseInput(input: String) = input.split("\n\n").map { elf ->
        elf.split("\n").map { it.toInt() }
    }

    fun List<List<Int>>.topNElves(n: Int) = map { it.sum() }
        .sortedDescending()
        .take(n)
        .sum()

    fun part1(input: String): Int = parseInput(input).topNElves(1)

    fun part2(input: String): Int = parseInput(input).topNElves(3)

    val testInput = File("src/Day01_test.txt").readText()
    val result = part2(testInput)
    println(result)
}
