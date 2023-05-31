package com.example.finalproject

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.finalproject.databinding.FragmentCreatePersonBinding
import androidx.navigation.findNavController

class CreatePersonFragment : Fragment() {

    private var _binding: FragmentCreatePersonBinding? = null
    private val binding get() = _binding!!

    val viewModel: EventViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreatePersonBinding.inflate(inflater, container, false)
        val rootView = binding.root



        val myOnClickListener = View.OnClickListener { view ->
            when(view.id){
                R.id.create_button ->{
                    if(binding.editTextName.text.toString().equals("")){
                        Toast.makeText(activity, "Give the person a name", Toast.LENGTH_SHORT).show()
                    }
                    else if(binding.editTextGift.text.toString().equals("")){
                        Toast.makeText(activity, "Add gift ideas", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        val personName = binding.editTextName.text.toString()
                        val giftList = binding.editTextGift.text.toString()
                        viewModel.createNewPerson(personName, giftList)
                        rootView.findNavController().navigateUp()
                    }
                }
                R.id.ideas_button2 -> openWebPage("https://www.goodhousekeeping.com/holidays/gift-ideas/g43163293/most-popular-gifts-2023/")

                R.id.go_back_button_4 -> rootView.findNavController().navigateUp()
            }
        }

        binding.createButton.setOnClickListener(myOnClickListener)
        binding.ideasButton2.setOnClickListener(myOnClickListener)
        binding.goBackButton4.setOnClickListener(myOnClickListener)

        return rootView
    }

    fun openWebPage(url: String) {
        val webpage: Uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        startActivity(intent)
    }

}