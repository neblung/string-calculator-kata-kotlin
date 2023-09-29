package kata.stringcalculator

/**
 * See [here](https://osherove.com/tdd-kata-1)
 */
fun add(string: String): Int {
    if (string.isEmpty()) return 0
    val lines = string.lines()
    val numbers = if (lines[0].startsWith("//")) {
        val delimiters = delimiters(lines[0].drop(2))
        numbersOfLines(delimiters, lines.drop(1))
    } else {
        numbersOfLines(listOf(","), lines)
    }
    requireContainsNoNegatives(numbers)
    return numbers.filter { it <= 1000 }.sum()
}

private fun delimiters(definition: String): List<String> {
    fun dropBrackets() = definition.drop(1).dropLast(1)
    return listOf(if (definition.startsWith("[")) dropBrackets() else definition)
}

private fun numbersOfLines(delimiters: List<String>, linesWithNumbers: List<String>): List<Int> =
    linesWithNumbers.flatMap { numbersOfLine(delimiters, it) }

private fun numbersOfLine(delimiters: List<String>, line: String) =
    line.split(*delimiters.toTypedArray()).map { it.toInt() }

private fun requireContainsNoNegatives(numbers: List<Int>) {
    val negatives = numbers.filter { it < 0 }
    if (negatives.isNotEmpty()) error("negatives not allowed: $negatives")
}
