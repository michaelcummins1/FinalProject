package com.example.finalproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.finalproject.databinding.FragmentPickPersonBinding
import com.example.finalproject.databinding.FragmentViewEventBinding
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController


class ViewEventFragment : Fragment() {

    private var _binding: FragmentViewEventBinding? = null
    private val binding get() = _binding!!

    val viewModel: EventViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentViewEventBinding.inflate(inflater, container, false)
        val rootView = binding.root

        val selectedEvent = viewModel.selectedEvent ?: Event("", "", listOf())

        binding.eventName.text = selectedEvent.title
        binding.eventDate.text = selectedEvent.date

        val myAdapter = ViewEventAdapter(selectedEvent.people, viewModel)
        binding.viewEventRecycler.adapter = myAdapter

        binding.goBackButton1.setOnClickListener{
            rootView.findNavController().navigateUp()
        }

        return rootView
    }

}