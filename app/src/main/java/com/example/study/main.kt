package com.example.study

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation

class main : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v= inflater.inflate(R.layout.fragment_main, container, false)
        val ja=v.findViewById<Button>(R.id.javat)
        val kot=v.findViewById<Button>(R.id.kott)

        ja.setOnClickListener {
            switcher(0)
        }
        kot.setOnClickListener{
            switcher(1)
        }

        return v
    }


    fun switcher(i:Int){
        if (i ==0){
            Navigation.findNavController(requireView()).navigate(R.id.action_main_to_android)

        }
        else{
            Navigation.findNavController(requireView()).navigate(R.id.action_main_to_kotlin)
        }


    }


}