package ru.lonelywh1te.stephenkingapp.data.repository

import android.util.Log
import ru.lonelywh1te.stephenkingapp.data.BookApi
import ru.lonelywh1te.stephenkingapp.data.BookMapper
import ru.lonelywh1te.stephenkingapp.domain.model.Book
import ru.lonelywh1te.stephenkingapp.domain.repository.IBookRepository

private const val LOG_TAG = "BookRepositoryImpl"

class BookRepositoryImpl(private val api: BookApi): IBookRepository {
    private val mapper = BookMapper()

    override suspend fun getBooks(): List<Book> {
        try {
            val response = api.getBooks()

            if (response.isSuccessful) {
                response.body()?.let { body ->
                    val books = body.data
                    return books.map { book -> mapper.bookDtoToBook(book) }
                }
            } else {
                Log.e(LOG_TAG, response.errorBody().toString())
            }

        } catch (e: Exception) {
            Log.e(LOG_TAG, "", e)
            return listOf()
        }

        return listOf()
    }
}