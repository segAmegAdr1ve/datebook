package com.example.datebook.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.datebook.domain.entity.DetailEntryModel
import com.example.datebook.domain.entity.ItemModel
import com.example.datebook.domain.usecases.GetDetailEntryUseCase
import com.example.datebook.domain.usecases.GetItemListUseCase
import com.example.datebook.domain.usecases.InsertEntryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class DatebookViewModel @Inject constructor(
    private val getItemListUseCase: GetItemListUseCase,
    private val getDetailEntryUseCase: GetDetailEntryUseCase,
    private val insertEntryUseCase: InsertEntryUseCase
) : ViewModel() {

    private val selectedDateTimeCalendar: Calendar = Calendar.getInstance()
    val dateTime get() = selectedDateTimeCalendar.timeInMillis

    private val _itemListLiveData = MutableLiveData<List<ItemModel>>()
    val itemListLiveData = _itemListLiveData as LiveData<List<ItemModel>>

    private val _currentEntryLiveData = MutableLiveData<DetailEntryModel>()
    val currentEntryLiveData = _currentEntryLiveData as LiveData<DetailEntryModel>

    init {
        selectedDateTimeCalendar.clear()
    }

    fun getItemList(year: Int, month: Int, dayOfMonth: Int) {
        viewModelScope.launch {
            _itemListLiveData.postValue(getItemListUseCase.execute(year, month, dayOfMonth))
        }
    }

    fun getEntry(entryId: Int) {
        viewModelScope.launch {
            _currentEntryLiveData.postValue(getDetailEntryUseCase.execute(entryId))
        }
    }

    fun insertEntry(id: Int, dateFinish: Long, name: String, description: String) {
        viewModelScope.launch {
            insertEntryUseCase.execute(DetailEntryModel(id, dateFinish, name, description))
        }
    }

    fun setDate(date: Long) {
        val temporaryCalendar = Calendar.Builder().setInstant(date).build()
        selectedDateTimeCalendar.set(
            temporaryCalendar.get(Calendar.YEAR),
            temporaryCalendar.get(Calendar.MONTH),
            temporaryCalendar.get(Calendar.DAY_OF_MONTH)
        )
    }

    fun setTimeOfDay(hour: Int, minute: Int) {
        selectedDateTimeCalendar.set(Calendar.HOUR_OF_DAY, hour)
        selectedDateTimeCalendar.set(Calendar.MINUTE, minute)
    }

    fun isEntryValid(
        entryName: String, dateEnd: String, timeEnd: String, description: String
    ): Boolean {
        return !(entryName.isBlank() || dateEnd.isBlank() || timeEnd.isBlank() || description.isBlank())
    }
}
