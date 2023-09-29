package kata.stringcalculator

/**
 * See [here](https://osherove.com/tdd-kata-1)
 */
fun add(numbers: String): Int {
    if (numbers.isEmpty()) return 0
    val lines = numbers.lines()
    return if (lines[0].startsWith("//")) {
        val delimiter = lines[0].drop(2)
        addLines(delimiter, lines.drop(1))
    } else {
        addLines(",", lines)
    }
}

private fun addLines(delimiter: String, linesWithNumbers: List<String>): Int =
    linesWithNumbers.sumOf { addLine(delimiter, it) }

private fun addLine(delimiter: String, line: String) = line.split(delimiter).sumOf { it.toInt() }
