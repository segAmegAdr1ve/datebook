package com.example.datebook.model.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//@Entity(tableName = )
data class noteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    //@ColumnInfo(name = "dateFinish")
    val dateFinish: Long,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "description")
    val description: String
)