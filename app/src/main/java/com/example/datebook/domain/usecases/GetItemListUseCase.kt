package com.example.datebook.domain.usecases

import android.content.Context
import com.example.datebook.R
import com.example.datebook.domain.entity.Header
import com.example.datebook.domain.entity.ItemModel
import com.example.datebook.domain.repository.LocalRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.Calendar
import javax.inject.Inject

class GetItemListUseCase @Inject constructor(
    private val localRepository: LocalRepository,
    @ApplicationContext private val context: Context
) {
    suspend fun execute(year: Int, month: Int, dayOfMonth: Int): MutableList<ItemModel> {

        val entryList = localRepository.getCompactEntryListByDate(year, month, dayOfMonth)
        val listOfTimeRanges = context.resources.getStringArray(R.array.time_ranges)
        val calendar = Calendar.getInstance()
        val listResult = listOfTimeRanges.map { range ->
            ItemModel(Header(range), null)
        }.toMutableList()

        for (entry in entryList) {
            calendar.timeInMillis = entry.dateFinish
            listResult.add(
                listResult.indexOf(
                    ItemModel(
                        Header(listOfTimeRanges[calendar.get(Calendar.HOUR_OF_DAY)]), null
                    )) + 1,
                ItemModel(header = null, entry)
            )
        }
        return listResult
    }
}