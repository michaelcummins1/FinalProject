package com.example.finalproject

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.finalproject.databinding.FragmentDisplayEventsBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


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

        val myAdapter = DisplayFragmentAdapter(viewModel.eventList, viewModel)
        binding.displayRecyclerView.adapter = myAdapter

        val myOnClickListener: View.OnClickListener = View.OnClickListener { view ->
            when(view.id){
                R.id.person_button -> {
                    rootView.findNavController().navigate(R.id.action_displayEventsFragment_to_createPersonFragment)
                }
                R.id.event_button -> {
                    if(viewModel.personList.size > 0){
                        rootView.findNavController().navigate(R.id.action_displayEventsFragment_to_createEvent)
                    }
                    else{
                        Toast.makeText(activity, "Create a person first", Toast.LENGTH_SHORT).show()
                    }
                }
                R.id.clear_events_button -> {
                    if(viewModel.eventList.size == 0){
                        Toast.makeText(activity, "There are no events to clear", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        MaterialAlertDialogBuilder(requireContext())
                            .setTitle("Confirmation:")
                            .setMessage("Are you sure you want to clear all events?")
                            .setPositiveButton("Yes") { dialog, which ->
                                viewModel.clearEvents()
                                binding.displayRecyclerView.adapter = myAdapter
                            }
                            .setNegativeButton("No") { dialog, which ->
                            }
                            .show()
                    }
                }
                R.id.clear_people_button ->{
                    if(viewModel.personList.size == 0){
                        Toast.makeText(activity, "There are no people to clear", Toast.LENGTH_SHORT).show()
                    }
                    else if(viewModel.eventList.size > 0){
                        Toast.makeText(activity, "You must clear events before you can clear people", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        MaterialAlertDialogBuilder(requireContext())
                            .setTitle("Confirmation:")
                            .setMessage("Are you sure you want to clear all people?")
                            .setPositiveButton("Yes") { dialog, which ->
                                viewModel.clearPeople()
                            }
                            .setNegativeButton("No") { dialog, which ->
                            }
                            .show()
                    }
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