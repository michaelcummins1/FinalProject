package com.example.finalproject

import android.icu.text.DateFormat.getDateInstance
import android.icu.text.SimpleDateFormat
import android.net.ParseException
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.finalproject.databinding.FragmentCreateEventBinding
import java.util.*

class CreateEventFragment : Fragment() {

    private var _binding: FragmentCreateEventBinding? = null
    private val binding get() = _binding!!

    val viewModel: EventViewModel by activityViewModels()

    val peopleInEventList: MutableList<Person> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateEventBinding.inflate(inflater, container, false)
        val rootView = binding.root

        val myOnClickListener: View.OnClickListener = View.OnClickListener { view ->
            when (view.id) {
                R.id.add_person -> rootView.findNavController()
                    .navigate(R.id.action_createEvent_to_pickPersonFragment)
                R.id.create_event_button -> {
                    if (binding.editTextTitle.text.toString().equals("")) {
                        Toast.makeText(activity, "Give the event a name", Toast.LENGTH_SHORT).show()
                    } else if (viewModel.selectedDate.value.equals(null)) {
                        Toast.makeText(activity, "Give the event a date", Toast.LENGTH_SHORT).show()
                    } else if (peopleInEventList.size == 0) {
                        Toast.makeText(activity, "Give the event a list of people", Toast.LENGTH_SHORT).show()
                    } else {
                        viewModel.createNewEvent(
                            binding.editTextTitle.text.toString(),
                            viewModel.selectedDate.value ?: "",
                            peopleInEventList
                        )
                        viewModel.selectedDate.value = null
                        viewModel.selectedPerson.value = null
                        rootView.findNavController().navigateUp()
                    }
                }
                R.id.add_date -> rootView.findNavController()
                    .navigate(R.id.action_createEvent_to_pickDateFragment)
            }
        }
        binding.addDate.setOnClickListener(myOnClickListener)
        binding.addPerson.setOnClickListener(myOnClickListener)
        binding.createEventButton.setOnClickListener(myOnClickListener)

        viewModel.selectedPerson.observe(viewLifecycleOwner) {
            if (!peopleInEventList.contains(viewModel.selectedPerson.value) && viewModel.selectedPerson.value != null) {
                peopleInEventList.add(viewModel.selectedPerson.value ?: Person("", listOf()))
            } else if (viewModel.selectedPerson.value != null) {
                Toast.makeText(activity, "This person is already added", Toast.LENGTH_SHORT).show()
            }
            var nameList = ""
            for ((index, person) in peopleInEventList.withIndex()) {
                if (index != peopleInEventList.size - 1) {
                    nameList += "${person.name}, "
                } else {
                    nameList += "${person.name}"
                }
            }
            binding.listPeople.text = nameList
        }

        viewModel.selectedDate.observe(viewLifecycleOwner) {
            binding.displayDate.text = viewModel.selectedDate.value ?: ""
        }

        return rootView
    }

}