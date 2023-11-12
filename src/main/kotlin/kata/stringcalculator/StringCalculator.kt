package kata.stringcalculator

private typealias Delimiters = List<String>
private typealias LinesWithNumbers = List<String>

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
    val (delimiters, linesWithNumbers) = keepApart(string.lines())
    return linesWithNumbers.collectNumbers(delimiters.toTypedArray())
}

private fun keepApart(lines: List<String>): Pair<Delimiters, LinesWithNumbers> =
    if (lines[0].startsWith("//")) delimiters(lines[0].drop(2)) to lines.drop(1) else listOf(",") to lines

private fun delimiters(definition: String): Delimiters {
    return if (definition.startsWith("[")) {
        fun dropBrackets() = definition.drop(1).dropLast(1)
        dropBrackets().split("][")
    } else {
        listOf(definition)
    }
}

private fun LinesWithNumbers.collectNumbers(delimiters: Array<String>): List<Int> =
    flatMap { it.split(*delimiters).map(String::toInt) }

private fun List<Int>.requireContainsNoNegatives() {
    val negatives = filter { it < 0 }
    require(negatives.isEmpty()) { "negatives not allowed: $negatives" }
}
