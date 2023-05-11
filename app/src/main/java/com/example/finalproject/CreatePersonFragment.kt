package com.example.finalproject

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.finalproject.databinding.FragmentCreateEventBinding
import com.example.finalproject.databinding.FragmentCreatePersonBinding
import androidx.fragment.app.viewModels
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController

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



        val myOnClickListener = View.OnClickListener { view ->
            when(view.id){
                R.id.create_button ->{
                    if(binding.editTextName.text.toString().equals("")){
                        Toast.makeText(activity, "Give the person a name", Toast.LENGTH_SHORT).show()
                    }
                    else if(binding.editTextGift.text.toString().equals("")){
                        Toast.makeText(activity, "Add gift ideas", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        val personName = binding.editTextName.text.toString()
                        val giftList = binding.editTextGift.text.toString()
                        viewModel.createNewPerson(personName, giftList)
                        rootView.findNavController().navigateUp()
                    }
                }
            }
        }

        binding.createButton.setOnClickListener(myOnClickListener)

        return rootView
    }

}