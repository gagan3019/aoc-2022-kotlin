package day04

import readInput

fun main() {
    fun List<String>.ranges() = map {
            val (firstElf, secondElf) = it.split(",")
            Pair(firstElf.toRange(), secondElf.toRange())
        }

    fun part1(input: List<String>): Int =
        input.ranges().count { it.first in it.second || it.second in it.first }

    fun part2(input: List<String>): Int =
        input.ranges().count { it.first overlaps it.second || it.second overlaps it.first }

    val input = readInput("day04/Day04_test")
    //val input = readInput("day04/Day04")
    println(part1(input))
    println(part2(input))
}

private fun String.toRange(): IntRange {
    val (start, end) = this.split("-")
    return IntRange(start.toInt(), end.toInt())
}

private operator fun IntRange.contains(other: IntRange): Boolean = other.first in this && other.last in this
private infix fun IntRange.overlaps(other: IntRange): Boolean = other.first in this || other.last in this