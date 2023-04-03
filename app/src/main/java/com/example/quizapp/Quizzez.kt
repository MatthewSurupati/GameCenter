package com.example.quizapp

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.quizapp.databinding.FragmentQuizzezBinding
import com.example.quizapp.databinding.FragmentResultPageBinding

class Quizzez : Fragment() {

    private lateinit var binding: FragmentQuizzezBinding
    private var rightAnswer: String? = null
    private var rightAnswerCount = 0
    private var quizCount = 1
    private val Quiz_Count = 10

    private val QuizQuestion = mutableListOf(
        mutableListOf("Sharks are mammals", "False", "True"),
        mutableListOf("Sea otters have a favorite rock they use to break open food", "True", "False"),
        mutableListOf("The blue whale is the biggest animal to have ever lived", "True", "False"),
        mutableListOf("The hummingbird egg is the world's smallest bird egg", "True", "False"),
        mutableListOf("Pigs roll in the mud because they don’t like being clean", "False", "True"),
        mutableListOf("Bats are blind", "False", "True"),
        mutableListOf("A dog sweats by panting its tongue", "False", "True"),
        mutableListOf("It takes a sloth two weeks to digest a meal", "True", "False"),
        mutableListOf("The largest living frog is the Goliath frog of West Africa", "True", "False"),
        mutableListOf("An ant can lift 1,000 times its body weight", "False", "True"),
        mutableListOf("When exiting a cave, bats always go in the direction of the wind", "False", "True"),
        mutableListOf("Galapagos tortoises sleep up to 16 hours a day", "True", "False"),
        mutableListOf("An octopus has seven hearts", "False", "True"),
        mutableListOf("The goat is the national animal of Scotland", "False", "True"),
        mutableListOf("Herbivores are animal eaters", "False", "True"),
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuizzezBinding.inflate(inflater, container, false)
        binding.AnswerButtonOne.setOnClickListener { view ->
            checkAnswer(view)
        }
        binding.AnswerButtonTwo.setOnClickListener { view ->
            checkAnswer(view)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        QuizQuestion.shuffle()
        showNext()
    }

    fun showNext(){
        binding.countLabel.text = getString(R.string.count_label, quizCount)

        //pick one of set quiz
        val quiz = QuizQuestion[0]

        //set question and answer
        binding.question.text = quiz[0]
        rightAnswer = quiz[1]

        binding.AnswerButtonOne.text = quiz[1]
        binding.AnswerButtonTwo.text = quiz[2]

        QuizQuestion.removeAt(0)

    }

    fun checkAnswer(view: View){
        val answerBtn: Button = view.findViewById(view.id)
        val btnText =  answerBtn.text.toString()

        val alertTitle: String
        if (btnText == rightAnswer){
//            corret
            alertTitle = "Correct!!"
            rightAnswerCount++
        } else{
            alertTitle = "Wrong!!"
        }
        AlertDialog.Builder(requireContext())
            .setTitle(alertTitle)
            .setMessage("Answer: $rightAnswer")
            .setPositiveButton("Ok"){ dialogInterface, i ->
                checkQuizCount()
            }
            .setCancelable(false)
            .show()
    }

    fun checkQuizCount() {
        if (quizCount == Quiz_Count) {
            startResultFragment(rightAnswerCount)
        } else{
            quizCount++
            showNext()
        }
    }
    fun startResultFragment(rightAnswerCount: Int) {
        val resultFragment = Result_Page.newInstance(rightAnswerCount)
        val transaction = requireFragmentManager().beginTransaction()
        transaction.replace(R.id.startScreen, resultFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}