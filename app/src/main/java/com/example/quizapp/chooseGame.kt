package com.example.quizapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.quizapp.databinding.FragmentChooseGameBinding
import java.util.jar.Attributes.Name

class chooseGame : Fragment() {

    private lateinit var _binding: FragmentChooseGameBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentChooseGameBinding.inflate(inflater, container, false)
        val _name = chooseGameArgs.fromBundle(requireArguments()).Name
        _binding.ViewName.text = "Hello $_name"
//        val viewChoose = inflater.inflate(R.layout.fragment_choose_game, container, false)
//        val hello_name = viewChoose.findViewById<TextView>(R.id.ViewName)
//        val _name = chooseGameArgs.fromBundle(requireArguments()).Name
//        hello_name.text = "Hello $_name"
        val navControl = findNavController()
        _binding.Quiz.setOnClickListener {
            val action = chooseGameDirections.actionChooseGameToQuizzez()
            navControl.navigate(action)
        }
        _binding.Hangman.setOnClickListener {
            val actions = chooseGameDirections.actionChooseGameToHangman()
            navControl.navigate(actions)
        }
        _binding.Abouts.setOnClickListener {
            val actions = chooseGameDirections.actionChooseGameToAbout2()
            navControl.navigate(actions)
        }

        return _binding.root
    }

}