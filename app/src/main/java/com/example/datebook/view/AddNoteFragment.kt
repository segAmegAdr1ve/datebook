package com.example.datebook.view

import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
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
import org.intellij.lang.annotations.Language
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.Locale.LanguageRange

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class AddNoteFragment : Fragment() {

    private val viewModel: DatebookViewModel by activityViewModels()

    private var _binding: FragmentAddNoteBinding? = null
    private val binding get() = _binding!!

    //private val navigationArgs: добавить nav args in nav_graph
    //private var pickedDate: Long? = null // мб сделать с этим полем так же как с байндингом выше???


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
                viewModel.pickedEpochDay = it
                binding.noteDateEnd.setText(viewModel.formatDate())

                //подумать над использованием calendar потому что в него можно сунуть часы и минуты calendar.setDate()
                /*val calendar = Calendar.Builder().setInstant(it)
                    .setLocale(Locale.getDefault()).build()//Builder().setLanguage("ru"))
                calendar.set(Calendar.HOUR_OF_DAY, 12)
                calendar.set(Calendar.MINUTE, 30)*/

                //calendar.set//()//setTimeOfDay()
                //val outputDateFormat = java.text.SimpleDateFormat("dd:MM hh:mm:ss yyyy", Locale.getDefault())
                viewModel.setDate(it)
                Toast.makeText(context, viewModel.formatDateFromCalendar(), Toast.LENGTH_LONG).show()
            }
        }

        binding.noteTimeEnd.setOnClickListener {
            showTimePicker { hour, minute ->
                binding.noteTimeEnd.setText(getString(R.string.time_format, hour, minute))
                viewModel.pickedTimeHour = hour
                viewModel.pickedTimeMinute = minute
            }
        }



        //dasdasd
        //var timestamp: Long = 1231231231

        //binding.noteName.setOnFocusChangeListener()

        /*val date = Date(timestamp * 1000)
        val localdate = LocalDate.ofEpochDay(1312312123)
        ZonedDateTime.ofInstant(
            Instant.ofEpochMilli(System.currentTimeMillis()),
            ZoneId.systemDefault()
        )
        val formattedDateTime = DateTimeFormatter
            .ofPattern("dd MM yyyy HH:mm:ss")
            .format(localdate)*/
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