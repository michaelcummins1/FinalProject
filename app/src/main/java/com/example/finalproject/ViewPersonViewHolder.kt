package com.example.finalproject

import ViewPersonAdapter
import android.annotation.SuppressLint
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
    val activity: FragmentActivity?
) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var currentGift: String

    init {
        binding.imageButton.setOnClickListener { view ->
            viewModel.deleteGiftFromPerson(currentGift)
            adapter.notifyDataSetChanged()
//            if (viewModel.selectedPersonDisplay.giftIdeas.size == 0) {
//                MaterialAlertDialogBuilder(requireContext())
//                    .setTitle("No remaining gifts")
//                    .setMessage("Would you like to delete the person?")
//                    .setPositiveButton("Yes") { dialog, which ->
//
//                    }
//                    .setNegativeButton("No") { dialog, which ->
//                    }
//                 .show()
//            }
        }
    }

    fun bindGift(gift: String) {
        currentGift = gift
        binding.viewPersonGift.text = currentGift
    }
}