package com.example.finalproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.databinding.ViewEventItemLayoutBinding

class ViewEventAdapter(val peopleList: List<Int>, val viewModel: EventViewModel, val event: Event) :
    RecyclerView.Adapter<ViewEventViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewEventViewHolder {
        val binding =
            ViewEventItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewEventViewHolder(binding, viewModel, event, this)
    }

    override fun onBindViewHolder(holder: ViewEventViewHolder, position: Int) {
        val currentPerson = viewModel.personList[peopleList[position]]
        holder.bindPerson(currentPerson)
    }

    override fun getItemCount(): Int {
        return peopleList.size
    }

}