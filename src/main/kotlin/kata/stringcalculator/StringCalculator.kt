package kata.stringcalculator

/**
 * See [here](https://osherove.com/tdd-kata-1)
 */
fun add(string: String): Int {
    if (string.isEmpty()) return 0
    return with(getNumbers(string)) {
        requireContainsNoNegatives()
        filter { it <= 1000 }.sum()
    }
}

private fun getNumbers(string: String): List<Int> {
    val lines = string.lines()
    return if (lines[0].startsWith("//")) {
        val delimiters = delimiters(lines[0].drop(2))
        numbersOfLines(delimiters, lines.drop(1))
    } else {
        numbersOfLines(listOf(","), lines)
    }
}

private fun delimiters(definition: String): List<String> {
    fun dropBrackets() = definition.drop(1).dropLast(1)
    return if (definition.startsWith("[")) {
        dropBrackets().split("][")
    } else {
        listOf(definition)
    }
}

private fun numbersOfLines(delimiters: List<String>, linesWithNumbers: List<String>): List<Int> =
    linesWithNumbers.flatMap { numbersOfLine(delimiters, it) }

private fun numbersOfLine(delimiters: List<String>, line: String) =
    line.split(*delimiters.toTypedArray()).map { it.toInt() }

private fun List<Int>.requireContainsNoNegatives() {
    val negatives = filter { it < 0 }
    require(negatives.isEmpty()) { "negatives not allowed: $negatives" }
}
