package kata.stringcalculator

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class StringCalculatorTests {
    @Test
    fun `add -- empty -- should return 0`() {
        add("") shouldBe 0
    }

    @Test
    fun `add -- single number -- should return it`() {
        add("42") shouldBe 42
    }

    @Test
    fun `add -- two numbers separated by comma -- should return their sum`() {
        add("17,4") shouldBe 21
    }

    @Test
    fun `add -- many numbers separated by comma -- should return their sum`() {
        add("1,2,3,4") shouldBe 10
    }

    @Test
    fun `add -- numbers separated by comma or newline -- should return their sum`() {
        val numbers = """
            1
            2,3
        """.trimIndent()
        add(numbers) shouldBe 6
    }

    @Test
    fun `add -- different delimiters should be supported`() {
        val numbers = """
            //;
            1;2
        """.trimIndent()
        add(numbers) shouldBe 3
    }
}
