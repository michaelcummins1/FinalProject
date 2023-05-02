package com.example.finalproject

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.databinding.PickPersonItemLayoutBinding

class PickPersonViewHolder(val binding: PickPersonItemLayoutBinding, val viewModel: EventViewModel) : RecyclerView.ViewHolder(binding.root) {
    private lateinit var currentPerson: Person

    init {
        binding.root.setOnClickListener { view ->
            viewModel.pickedPerson(currentPerson)
            binding.root.findNavController().navigateUp()
        }
    }

        fun bindPerson(person: Person) {
            currentPerson = person
            binding.pickPersonName.text = currentPerson.name
        }

}