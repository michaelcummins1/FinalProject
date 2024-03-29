package com.example.finalproject

import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.databinding.FragmentDisplayEventsBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlin.math.sin


class DisplayEventsFragment : Fragment() {

    private var _binding: FragmentDisplayEventsBinding? = null
    private val binding get() = _binding!!

    val viewModel: EventViewModel by activityViewModels()

    lateinit var myAdapter: DisplayFragmentAdapter




    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDisplayEventsBinding.inflate(inflater, container, false)
        val rootView = binding.root

        myAdapter = DisplayFragmentAdapter(listOf(), viewModel)
        binding.displayRecyclerView.adapter = myAdapter

        viewModel.eventList.observe(viewLifecycleOwner){
           myAdapter.notifyDataSetChanged()
        }

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
                    if (viewModel.eventList.value!!.size == 0) {
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
                    } else if (viewModel.eventList.value!!.size > 0) {
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
                R.id.ideas_button -> openWebPage("https://www.goodhousekeeping.com/holidays/gift-ideas/g43163293/most-popular-gifts-2023/")
            }
        }

        binding.personButton.setOnClickListener(myOnClickListener)
        binding.eventButton.setOnClickListener(myOnClickListener)
        binding.clearEventsButton.setOnClickListener(myOnClickListener)
        binding.clearPeopleButton.setOnClickListener(myOnClickListener)
        binding.ideasButton.setOnClickListener(myOnClickListener)

        val dbRef = Firebase.database.reference

        dbRef.addValueEventListener(object : ValueEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val allDBEntries = dataSnapshot.children
                for (allEventEntries in allDBEntries) {
                    for (singleEventEntry in allEventEntries.children) {

                        val nameTest = singleEventEntry.child("name").value
                        val giftsTest = singleEventEntry.child("giftIdeas").value

                        if(nameTest != null && giftsTest != null){
                            val name = singleEventEntry.child("name").value.toString()
                            var gifts = singleEventEntry.child("giftIdeas").value.toString()
                            gifts = gifts.substring(1, gifts.length - 1)
                            val giftList: MutableList<String> = mutableListOf()
                            var i = 0
                            while(i < gifts.length){
                                if(gifts.get(i) == ',' ){
                                    giftList.add(gifts.substring(0, i))
                                    gifts = gifts.substring(i + 2)
                                    i = 0
                                }
                                i++
                            }
                            giftList.add(gifts)
                            val tempPerson = Person(name, giftList)
                            if(!viewModel.personList.contains(tempPerson)){
                                viewModel.personList.add(tempPerson)
                            }
                        }

                        val titleTest = singleEventEntry.child("title").value
                        val dateTest = singleEventEntry.child("date").value
                        val peopleTest = singleEventEntry.child("people").value

                        if(titleTest != null && dateTest != null && peopleTest != null){
                            val title = singleEventEntry.child("title").value.toString()
                            val date = singleEventEntry.child("date").value.toString()
                            var people = singleEventEntry.child("people").value.toString()
                            people = people.substring(1, people.length - 1)
                            val peopleList : MutableList<Int> = mutableListOf()
                            var j = 0
                            while(j < people.length) {
                                if (people.get(j) == ',') {
                                    peopleList.add(people.substring(0, j).toInt())
                                    people = people.substring(j + 2)
                                    j = 0
                                }
                                j++
                            }
                            peopleList.add(people.toInt())
                            val tempEvent = Event(title, date, peopleList)
                            if(!viewModel.eventList.value!!.contains(tempEvent)){
                                viewModel.eventList.value!!.add(tempEvent)
                                viewModel.sortEvents()
                            }
                        }
                        myAdapter = DisplayFragmentAdapter(viewModel.eventList.value!!, viewModel)
                        binding.displayRecyclerView.adapter = myAdapter
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })

        return rootView
    }

    fun openWebPage(url: String) {
        val webpage: Uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        startActivity(intent)
    }
}
