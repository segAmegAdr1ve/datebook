package com.example.datebook.di

import android.content.Context
import androidx.room.Room
import com.example.datebook.Constants.DATEBOOK_TABLE_NAME
import com.example.datebook.data.database.DatebookDatabase
import com.example.datebook.data.database.dao.DatebookDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): DatebookDatabase {
        return Room.databaseBuilder(context, DatebookDatabase::class.java, DATEBOOK_TABLE_NAME)
            .build()
    }

    @Singleton
    @Provides
    fun provideDatebookDao(database: DatebookDatabase): DatebookDao {
        return database.getDatebookDao()
    }

}