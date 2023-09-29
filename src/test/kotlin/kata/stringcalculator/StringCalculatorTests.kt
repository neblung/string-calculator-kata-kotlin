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
}
