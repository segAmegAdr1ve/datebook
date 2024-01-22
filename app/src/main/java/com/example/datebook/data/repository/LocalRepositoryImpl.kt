package com.example.datebook.data.repository

import android.util.Log
import androidx.core.graphics.blue
import com.example.datebook.data.database.dao.DatebookDao
import com.example.datebook.data.mappers.Mapper
import com.example.datebook.domain.entity.CompactEntryModel
import com.example.datebook.domain.entity.DetailEntryModel
import com.example.datebook.domain.repository.LocalRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.job
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val datebookDao: DatebookDao,
    private val mapper: Mapper
) : LocalRepository {
    override suspend fun getDetailEntryById(entryId: Int): DetailEntryModel {
        return mapper.toDetailEntryModel(datebookDao.getEntryById(entryId))
    }
    override suspend fun getCompactEntryListByDate(
        year: Int,
        month: Int,
        dayOfMonth: Int
    ): List<CompactEntryModel> {
        val calendar = Calendar.getInstance()
        val listOfEntries = mutableListOf<CompactEntryModel>()

        datebookDao.getAllEntries().forEach {
            calendar.timeInMillis = it.dateFinish
            if (calendar.get(Calendar.YEAR) == year &&
                calendar.get(Calendar.MONTH) == month &&
                calendar.get(Calendar.DAY_OF_MONTH) == dayOfMonth
            ) {
                listOfEntries.add(mapper.toCompactEntryModel(it))
            }
        }
        return listOfEntries
    }
    override suspend fun insertEntry(detailEntryModel: DetailEntryModel) {
        datebookDao.insertDatebookEntry(mapper.toDatebookDBModel(detailEntryModel))
    }

}