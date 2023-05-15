package com.example.finalproject

import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.databinding.ViewEventItemLayoutBinding

class ViewEventViewHolder(val binding: ViewEventItemLayoutBinding, val viewModel: EventViewModel): RecyclerView.ViewHolder(binding.root) {

    private lateinit var currentPerson: Person

    init {
        binding.root.setOnClickListener { view ->
            viewModel.selectedPersonDisplay = currentPerson
            binding.root.findNavController().navigate(R.id.action_viewEventFragment_to_viewPersonFragment)
        }
    }

    fun bindPerson(person: Person){
        currentPerson = person
        binding.viewEventName.text = currentPerson.name
    }
}