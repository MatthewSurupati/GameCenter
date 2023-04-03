package com.example.quizapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children

class Hangman : Fragment() {

    private val gameManager = GameManage()
    private lateinit var letterUsedTextView: TextView
    private lateinit var wordTextView: TextView
    private lateinit var gameLostTextView: TextView
    private lateinit var gameWonTextView: TextView
    private lateinit var newGameButton: Button
    private lateinit var lettersLayout: ConstraintLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewRoot = inflater.inflate(R.layout.fragment_hangman, container, false)
        wordTextView = viewRoot.findViewById(R.id.WordView)
        letterUsedTextView = viewRoot.findViewById(R.id.lettersUsedTextView)
        gameLostTextView = viewRoot.findViewById(R.id.LostText)
        gameWonTextView = viewRoot.findViewById(R.id.TextWin)
        newGameButton = viewRoot.findViewById(R.id.NextButton)
        lettersLayout = viewRoot.findViewById(R.id.LetterLayout)

        newGameButton.setOnClickListener {
            startNewGame()
        }
        val gameState = gameManager.startGame()
        updateUI(gameState)

        lettersLayout.children.forEach {letterView ->
            if (letterView is TextView){
                letterView.setOnClickListener {
                    val gameState = gameManager.plays((letterView).text[0])
                    updateUI(gameState)
                    letterView.visibility = View.GONE
                }
            }
        }
        // Inflate the layout for this fragment
        return viewRoot
    }

    private fun showGameLost(wordToGuess: String){
        wordTextView.text = wordToGuess
        gameLostTextView.visibility = View.VISIBLE
        lettersLayout.visibility = View.GONE
    }

    private fun showGameWon(wordToGuess: String){
        wordTextView.text = wordToGuess
        gameWonTextView.visibility = View.VISIBLE
        lettersLayout.visibility = View.GONE
    }

    private fun updateUI(gameState: StateOfGame){
        when(gameState){
            is StateOfGame.Lost -> showGameLost(gameState.wordToGuess)
            is StateOfGame.Running -> {
                wordTextView.text = gameState.underscoreWord
                letterUsedTextView.text = "Letters used: ${gameState.letter}"
            }
            is StateOfGame.Won -> showGameWon(gameState.wordToGuess)
        }
    }

    private fun startNewGame(){
        gameLostTextView.visibility = View.GONE
        gameWonTextView.visibility = View.GONE
        val gameState = gameManager.startGame()
        lettersLayout.visibility = View.VISIBLE
        lettersLayout.children.forEach { letterView ->
            letterView.visibility = View.VISIBLE
        }
        updateUI(gameState)
    }
}