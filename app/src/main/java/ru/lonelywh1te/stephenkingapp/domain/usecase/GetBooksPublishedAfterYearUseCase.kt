package ru.lonelywh1te.stephenkingapp.domain.usecase

import ru.lonelywh1te.stephenkingapp.domain.model.Book
import ru.lonelywh1te.stephenkingapp.domain.model.Result
import ru.lonelywh1te.stephenkingapp.domain.repository.IBookRepository

class GetBooksPublishedAfterYearUseCase(private val repository: IBookRepository) {
    suspend fun execute(year: Int): Result<List<Book>> {
        var result = repository.getBooks()

        if (result is Result.Success) {
            result = Result.Success(data = result.data.filter { it.year > year })
        }

        return result
    }
}