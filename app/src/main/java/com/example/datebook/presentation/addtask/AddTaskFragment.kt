package com.example.datebook.presentation.addtask

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.datebook.Constants.ADD_TASK
import com.example.datebook.R
import com.example.datebook.databinding.FragmentAddTaskBinding
import com.example.datebook.domain.entity.DetailEntryModel
import com.example.datebook.presentation.viewmodels.DatebookViewModel
import com.example.datebook.utils.toNormalDateFormat
import com.example.datebook.utils.toNormalTimeFormat
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.MaterialTimePicker.INPUT_MODE_CLOCK
import com.google.android.material.timepicker.TimeFormat

class AddTaskFragment : Fragment() {

    private val viewModel: DatebookViewModel by activityViewModels()

    private var _binding: FragmentAddTaskBinding? = null
    private val binding get() = _binding!!

    private val navigationArgs: AddTaskFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val entryId = navigationArgs.entryId

        if (entryId == ADD_TASK) {
            binding.noteDateEnd.setOnClickListener {
                showDatePicker {
                    viewModel.setDate(date = it)
                    binding.noteDateEnd.setText(it.toNormalDateFormat())
                }
            }

            binding.noteTimeEnd.setOnClickListener {
                showTimePicker { hour, minute ->
                    viewModel.setTimeOfDay(hour = hour, minute = minute)
                    binding.noteTimeEnd.setText(viewModel.dateTime.toNormalTimeFormat())
                }
            }

            binding.saveBtn.setOnClickListener {
                with(binding) {
                    if (viewModel.isEntryValid(
                            noteName.text.toString(),
                            noteDateEnd.text.toString(),
                            noteTimeEnd.text.toString(),
                            noteDescription.text.toString()
                        )
                    ) {
                        viewModel.insertEntry(
                            id = 0,
                            dateFinish = viewModel.dateTime,
                            name = noteName.text.toString(),
                            description = noteDescription.text.toString()
                        )
                        findNavController().navigateUp()
                    } else Toast.makeText(context, getString(R.string.fill_all_fields), Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            viewModel.getEntry(entryId)
            viewModel.currentEntryLiveData.observe(viewLifecycleOwner) {
                bind(it)
            }
            binding.saveBtn.visibility = View.INVISIBLE
            binding.noteName.focusable = View.NOT_FOCUSABLE
            binding.noteDescription.focusable = View.NOT_FOCUSABLE
        }
    }

    private fun showDatePicker(onDateSelected: (epochDay: Long) -> (Unit)) {
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

    private fun bind(detailEntryModel: DetailEntryModel) {
        with(binding) {
            noteName.setText(detailEntryModel.name)
            noteDateEnd.setText(detailEntryModel.dateFinish.toNormalDateFormat())
            noteTimeEnd.setText(detailEntryModel.dateFinish.toNormalTimeFormat())
            noteDescription.setText(detailEntryModel.description)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}