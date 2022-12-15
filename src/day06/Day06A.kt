import java.io.File

fun main() {
    val testInput = File("src/day06/Day06_test.txt").readText()
    println(testInput.findFirstStartMarker(4))
    println(testInput.findFirstStartMarker(14))
}

private fun String.findFirstStartMarker(startMarkerSize: Int): Int = withIndex()
    .windowed(startMarkerSize, 1)
    .first { window -> window.map { it.value }.toSet().size == startMarkerSize}
    .last().index + 1