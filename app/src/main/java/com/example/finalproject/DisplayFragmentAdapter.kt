package com.example.finalproject


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.databinding.DisplayEventItemLayoutBinding

class DisplayFragmentAdapter(val eventList: List<Event>, val viewModel: EventViewModel) : RecyclerView.Adapter<DisplayEventViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DisplayEventViewHolder {
        val binding = DisplayEventItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DisplayEventViewHolder(binding, viewModel, this)
    }

    override fun onBindViewHolder(holder: DisplayEventViewHolder, position: Int) {
        val currentEvent = eventList[position]
        holder.bindEvent(currentEvent)
    }

    override fun getItemCount(): Int {
        return eventList.size
    }

}