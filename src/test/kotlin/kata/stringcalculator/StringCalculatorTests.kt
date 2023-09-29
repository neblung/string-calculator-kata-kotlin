package kata.stringcalculator

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.kotest.matchers.string.shouldNotContain
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

    @Test
    fun `add -- negative numbers -- should be rejected`() {
        val thrown = shouldThrow<RuntimeException> {
            add("1,-2,3,-4")
        }
        listOf("negatives not allowed", "-2", "-4").forEach { thrown.message shouldContain it }
        listOf("1", "3").forEach { thrown.message shouldNotContain it }
    }

    @Test
    fun `add -- numbers bigger than 1000 should be ignored`() {
        add("1000,17") shouldBe 1017
        add("1001,17") shouldBe 17
    }

    @Test
    fun `delimiters can be of any length when set in brackets`() {
        val numbers = """
            //[***]
            1***2***3
        """.trimIndent()
        add(numbers) shouldBe 6
    }
}
