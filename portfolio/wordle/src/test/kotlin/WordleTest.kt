import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import java.io.File

@Suppress("unused")
class WordleTest : StringSpec({
    "isValid accepts words containing exactly five letters" {
        isValid("apple") shouldBe true
        isValid("four") shouldBe false
        isValid("letter") shouldBe false
        isValid("ab12e") shouldBe false
    }

    "readWordList reads words from a file" {
        val file = File.createTempFile("wordle", ".txt")
        file.writeText("apple\nbeach\ncrane\n")

        readWordList(file.absolutePath) shouldContainExactly listOf("apple", "beach", "crane")
        file.delete()
    }

    "pickRandomWord returns and removes one word" {
        val words = mutableListOf("apple")

        pickRandomWord(words) shouldBe "apple"
        words.isEmpty() shouldBe true
    }

    "evaluateGuess identifies absent misplaced and correct letters" {
        evaluateGuess("crane", "crane") shouldContainExactly listOf(2, 2, 2, 2, 2)
        evaluateGuess("abcde", "fghij") shouldContainExactly listOf(0, 0, 0, 0, 0)
        evaluateGuess("eabcd", "abcde") shouldContainExactly listOf(1, 1, 1, 1, 1)
    }

    "evaluateGuess handles repeated letters correctly" {
        evaluateGuess("allee", "apple") shouldContainExactly listOf(2, 1, 0, 1, 2)
    }
})
