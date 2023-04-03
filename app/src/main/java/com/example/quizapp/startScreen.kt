package com.example.quizapp

import java.sql.*
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.findNavController

class startScreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_start_screen, container, false)
        val startButton = view.findViewById<Button>(R.id.button_Start)
        val names = view.findViewById<EditText>(R.id.enter_name)

        startButton.setOnClickListener{
            val navControl = view.findNavController()
            val _name = names.text.toString()
            val action = startScreenDirections.actionStartScreenToChooseGame("$_name")
            navControl.navigate(action)
            
        }
        // Inflate the layout for this fragment
        return view
    }
}