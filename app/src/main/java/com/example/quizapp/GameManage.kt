package com.example.quizapp

import kotlin.random.Random

class GameManage {
    private var letterUsed: String = ""
    private lateinit var underscoreWord: String
    private lateinit var wordToGuess: String
    private var maxTries = 5
    private var currentTries = 0

    private fun getGameState(): StateOfGame{
        if (underscoreWord.equals(wordToGuess, true)){
            return StateOfGame.Won(wordToGuess)
        }
        if (currentTries == maxTries){
            return StateOfGame.Lost(wordToGuess)
        }
        return StateOfGame.Running(letterUsed, underscoreWord)
    }

    fun generateUnderscores(word: String){
        val sb = java.lang.StringBuilder()
        word.forEach { char ->
            if (char == '/'){
                sb.append('/')
            } else {
                sb.append('_')
            }
        }
        underscoreWord = sb.toString()
    }

    fun startGame(): StateOfGame{
        letterUsed = ""
        currentTries = 0
        val randmIndex = Random.nextInt(0, ListOfWords().words.size)
        wordToGuess = ListOfWords().words[randmIndex]
        generateUnderscores(wordToGuess)
        return getGameState()
    }

    fun plays(letter: Char): StateOfGame {
        if (letterUsed.contains(letter)){
            return StateOfGame.Running(letterUsed, underscoreWord)
        }
        letterUsed += letter
        val indexes = mutableListOf<Int>()

        wordToGuess.forEachIndexed { index, char ->
            if (char.equals(letter, true)){
                indexes.add(index)
            }
        }

        var finalUnderscoreWord = "" + underscoreWord
        indexes.forEach { index ->
            val sb = java.lang.StringBuilder(finalUnderscoreWord).also { it.setCharAt(index, letter) }
            finalUnderscoreWord = sb.toString()
        }
        if (indexes.isEmpty()) {
            currentTries++
        }
        underscoreWord = finalUnderscoreWord
        return getGameState()
    }
}