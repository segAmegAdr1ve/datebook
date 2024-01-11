package com.example.datebook.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.datebook.R
import com.example.datebook.databinding.FragmentAddNoteBinding
import com.example.datebook.viewModel.DatebookViewModel
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.MaterialTimePicker.INPUT_MODE_CLOCK
import com.google.android.material.timepicker.TimeFormat

class AddNoteFragment : Fragment() {

    private val viewModel: DatebookViewModel by activityViewModels()

    private var _binding: FragmentAddNoteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.noteDateEnd.setOnClickListener {
            showDatePicker {
                viewModel.setDate(date = it)
                binding.noteDateEnd.setText(viewModel.formatDate())
            }
        }

        binding.noteTimeEnd.setOnClickListener {
            showTimePicker { hour, minute ->
                viewModel.setTimeOfDay(hour = hour, minute = minute)
                binding.noteTimeEnd.setText(viewModel.formatTimeOfDay())
            }
        }
    }

    private fun showDatePicker(onDateSelected: (epochDay: Long) -> (Unit)) {
        //Можно выбрать только дату текущая+
        val constraintsBuilder = CalendarConstraints.Builder()
            .setValidator(DateValidatorPointForward.now())
            .build()

        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText(getString(R.string.choose_date))
                .setCalendarConstraints(constraintsBuilder)
                .build()
                .apply {
                    addOnPositiveButtonClickListener {
                        onDateSelected(it)
                    }
                }
        datePicker.show(parentFragmentManager, "TAG")
    }
    private fun showTimePicker(onTimeSelected: (hour: Int, minute: Int) -> (Unit)) {
        val timePicker =
            MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .setInputMode(INPUT_MODE_CLOCK)
                .setHour(12)
                .setMinute(30)
                .setTitleText(getString(R.string.choose_time))
                .build()
                .apply {
                    addOnPositiveButtonClickListener {
                        onTimeSelected(hour, minute)
                    }
                }
        timePicker.show(parentFragmentManager, "TAG")
    }
}