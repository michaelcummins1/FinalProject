package com.example.finalproject

import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.databinding.DisplayEventItemLayoutBinding

class DisplayEventViewHolder(val binding: DisplayEventItemLayoutBinding, val viewModel: EventViewModel) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var currentEvent : Event

    init {
        binding.root.setOnClickListener { view ->
            viewModel.selectedEvent = currentEvent
            binding.root.findNavController().navigate(R.id.action_displayEventsFragment_to_viewEventFragment)
        }
    }

    fun bindEvent(event: Event){
        currentEvent = event
        binding.eventDateDisplay.text = currentEvent.date
        binding.eventNameDisplay.text = currentEvent.title
    }
}