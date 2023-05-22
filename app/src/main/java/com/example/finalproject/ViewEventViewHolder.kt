package com.example.finalproject

import android.annotation.SuppressLint
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.databinding.ViewEventItemLayoutBinding

@SuppressLint("NotifyDataSetChanged")
class ViewEventViewHolder(
    val binding: ViewEventItemLayoutBinding,
    val viewModel: EventViewModel,
    val event: Event,
    val adapter: ViewEventAdapter
) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var currentPerson: Person

    init {
        binding.root.setOnClickListener { view ->
            viewModel.selectedPersonDisplay = currentPerson
            binding.root.findNavController()
                .navigate(R.id.action_viewEventFragment_to_viewPersonFragment)
        }
        binding.trashButton2.setOnClickListener { view ->
            viewModel.deletePersonFromEvent(currentPerson, event)
            adapter.notifyDataSetChanged()
        }
    }

    fun bindPerson(person: Person) {
        currentPerson = person
        binding.viewEventName.text = currentPerson.name
    }
}