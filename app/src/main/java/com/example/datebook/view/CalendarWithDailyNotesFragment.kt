package com.example.datebook.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.datebook.R
import com.example.datebook.databinding.FragmentCalendarWithDailyNotesBinding
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import java.util.Calendar


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class CalendarWithDailyNotesFragment : Fragment() {

    private var _binding: FragmentCalendarWithDailyNotesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCalendarWithDailyNotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // viewModel и обсерверы все сюда
        binding.floatingActionButton2.setOnClickListener {
            val action = CalendarWithDailyNotesFragmentDirections
                .actionCalendarWithDailyNotesFragmentToAddNoteFragment()
            findNavController().navigate(action)
        }

    }
}