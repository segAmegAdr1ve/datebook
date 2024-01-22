package com.example.datebook.data.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.datebook.Constants.DATEBOOK_TABLE_NAME

@Entity(tableName = DATEBOOK_TABLE_NAME)
data class DatebookDBModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val dateFinish: Long,
    val name: String,
    val description: String
)