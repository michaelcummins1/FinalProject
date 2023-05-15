package com.example.finalproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.finalproject.databinding.FragmentPickDateBinding

class PickDateFragment : Fragment() {

    private var _binding: FragmentPickDateBinding? = null
    private val binding get() = _binding!!

    val viewModel: EventViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPickDateBinding.inflate(inflater, container, false)
        val rootView = binding.root

        binding.calendarView.setOnDateChangeListener(object : CalendarView.OnDateChangeListener {
            override fun onSelectedDayChange(view: CalendarView, year: Int, month: Int, d: Int) {
                viewModel.selectedDate.value = "${month + 1}/$d/$year"
            }
        })

        val myOnClickListener: View.OnClickListener = View.OnClickListener { view ->
            when (view.id) {
                R.id.confirm_date -> {
                    viewModel.selectedPersonCreate.value = null
                    rootView.findNavController().navigateUp()
                }
            }
        }

        binding.confirmDate.setOnClickListener(myOnClickListener)


        return rootView
    }
}