package ru.lonelywh1te.stephenkingapp.domain.repository

import ru.lonelywh1te.stephenkingapp.domain.model.Book

interface IBookRepository {
    suspend fun getBooks(): List<Book>
}