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
    val (delimiters, linesWithNumbers) = if (lines[0].startsWith("//")) {
        delimiters(lines[0].drop(2)) to lines.drop(1)
    } else {
        listOf(",") to lines
    }
    return linesWithNumbers.collectNumbers(delimiters.toTypedArray())
}

private fun delimiters(definition: String): List<String> {
    return if (definition.startsWith("[")) {
        fun dropBrackets() = definition.drop(1).dropLast(1)
        dropBrackets().split("][")
    } else {
        listOf(definition)
    }
}

private fun List<String>.collectNumbers(delimiters: Array<String>): List<Int> =
    flatMap { it.split(*delimiters).map(String::toInt) }

private fun List<Int>.requireContainsNoNegatives() {
    val negatives = filter { it < 0 }
    require(negatives.isEmpty()) { "negatives not allowed: $negatives" }
}
