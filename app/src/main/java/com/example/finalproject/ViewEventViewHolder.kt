package com.example.finalproject

import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.databinding.ViewEventItemLayoutBinding

class ViewEventViewHolder(val binding: ViewEventItemLayoutBinding, val viewModel: EventViewModel): RecyclerView.ViewHolder(binding.root) {

    private lateinit var currentPerson: Person

    fun bindPerson(person: Person){
        currentPerson = person
        binding.viewEventName.text = currentPerson.name
    }
}