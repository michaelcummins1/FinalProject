package com.example.finalproject

import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.databinding.PickPersonItemLayoutBinding

class PickPersonViewHolder(val binding: PickPersonItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
    private lateinit var currentPerson : Person

    fun bindPerson(person: Person){
        currentPerson = person
        binding.pickPersonName.text = currentPerson.name
    }
}