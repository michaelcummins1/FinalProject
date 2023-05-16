package com.example.finalproject

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.finalproject.databinding.FragmentDisplayEventsBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlin.math.sin


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

        val dbRef = Firebase.database.reference

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val allDBEntries = dataSnapshot.children

                var numOfEventsAdded = 0

                for (allEventEntries in allDBEntries) {
                    for (singleEventEntry in allEventEntries.children) {
                        numOfEventsAdded++
                        val name = singleEventEntry.child("addedPeople").child("name").getValue().toString()
                        val giftIdeas= singleEventEntry.child("addedPeople").child("giftIdeas").getValue().toString()


                        val title = singleEventEntry.child("addedEvents").child("title").getValue().toString()
                        val date = singleEventEntry.child("addedEvents").child("date").getValue().toString()
                        var people = singleEventEntry.child("addedEvents").child("people").getValue().toString()
                        people = people.substring(1, people.length - 1)
                        val peopleList : MutableList<Int> = mutableListOf()

                        Log.d("DisplayEventsFragment", "people contains: $people")
                        while(people.length > 0){
//                            val num = people.substring(0, people.indexOf(",")).toInt()
//                            peopleList.add(num)
//                            people = people.substring(people.indexOf(","))
                        }

                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })

        val myAdapter = DisplayFragmentAdapter(viewModel.eventList, viewModel)
        binding.displayRecyclerView.adapter = myAdapter

        val myOnClickListener: View.OnClickListener = View.OnClickListener { view ->
            when (view.id) {
                R.id.person_button -> {
                    rootView.findNavController()
                        .navigate(R.id.action_displayEventsFragment_to_createPersonFragment)
                }
                R.id.event_button -> {
                    if (viewModel.personList.size > 0) {
                        rootView.findNavController()
                            .navigate(R.id.action_displayEventsFragment_to_createEvent)
                    } else {
                        Toast.makeText(activity, "Create a person first", Toast.LENGTH_SHORT).show()
                    }
                }
                R.id.clear_events_button -> {
                    if (viewModel.eventList.size == 0) {
                        Toast.makeText(activity, "There are no events to clear", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        MaterialAlertDialogBuilder(requireContext())
                            .setTitle("Confirmation:")
                            .setMessage("Are you sure you want to clear all events?")
                            .setPositiveButton("Yes") { dialog, which ->
                                viewModel.clearEvents()
                                myAdapter.notifyDataSetChanged()
                            }
                            .setNegativeButton("No") { dialog, which ->
                            }
                            .show()
                    }
                }
                R.id.clear_people_button -> {
                    if (viewModel.personList.size == 0) {
                        Toast.makeText(activity, "There are no people to clear", Toast.LENGTH_SHORT)
                            .show()
                    } else if (viewModel.eventList.size > 0) {
                        Toast.makeText(
                            activity,
                            "You must clear events before you can clear people",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
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