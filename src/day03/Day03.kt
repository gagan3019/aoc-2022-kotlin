package day03

import readInput

fun main() {
    fun Char.priority(): Int = when (this) {
        in 'a'..'z' -> {
            (this - 'a') + 1
        }

        in 'A'..'Z' -> {
            (this - 'A') + 27
        }

        else -> {
            0
        }
    }

    fun part1(input: List<String>): Int = input.sumOf { line ->
        val (firstHalf, secondHalf) = line.trimEnd()
        val commonItem = firstHalf common secondHalf
        commonItem.priority()
    }

    fun part2(input: List<String>): Int = input.splitInAGroupOf(3).sumOf { group ->
        group.commonItem().priority()
    }

    val inputForPartOne = readInput("day03/Day03_test")
    println(part1(inputForPartOne))
    val inputForPartTwo = readInput("day03/Day03_test_part2")
    println(part2(inputForPartTwo))
}

private fun <E> List<E>.splitInAGroupOf(n: Int): List<List<E>> = this.mapIndexed { index, line ->
    Pair(index / n, line)
}.groupBy({ it.first }) { it.second }.map { it.value }

private operator fun String.component1(): String = substring(0, length / 2)
private operator fun String.component2(): String = substring(length / 2, length)

private infix fun String.common(other: String): Char {
    val map = IntArray(128) { 0 }
    this.toCharArray().forEach { map[it.code]++ }
    return other.first { map[it.code] >= 1 }
}

private fun List<String>.commonItem(): Char {
    val map = IntArray(128) { 0 }
    this.forEachIndexed { index, string ->
        string.toCharArray().forEach {
            if (map[it.code] == index) {
                map[it.code] = index + 1
            }
        }
    }
    return map.indexOfFirst { it == size }.toChar()
}
