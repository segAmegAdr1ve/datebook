package com.example.datebook.domain.repository

import com.example.datebook.domain.entity.CompactEntryModel
import com.example.datebook.domain.entity.DetailEntryModel

interface LocalRepository {

    suspend fun getDetailEntryById(entryId: Int): DetailEntryModel

    suspend fun getCompactEntryListByDate(year: Int, month: Int, dayOfMonth: Int): List<CompactEntryModel>

    suspend fun insertEntry(detailEntryModel: DetailEntryModel)
}