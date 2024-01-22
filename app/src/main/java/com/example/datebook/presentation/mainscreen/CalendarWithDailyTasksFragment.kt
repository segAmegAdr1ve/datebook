package com.example.datebook.presentation.mainscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.datebook.databinding.FragmentCalendarWithDailyTasksBinding
import com.example.datebook.presentation.viewmodels.DatebookViewModel

class CalendarWithDailyTasksFragment : Fragment() {

    private val viewModel: DatebookViewModel by activityViewModels()

    private var _binding: FragmentCalendarWithDailyTasksBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCalendarWithDailyTasksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addNoteFAB.setOnClickListener {
            val action = CalendarWithDailyTasksFragmentDirections
                .actionCalendarWithDailyNotesFragmentToAddTaskFragment()
            findNavController().navigate(action)
        }

        val taskAdapter = TasksForDayAdapter()
        taskAdapter.onTaskCardClicked = { entryId ->
            val action = CalendarWithDailyTasksFragmentDirections
                .actionCalendarWithDailyNotesFragmentToAddTaskFragment(entryId)
            findNavController().navigate(action)
        }
        binding.recyclerView.adapter = taskAdapter

        viewModel.itemListLiveData.observe(viewLifecycleOwner) {
            taskAdapter.setList(it)
        }

        binding.calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            viewModel.getItemList(year, month, dayOfMonth)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}