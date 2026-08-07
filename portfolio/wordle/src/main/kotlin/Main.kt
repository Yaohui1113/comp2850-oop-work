fun main() {
    val words = readWordList("data/words.txt")
    if (words.isEmpty()) {
        println("No words are available to play.")
        return
    }

    val target = pickRandomWord(words)
    val maximumAttempts = 6

    println("Welcome to Wordle!")
    println("Guess the 5-letter word. You have $maximumAttempts attempts.")

    for (attempt in 1..maximumAttempts) {
        val guess = obtainGuess(attempt).lowercase()
        val matches = evaluateGuess(guess, target.lowercase())
        displayGuess(guess, matches)

        if (guess == target.lowercase()) {
            println("Correct! You guessed the word.")
            return
        }
    }

    println("You have run out of guesses. The word was $target.")
}
