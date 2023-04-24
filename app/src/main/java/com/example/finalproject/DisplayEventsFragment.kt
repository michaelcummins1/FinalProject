package com.example.finalproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.finalproject.databinding.FragmentCreatePersonBinding
import com.example.finalproject.databinding.FragmentDisplayEventsBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class DisplayEventsFragment : Fragment() {

    private var _binding: FragmentDisplayEventsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDisplayEventsBinding.inflate(inflater, container, false)
        val rootView = binding.root

        //Just to test if Firebase works will probably have to be used in ViewModel.kt
        lateinit var dbRef : DatabaseReference
        dbRef = Firebase.database.reference


        return rootView
    }
}