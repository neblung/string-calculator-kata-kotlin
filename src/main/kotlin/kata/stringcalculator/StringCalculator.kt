package kata.stringcalculator

/**
 * See [here](https://osherove.com/tdd-kata-1)
 */
fun add(numbers: String): Int {
    return if (numbers.isEmpty()) {
        0
    } else if (numbers.contains(",")) {
        numbers.substringBefore(",").toInt() + numbers.substringAfter(",").toInt()
    } else {
        numbers.toInt()
    }
}
