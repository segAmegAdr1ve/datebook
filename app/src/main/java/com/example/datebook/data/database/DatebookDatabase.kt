package com.example.datebook.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.datebook.Constants.DATABASE_VERSION
import com.example.datebook.data.database.dao.DatebookDao
import com.example.datebook.data.database.models.DatebookDBModel

@Database(
    entities = [DatebookDBModel::class],
    version = DATABASE_VERSION,
    exportSchema = false
)
abstract class DatebookDatabase: RoomDatabase() {

    abstract fun getDatebookDao(): DatebookDao

}