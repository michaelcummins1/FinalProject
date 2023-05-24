package com.example.finalproject

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.databinding.ViewEventItemLayoutBinding

@SuppressLint("NotifyDataSetChanged")
class ViewEventViewHolder(
    val binding: ViewEventItemLayoutBinding,
    val viewModel: EventViewModel,
    val event: Event,
    val adapter: ViewEventAdapter,
    val activity: FragmentActivity?
) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var currentPerson: Person

    init {
        binding.root.setOnClickListener { view ->
            viewModel.selectedPersonDisplay = currentPerson
            binding.root.findNavController()
                .navigate(R.id.action_viewEventFragment_to_viewPersonFragment)
        }
        binding.trashButton2.setOnClickListener { view ->
            if(event.people.size > 1) {
                viewModel.deletePersonFromEvent(currentPerson, event)
                adapter.notifyDataSetChanged()
            }
            else{
                Toast.makeText(activity, "You cannot delete all people from an event", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun bindPerson(person: Person) {
        currentPerson = person
        binding.viewEventName.text = currentPerson.name
    }
}