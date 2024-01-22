package com.example.datebook.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.datebook.data.database.models.DatebookDBModel
import com.example.datebook.Constants.DATEBOOK_TABLE_NAME

@Dao
interface DatebookDao {

    @Insert
    suspend fun insertDatebookEntry(datebookNote: DatebookDBModel)

    @Query("SELECT * FROM $DATEBOOK_TABLE_NAME")
    suspend fun getAllEntries(): List<DatebookDBModel>

    @Query("SELECT * FROM $DATEBOOK_TABLE_NAME WHERE id = :id")
    suspend fun getEntryById(id: Int): DatebookDBModel

}