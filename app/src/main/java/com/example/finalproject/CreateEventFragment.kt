package com.example.finalproject

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import com.example.finalproject.databinding.FragmentCreateEventBinding

class CreateEventFragment : Fragment() {

    private var _binding: FragmentCreateEventBinding? = null
    private val binding get() = _binding!!

    val viewModel: EventViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateEventBinding.inflate(inflater, container, false)
        val rootView = binding.root

        val myOnClickListener: View.OnClickListener = View.OnClickListener { view ->
            when(view.id){
                R.id.add_person -> R.id.action_createEvent_to_pickPersonFragment
            }
        }

        binding.addPerson.setOnClickListener(myOnClickListener)
        return rootView
    }
}