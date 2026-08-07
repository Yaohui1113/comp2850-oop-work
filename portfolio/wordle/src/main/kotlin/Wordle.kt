import java.io.File

fun isValid(word: String): Boolean = word.length == 5 && word.all { it.isLetter() }

fun readWordList(filename: String): MutableList<String> = File(filename).readLines().toMutableList()

fun pickRandomWord(words: MutableList<String>): String {
    require(words.isNotEmpty()) { "The word list must not be empty" }
    val index = words.indices.random()
    return words.removeAt(index)
}

fun obtainGuess(attempt: Int): String {
    while (true) {
        print("Attempt $attempt: ")
        val guess = readln().trim()
        if (isValid(guess)) {
            return guess
        }
        println("Please enter a 5-letter word.")
    }
}

fun evaluateGuess(guess: String, target: String): List<Int> {
    require(guess.length == target.length) { "Guess and target must have the same length" }

    val matches = MutableList(guess.length) { 0 }
    val remaining = target.toMutableList()

    for (index in guess.indices) {
        if (guess[index] == target[index]) {
            matches[index] = 2
            remaining[index] = '\u0000'
        }
    }

    for (index in guess.indices) {
        if (matches[index] == 0) {
            val targetIndex = remaining.indexOf(guess[index])
            if (targetIndex != -1) {
                matches[index] = 1
                remaining[targetIndex] = '\u0000'
            }
        }
    }

    return matches
}

fun displayGuess(guess: String, matches: List<Int>) {
    require(guess.length == matches.size) { "Guess and matches must have the same length" }

    val reset = "\u001B[0m"
    val yellow = "\u001B[33m"
    val green = "\u001B[32m"

    guess.forEachIndexed { index, letter ->
        when (matches[index]) {
            2 -> print("$green$letter$reset")
            1 -> print("$yellow$letter$reset")
            else -> print(letter)
        }
    }
    println()
}
