fun main() {
    fun part1(input: List<String>): Int {
        fun scoreForShape(shape: Char): Int = shape - 'X' + 1
        fun scoreForRound(theirShape: Char, myShape: Char): Int = when(theirShape to myShape) {
            'B' to 'X', 'C' to 'Y', 'A' to 'Z' -> 0
            'A' to 'X', 'B' to 'Y', 'C' to 'Z' -> 3
            'C' to 'X', 'A' to 'Y', 'B' to 'Z' -> 6
            else -> error("Invalid move choices")
        }
        return input.sumOf { round ->
            val (theirShape, _ , myShape) = round
            scoreForShape(myShape) + scoreForRound(theirShape, myShape)
        }
    }

    fun part2(input: List<String>): Int {
        fun Char.beats() : Char = when(this) {
            'B' -> 'A'
            'C' -> 'B'
            'A' -> 'C'
            else -> error("Invalid shape")
        }
        fun Char.beatenBy() : Char = 'A' + (((this - 'A') + 1) % 3)
        fun scoreForShape(shape: Char): Int = shape - 'A' + 1
        fun chooseMyShape(theirShape: Char, expectedResult: Char): Char = when(expectedResult) {
            'X' -> theirShape.beats()
            'Y' -> theirShape
            'Z' -> theirShape.beatenBy()
            else -> error("invalid shape")
        }
        fun scoreForRound(expectedResult: Char): Int = (expectedResult - 'X') * 3
        return input.sumOf { round ->
            val (theirShape, _ , expectedResult) = round
            val myShape = chooseMyShape(theirShape, expectedResult)
            scoreForShape(myShape) + scoreForRound(expectedResult)
        }
    }

    val input = readInput("Day02_test")
    println(part1(input))
    println(part2(input))
}

private operator fun String.component1(): Char = this[0]
private operator fun String.component2(): Char = this[1]
private operator fun String.component3(): Char = this[2]
