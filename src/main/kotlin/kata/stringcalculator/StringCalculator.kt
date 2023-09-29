package kata.stringcalculator

/**
 * See [here](https://osherove.com/tdd-kata-1)
 */
fun add(numbers: String): Int {
    if (numbers.isEmpty()) return 0
    return numbers.lines().sumOf { addLine(",", it) }
}

private fun addLine(delimiter: String, line: String) = line.split(delimiter).sumOf { it.toInt() }
