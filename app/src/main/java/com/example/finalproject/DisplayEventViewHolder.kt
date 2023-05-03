package com.example.finalproject

import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.databinding.DisplayEventItemLayoutBinding

class DisplayEventViewHolder(val binding: DisplayEventItemLayoutBinding, val viewModel: EventViewModel) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var currentEvent : Event

    fun bindEvent(event: Event){
        currentEvent = event
        binding.eventDateDisplay.text = currentEvent.date
        binding.eventNameDisplay.text = currentEvent.title
    }
}