package com.example.finalproject

import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.databinding.PickPersonItemLayoutBinding

class PickPersonViewHolder(val binding: PickPersonItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
    private lateinit var currentPerson : Person

    init{
        binding.root.setOnClickListener{ view ->
        setFragmentResultListener("PICK_PERSON_KEY")
        }
    }

    fun bindPerson(person: Person){
        currentPerson = person
        binding.pickPersonName.text = currentPerson.name
    }
}