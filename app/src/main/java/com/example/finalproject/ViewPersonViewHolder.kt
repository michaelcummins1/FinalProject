package com.example.finalproject

import ViewPersonAdapter
import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.databinding.FragmentViewPersonBinding
import com.example.finalproject.databinding.ViewPersonItemLayoutBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.security.AccessController.getContext

@SuppressLint("NotifyDataSetChanged")
class ViewPersonViewHolder(
    val binding: ViewPersonItemLayoutBinding,
    val binding2: FragmentViewPersonBinding,
    val viewModel: EventViewModel,
    val adapter: ViewPersonAdapter,
    val activity: FragmentActivity?,
    val context: Context
) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var currentGift: String

    init {
        binding.imageButton.setOnClickListener { view ->
            if (viewModel.selectedPersonDisplay.giftIdeas.size == 1) {
                Toast.makeText(activity, "You cannot delete all the gift ideas from a person", Toast.LENGTH_SHORT).show()
            }
            else{
                viewModel.deleteGiftFromPerson(currentGift)
                adapter.notifyDataSetChanged()
            }
        }
    }

    fun bindGift(gift: String) {
        currentGift = gift
        binding.viewPersonGift.text = currentGift
    }
}