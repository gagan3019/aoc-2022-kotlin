package day05

import readInput
import java.util.Stack

fun main() {
    fun part1(input: List<String>): String {
        val index = input.indexOfFirst { it.isBlank() || it.isEmpty() }
        val cratesDiagram:List<String> = input.subList(0, index)
        val lastLine = cratesDiagram.last()
        val numberOfCrates = lastLine.trim().last().code - 48
        val crates: MutableList<Stack<Char>> = ArrayList(numberOfCrates)
        repeat(numberOfCrates) {
            crates.add(Stack<Char>())
        }
        for (lineNum in (cratesDiagram.size - 2) downTo  0) {
            val inputLine = cratesDiagram[lineNum]
            inputLine.updateCrates(crates)
        }
        crates.forEachIndexed { index, crate -> println("Crate ${index + 1} has $crate") }
        input.subList(index + 1, input.size).forEach {
            val parts = it.trimEnd().split(" ")
            val nCratesToBeMoved = parts[1].toInt()
            val source = parts[3].toInt()
            val destination = parts[5].toInt()
            val sourceCrate = crates[source - 1]
            val destinationCrate = crates[destination - 1]
            val tempCrate = Stack<Char>()
            repeat(nCratesToBeMoved) {
                tempCrate.push(sourceCrate.pop())
            }
            repeat(nCratesToBeMoved) {
                destinationCrate.push(tempCrate.pop())
            }
        }
        return crates.joinToString("") { it.peek().toString() }
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    /*val testInput = readInput("DayN_test")
    check(part1(testInput) == 1)*/

    val input = readInput("day05/Day05_test")
    println(part1(input))
    println(part2(input))
}

private fun String.updateCrates(crates: List<Stack<Char>>) {
    var index = 1
    var crateNumber = 0
    while (index < trimEnd().length && crateNumber < crates.size) {
        val crate = crates[crateNumber]
        if (this[index] != ' ') {
            crate.push(this[index])
        }
        index += 4
        crateNumber++
    }
}