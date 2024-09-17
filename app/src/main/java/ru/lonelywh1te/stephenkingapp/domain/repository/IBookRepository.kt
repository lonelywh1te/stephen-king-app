package ru.lonelywh1te.stephenkingapp.domain.repository

import ru.lonelywh1te.stephenkingapp.domain.model.Book
import ru.lonelywh1te.stephenkingapp.domain.model.Result

interface IBookRepository {
    suspend fun getBooks(): Result<List<Book>>
}