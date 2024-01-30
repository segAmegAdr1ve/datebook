package com.example.datebook.domain.usecases

import com.example.datebook.domain.entity.CompactEntryModel
import com.example.datebook.domain.entity.DetailEntryModel
import com.example.datebook.domain.repository.LocalRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import java.lang.Exception

class TestLocalRepository: LocalRepository {

    override suspend fun getDetailEntryById(entryId: Int): DetailEntryModel {
        val testEntriesList = listOf(
            DetailEntryModel(
                1,
                11111,
                "name1",
                "description1"
            ),
            DetailEntryModel(
                id = 2,
                dateFinish = 22222,
                name = "name2",
                description = "description2"
            )
        )
        return testEntriesList.firstOrNull { it.id == entryId } ?: throw Exception("Entry not found")
    }

    override suspend fun getCompactEntryListByDate(
        year: Int,
        month: Int,
        dayOfMonth: Int
    ): List<CompactEntryModel> {
        TODO("Not yet implemented")
    }

    override suspend fun insertEntry(detailEntryModel: DetailEntryModel) {
        TODO("Not yet implemented")
    }

}

class GetDetailEntryUseCaseTest {

    @Test
    fun getEntryByIdShouldReturnMatchingEntry() {
        val useCase = GetDetailEntryUseCase(localRepository = TestLocalRepository())
        runBlocking {
            val actual = useCase.execute(2)
            val expected = DetailEntryModel(
                id = 2,
                dateFinish = 22222,
                name = "name2",
                description = "description2"
            )
            assertEquals(expected, actual)
        }
    }

}
