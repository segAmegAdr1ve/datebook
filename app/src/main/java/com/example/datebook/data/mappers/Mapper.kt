package com.example.datebook.data.mappers

import com.example.datebook.data.database.models.DatebookDBModel
import com.example.datebook.domain.entity.CompactEntryModel
import com.example.datebook.domain.entity.DetailEntryModel
import javax.inject.Inject

class Mapper @Inject constructor() {

    fun toDetailEntryModel(datebookDBModel: DatebookDBModel) =
        with(datebookDBModel) {
            DetailEntryModel(
                id = id,
                dateFinish = dateFinish,
                name = name,
                description = description
            )
        }

    fun toDatebookDBModel(detailEntryModel: DetailEntryModel) =
        with(detailEntryModel) {
            DatebookDBModel(
                id = id,
                dateFinish = dateFinish,
                name = name,
                description = description
            )
        }

    fun toCompactEntryModel(datebookDBModel: DatebookDBModel) =
        with(datebookDBModel) {
            CompactEntryModel(
                id = id,
                dateFinish = dateFinish,
                name = name
            )
        }
}