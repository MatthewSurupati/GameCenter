package com.example.quizapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.quizapp.databinding.FragmentChooseGameBinding
import com.example.quizapp.databinding.FragmentResultPageBinding

class Result_Page : Fragment() {

    private lateinit var binding: FragmentResultPageBinding
    private var rightAnswerCount: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResultPageBinding.inflate(inflater, container, false)
        val view = binding.root
        val args = arguments
        if (args != null) {
            rightAnswerCount = args.getInt(ARG_RIGHT_ANSWER_COUNT)
        }
        binding.resultLabel.text = "Your score is: $rightAnswerCount"
        binding.tryAgainBtn.setOnClickListener {
            val quizFragment = startScreen()
            val transaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.startScreen, quizFragment)
            transaction.commit()
        }
        // Inflate the layout for this fragment
        return view
    }
    companion object {
        private const val ARG_RIGHT_ANSWER_COUNT = "right_answer_count"

        fun newInstance(rightAnswerCount: Int): Result_Page {
            val args = Bundle().apply {
                putInt(ARG_RIGHT_ANSWER_COUNT, rightAnswerCount)
            }
            val fragment = Result_Page()
            fragment.arguments = args
            return fragment
        }
    }
}