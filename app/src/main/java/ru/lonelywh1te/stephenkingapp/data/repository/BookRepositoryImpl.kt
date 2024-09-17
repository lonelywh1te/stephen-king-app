package ru.lonelywh1te.stephenkingapp.data.repository

import android.util.Log
import ru.lonelywh1te.stephenkingapp.data.BookApi
import ru.lonelywh1te.stephenkingapp.data.BookMapper
import ru.lonelywh1te.stephenkingapp.domain.model.Book
import ru.lonelywh1te.stephenkingapp.domain.model.Result
import ru.lonelywh1te.stephenkingapp.domain.repository.IBookRepository

private const val LOG_TAG = "BookRepositoryImpl"

class BookRepositoryImpl(private val api: BookApi): IBookRepository {
    private val mapper = BookMapper()

    override suspend fun getBooks(): Result<List<Book>> {
        try {
            val response = api.getBooks()

            if (response.isSuccessful) {
                response.body()?.let { body ->
                    val data = body.data
                    val books = data.map { book -> mapper.bookDtoToBook(book) }

                    return Result.Success(data = books)
                }
                return Result.Error(Exception("Error: Response body is null"))
            } else {
                Log.e(LOG_TAG, response.errorBody().toString())
                return Result.Error(Exception("Error: ${response.code()} ${response.message()}"))
            }
        } catch (e: Exception) {
            return Result.Error(e)
        }
    }
}