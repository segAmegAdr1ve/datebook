package com.example.datebook.di

import com.example.datebook.data.repository.LocalRepositoryImpl
import com.example.datebook.domain.repository.LocalRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun getLocalRepository(localRepositoryImpl: LocalRepositoryImpl): LocalRepository

}