package com.example.datebook.domain.usecases

import com.example.datebook.domain.entity.DetailEntryModel
import com.example.datebook.domain.repository.LocalRepository
import javax.inject.Inject

class GetDetailEntryUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {
    suspend fun execute(entryId: Int): DetailEntryModel {
        return localRepository.getDetailEntryById(entryId)
    }
}