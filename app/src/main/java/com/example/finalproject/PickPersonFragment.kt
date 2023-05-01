package com.example.finalproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.finalproject.databinding.FragmentCreatePersonBinding
import com.example.finalproject.databinding.FragmentDisplayEventsBinding
import com.example.finalproject.databinding.FragmentPickPersonBinding
import androidx.fragment.app.activityViewModels


class PickPersonFragment : Fragment() {

    private var _binding: FragmentPickPersonBinding? = null
    private val binding get() = _binding!!

    val viewModel: EventViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPickPersonBinding.inflate(inflater, container, false)
        val rootView = binding.root

        val myAdapter = PickPersonAdapter(viewModel.personList, viewModel)
        binding.pickPersonRecycler.adapter = myAdapter

        return rootView
    }
}