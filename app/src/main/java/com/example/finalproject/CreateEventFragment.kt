package com.example.finalproject

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.example.finalproject.databinding.FragmentCreateEventBinding

class CreateEventFragment : Fragment() {

    private var _binding: FragmentCreateEventBinding? = null
    private val binding get() = _binding!!

    val peopleInEventList : MutableList<Person> = mutableListOf()

    val viewModel: EventViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateEventBinding.inflate(inflater, container, false)
        val rootView = binding.root


        val myOnClickListener: View.OnClickListener = View.OnClickListener { view ->
            when(view.id){
                R.id.add_person ->  rootView.findNavController().navigate(R.id.action_createEvent_to_pickPersonFragment)
                R.id.create_event_button ->{
                    viewModel.createNewEvent(binding.editTextTitle.text.toString(), binding.calendarView.date, peopleInEventList)
                    rootView.findNavController().navigateUp()
                }
            }
        }

        binding.addPerson.setOnClickListener(myOnClickListener)
        binding.createEventButton.setOnClickListener(myOnClickListener)

        viewModel.selectedPerson.observe(viewLifecycleOwner){
            if(!peopleInEventList.contains(viewModel.selectedPerson.value) && viewModel.selectedPerson.value != null) {
                peopleInEventList.add(viewModel.selectedPerson.value ?: Person("", listOf()))
            }
            else if (viewModel.selectedPerson.value != null){
                Toast.makeText(activity, "This person is already added", Toast.LENGTH_SHORT).show()
            }
            var nameList = ""
            for((index, person) in peopleInEventList.withIndex()){
                if(index != peopleInEventList.size - 1){
                    nameList += "${person.name}, "
                }
                else{
                    nameList += "${person.name}"
                }
            }
            binding.listPeople.text = nameList
        }

        return rootView
    }

}