package com.example.quizapp

import android.app.GameState

sealed class StateOfGame {
    class Running(
        val letter: String,
        val underscoreWord: String,
    ) : StateOfGame()
    class Lost(
        val wordToGuess: String
    ) : StateOfGame()
    class Won(
        val wordToGuess: String
    ) : StateOfGame()
}