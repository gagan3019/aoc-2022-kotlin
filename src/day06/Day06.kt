import java.io.File

fun main() {
    fun startOfMessageMarker(input: String, packetSize: Int): Int {
        for ((index, ch) in input.toCharArray().withIndex()) {
            if (index >= packetSize - 1 && input.substring(0, index + 1).lastN(packetSize).allUnique()) {
                return index + 1
            }
        }
        return -1
    }

    // test if implementation meets criteria from the description, like:
    val testInput = File("src/day06/Day06_test.txt").readText()
    println(startOfMessageMarker(testInput, 4))
    println(startOfMessageMarker(testInput, 14))
}

private fun String.allUnique(): Boolean = this.toCharArray().toSet().size == length
private fun String.lastN(n: Int): String = substring(length - n)