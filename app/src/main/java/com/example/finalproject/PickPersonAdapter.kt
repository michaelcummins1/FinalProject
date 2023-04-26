package com.example.finalproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.databinding.PickPersonItemLayoutBinding

class PickPersonAdapter(val personList: List<Person>): RecyclerView.Adapter<PickPersonViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PickPersonViewHolder {
        val binding = PickPersonItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PickPersonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PickPersonViewHolder, position: Int) {
        val currentPerson = personList[position]
        holder.bindPerson(currentPerson)
    }

    override fun getItemCount(): Int {
        return personList.size
    }


}