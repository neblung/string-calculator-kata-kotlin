package kata.stringcalculator

/**
 * See [here](https://osherove.com/tdd-kata-1)
 */
fun add(string: String): Int {
    if (string.isEmpty()) return 0
    val lines = string.lines()
    val numbers = if (lines[0].startsWith("//")) {
        val delimiter = delimiter(lines[0].drop(2))
        numbersOfLines(delimiter, lines.drop(1))
    } else {
        numbersOfLines(",", lines)
    }
    requireContainsNoNegatives(numbers)
    return numbers.filter { it <= 1000 }.sum()
}

private fun delimiter(definition: String): String {
    fun dropBrackets() = definition.drop(1).dropLast(1)
    return if (definition.startsWith("[")) dropBrackets() else definition
}

private fun numbersOfLines(delimiter: String, linesWithNumbers: List<String>): List<Int> =
    linesWithNumbers.flatMap { numbersOfLine(delimiter, it) }

private fun numbersOfLine(delimiter: String, line: String) = line.split(delimiter).map { it.toInt() }

private fun requireContainsNoNegatives(numbers: List<Int>) {
    val negatives = numbers.filter { it < 0 }
    if (negatives.isNotEmpty()) error("negatives not allowed: $negatives")
}
