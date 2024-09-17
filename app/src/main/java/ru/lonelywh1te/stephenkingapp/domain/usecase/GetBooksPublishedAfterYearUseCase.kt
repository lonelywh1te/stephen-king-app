package ru.lonelywh1te.stephenkingapp.domain.usecase

import ru.lonelywh1te.stephenkingapp.domain.model.Book
import ru.lonelywh1te.stephenkingapp.domain.repository.IBookRepository

class GetBooksPublishedAfterYearUseCase(private val repository: IBookRepository) {
    suspend fun execute(year: Int): List<Book> {
        return repository.getBooks().filter { it.year > year }
    }
}