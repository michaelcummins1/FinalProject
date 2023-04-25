package com.example.finalproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.finalproject.databinding.FragmentCreateEventBinding
import com.example.finalproject.databinding.FragmentCreatePersonBinding
import androidx.fragment.app.viewModels
import androidx.fragment.app.activityViewModels

class CreatePersonFragment : Fragment() {

    private var _binding: FragmentCreatePersonBinding? = null
    private val binding get() = _binding!!

    val viewModel: EventViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreatePersonBinding.inflate(inflater, container, false)
        val rootView = binding.root

        return rootView
    }

}