package com.example.finalproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.finalproject.databinding.FragmentCreatePersonBinding
import com.example.finalproject.databinding.FragmentDisplayEventsBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController


class DisplayEventsFragment : Fragment() {

    private var _binding: FragmentDisplayEventsBinding? = null
    private val binding get() = _binding!!

    val viewModel: EventViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDisplayEventsBinding.inflate(inflater, container, false)
        val rootView = binding.root

        //Just to test if Firebase works will probably have to be used in ViewModel.kt
        lateinit var dbRef : DatabaseReference
        dbRef = Firebase.database.reference

        val myOnClickListener: View.OnClickListener = View.OnClickListener { view ->
            when(view.id){
                R.id.person_button -> {
                    rootView.findNavController().navigate(R.id.action_displayEventsFragment_to_createPersonFragment)
                }
                R.id.event_button -> {
                    rootView.findNavController().navigate(R.id.action_displayEventsFragment_to_createEvent)
                }
                R.id.clear_events_button -> {
                    viewModel.eventList.clear()
                }
                R.id.clear_people_button ->{
                    viewModel.personList.clear()
                }
            }
        }

        binding.personButton.setOnClickListener(myOnClickListener)
        binding.eventButton.setOnClickListener(myOnClickListener)
        binding.clearEventsButton.setOnClickListener(myOnClickListener)
        binding.clearPeopleButton.setOnClickListener(myOnClickListener)

        return rootView
    }
}