package com.example.datebook.model.data

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.RoomDatabase


@Dao
interface NoteDao {

}

@Database(entities = [noteEntity::class], version = 1)
abstract class NoteDatabase: RoomDatabase() {
    abstract fun getNoteDao(): NoteDao

    companion object {
        @Volatile
        private lateinit var INSTANCE: NoteDatabase
        fun getDatabase(context: Context): NoteDatabase {
            synchronized(this) {
                if (!::INSTANCE.isInitialized)
            }
        }
    }
}