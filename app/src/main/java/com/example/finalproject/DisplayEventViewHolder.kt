package com.example.finalproject

import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaPlayer
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.databinding.DisplayEventItemLayoutBinding

@SuppressLint("NotifyDataSetChanged")
class DisplayEventViewHolder(
    val binding: DisplayEventItemLayoutBinding,
    val viewModel: EventViewModel,
    val adapter: DisplayFragmentAdapter,
) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var currentEvent: Event

    init {
        binding.root.setOnClickListener { view ->
            viewModel.selectedEvent = currentEvent
            binding.root.findNavController()
                .navigate(R.id.action_displayEventsFragment_to_viewEventFragment)
        }
        binding.trashButton.setOnClickListener { view ->
            viewModel.deleteEvent(currentEvent)
            adapter.notifyDataSetChanged()
        }
    }

    fun bindEvent(event: Event) {
        currentEvent = event
        binding.eventDateDisplay.text = currentEvent.date
        binding.eventNameDisplay.text = currentEvent.title
    }
}