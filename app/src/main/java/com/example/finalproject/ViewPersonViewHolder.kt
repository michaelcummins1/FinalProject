package com.example.finalproject

import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.databinding.ViewPersonItemLayoutBinding

class ViewPersonViewHolder(val binding: ViewPersonItemLayoutBinding, val viewModel: EventViewModel): RecyclerView.ViewHolder(binding.root) {

    private lateinit var currentGift: String

    fun bindGift(gift: String){
        currentGift = gift
        binding.viewPersonGift.text = currentGift
    }
}